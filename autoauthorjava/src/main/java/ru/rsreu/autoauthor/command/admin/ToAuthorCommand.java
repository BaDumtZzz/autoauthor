package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAuthorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OracleAuthorDao authorDao = (OracleAuthorDao) session.getServletContext().getAttribute("authorDao");
        OracleGroupDao groupDao = (OracleGroupDao) session.getServletContext().getAttribute("groupDao");;

        List<String> currentRole = (List<String>) session.getAttribute("role");
        if (currentRole.contains("admin")){
            request.setAttribute("authors", authorDao.getAll());
        } else {
            request.setAttribute("authors", authorDao.getByGroupId(groupDao.getAllGroupIdByLeaderId(String.valueOf(session.getAttribute("id")))));

        }
        return ConfigurationManager.getProperty("path.page.author");
    }
}