package ru.rsreu.autoauthor.command.leader;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToAddMemberCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
        request.setAttribute("groups", groupDao.getAllGroupByLeaderId(String.valueOf(request.getSession().getAttribute("id"))));
        OracleUserDao userDao = OracleDao.instance().getUserDAO();
        List<User> userList = userDao.getAllWithOrgName();
        request.setAttribute("users", userList);
        return ConfigurationManager.getProperty("path.page.add-member");
    }
}