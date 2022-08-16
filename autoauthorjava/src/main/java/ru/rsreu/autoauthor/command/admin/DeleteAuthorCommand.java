package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteAuthorCommand implements ActionCommand {
    public static final String ID = "delete_author_id";
    @Override
    public String execute(HttpServletRequest request) {
        OracleAuthorDao authorDao = (OracleAuthorDao) request.getSession().getServletContext().getAttribute("authorDao");;

        if (!authorDao.delete(request.getParameter(ID))){
            request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
        }

        ToAuthorCommand toAuthorCommand = new ToAuthorCommand();
        return toAuthorCommand.execute(request);
    }
}
