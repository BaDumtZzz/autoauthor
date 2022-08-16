package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToAdminCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        OracleUserDao userDao = OracleDao.instance().getUserDAO();
        List<User> userList = userDao.getAllWithOrgName();
        request.setAttribute("users", userList);
        return ConfigurationManager.getProperty("path.page.admin");
    }
}