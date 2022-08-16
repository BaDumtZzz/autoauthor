package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAddAuthorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();;
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
        List<String> currentRole = (List<String>) session.getAttribute("role");
        if (currentRole.contains("admin")){
            request.setAttribute("groups", groupDao.getAll());
        } else {
            request.setAttribute("groups", groupDao.getAllGroupByLeaderId(String.valueOf(session.getAttribute("id"))));

        }
        return ConfigurationManager.getProperty("path.page.add-author");
    }
}