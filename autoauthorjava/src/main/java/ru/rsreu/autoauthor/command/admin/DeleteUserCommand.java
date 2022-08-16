package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {
    public static final String ID = "delete_user_id";
    @Override
    public String execute(HttpServletRequest request) {
        OracleUserDao userDao = (OracleUserDao) request.getSession().getServletContext().getAttribute("userDao");

        if (!userDao.delete(request.getParameter(ID))){
            request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
            return ConfigurationManager.getProperty("path.page.add-user");

        }
        ToAdminCommand toAdminCommand = new ToAdminCommand();
        return toAdminCommand.execute(request);
    }
}
