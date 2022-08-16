package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.dao.oracle.OracleRoleDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToAddUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        OracleRoleDao roleDao = (OracleRoleDao) request.getSession().getServletContext().getAttribute("roleDao");;
        request.setAttribute("roles", roleDao.getAll());
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
        request.setAttribute("groups", groupDao.getAll());
        return ConfigurationManager.getProperty("path.page.add-user");
    }
}