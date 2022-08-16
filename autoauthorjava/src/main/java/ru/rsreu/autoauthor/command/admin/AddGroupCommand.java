package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddGroupCommand implements ActionCommand {
    public static final String GROUP = "new_group";
    @Override
    public String execute(HttpServletRequest request) {
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
        String newCategory = request.getParameter(GROUP);
        if (!groupDao.save(newCategory)){
            request.setAttribute("errorAddCategoryMessage", MessageManager.getProperty("message.addgrouperror"));

            return ConfigurationManager.getProperty("path.page.add-group");
        }
        HttpSession session = request.getSession();
        session.setAttribute("groups", groupDao.getAll());

        return ConfigurationManager.getProperty("path.page.group");
    }

}
