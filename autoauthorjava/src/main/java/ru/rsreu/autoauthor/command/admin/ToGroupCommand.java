package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleFileDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToGroupCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        List<String> currentRole = (List<String>) request.getSession().getAttribute("role");
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
        if (currentRole.contains("admin")){
            request.setAttribute("groups", groupDao.getAll());
        } else {
            request.setAttribute("groups", groupDao.getAllGroupByLeaderId(String.valueOf(request.getSession().getAttribute("id"))));
        }

        return ConfigurationManager.getProperty("path.page.group");
    }
}