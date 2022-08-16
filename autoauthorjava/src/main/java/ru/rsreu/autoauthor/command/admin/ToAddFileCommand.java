package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAddFileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        OracleAuthorDao authorDao = (OracleAuthorDao) request.getSession().getServletContext().getAttribute("authorDao");
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");
        List<String> groupIdList = groupDao.getAllGroupIdByLeaderId(String.valueOf(request.getSession().getAttribute("id")));

        request.setAttribute("authors", authorDao.getByGroupId(groupIdList));
        request.setAttribute("groups", groupDao.getAllGroupByLeaderId(String.valueOf(request.getSession().getAttribute("id"))));

        return ConfigurationManager.getProperty("path.page.add-file");
    }
}