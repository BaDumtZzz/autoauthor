package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleFileDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToFileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OracleFileDao fileDao = (OracleFileDao) session.getServletContext().getAttribute("fileDao");;
        OracleGroupDao groupDao = (OracleGroupDao) session.getServletContext().getAttribute("groupDao");;

        List<String> currentRole = (List<String>) session.getAttribute("role");
        if (currentRole.contains("admin")){
            request.setAttribute("files", fileDao.getAll());
        } else {
            request.setAttribute("files", fileDao.getByGroupId(groupDao.getAllGroupIdByLeaderId(String.valueOf(session.getAttribute("id")))));
        }
        return ConfigurationManager.getProperty("path.page.file");
    }
}