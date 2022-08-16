package ru.rsreu.autoauthor.command.leader;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.command.admin.ToAuthorCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class KickUserCommand implements ActionCommand {
    public static final String GROUP_ID = "kick_group_id";
    public static final String USER_ID = "kick_user_id";
    @Override
    public String execute(HttpServletRequest request) {
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");
        List<String> groupIdList = groupDao.getAllGroupIdByLeaderId(String.valueOf(request.getSession().getAttribute("id")));
        groupDao.kick(request.getParameter(GROUP_ID), request.getParameter(USER_ID));
        List<User> userList = groupDao.getAllMembersByGroupId(groupIdList);
        request.setAttribute("users", userList);

        return ConfigurationManager.getProperty("path.page.leader");

    }
}