package ru.rsreu.autoauthor.servlet;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.command.factory.ActionFactory;
import ru.rsreu.autoauthor.dao.oracle.OracleDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet("/controller")
public class FrontController extends HttpServlet {

    public void init() throws ServletException{
        this.getServletContext().setAttribute("groupDao", OracleDao.instance().getCategoryDAO());
        this.getServletContext().setAttribute("authorDao", OracleDao.instance().getAuthorDAO());
        this.getServletContext().setAttribute("roleDao", OracleDao.instance().getRoleDAO());
        this.getServletContext().setAttribute("userDao", OracleDao.instance().getUserDAO());
        this.getServletContext().setAttribute("fileDao", OracleDao.instance().getFileDAO());
        this.getServletContext().setAttribute("modelDao", OracleDao.instance().getModelDAO());

        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.getServletContext().setAttribute("uploader", new ServletFileUpload(fileFactory));
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}