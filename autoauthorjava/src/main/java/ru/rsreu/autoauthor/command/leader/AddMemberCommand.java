package ru.rsreu.autoauthor.command.leader;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.command.admin.ToGroupCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddMemberCommand implements ActionCommand {
    public static final String GROUP = "new_group";
    public static final String MEMBER = "new_member";
    @Override
    public String execute(HttpServletRequest request) {
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");
        if (!groupDao.addMember(request.getParameter(MEMBER), request.getParameter(GROUP))){
            request.setAttribute("errorAddCategoryMessage", MessageManager.getProperty("message.addgrouperror"));

            return ConfigurationManager.getProperty("path.page.add-member");
        }

        ToUserCommand toUserCommand = new ToUserCommand();
        return toUserCommand.execute(request);
    }

}
