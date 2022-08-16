package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteGroupCommand implements ActionCommand {
    public static final String ID = "delete_group_id";
    @Override
    public String execute(HttpServletRequest request) {
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;

        if (!groupDao.delete(request.getParameter(ID))){
            request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
        }

        ToGroupCommand toGroupCommand = new ToGroupCommand();
        return toGroupCommand.execute(request);
    }
}
