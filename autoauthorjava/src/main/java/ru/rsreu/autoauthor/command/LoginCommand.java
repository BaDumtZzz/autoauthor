package ru.rsreu.autoauthor.command;

import ru.rsreu.autoauthor.command.admin.ToAdminCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;
import ru.rsreu.autoauthor.logic.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = null;
// извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля
        User user = LoginLogic.checkLogin(login, pass);
        if (user!=null) {
            if (!user.getStatus().equals("blocked")){
                session.setMaxInactiveInterval(120);
                session.setAttribute("id", user.getId());
                session.setAttribute("nickname", user.getNickname());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", user.getRole());
                session.setAttribute("groupName", user.getGroupName());
                session.setAttribute("group", user.getGroup());
                session.setAttribute("status", user.getStatus());

                OracleUserDao oracleUserDao = (OracleUserDao) request.getSession().getServletContext().getAttribute("userDao");;
                oracleUserDao.setOnlineStatus(String.valueOf(session.getAttribute("id")));

                if (session.getAttribute("role").equals("admin")) {
                    ToAdminCommand command = new ToAdminCommand();
                    page = command.execute(request);
                } else{
                    page = ConfigurationManager.getProperty("path.page.main");
                }
            } else {
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginblocked"));
                page = ConfigurationManager.getProperty("path.page.login");
            }

        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
//        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
//        session.setAttribute("groups", groupDao.getAll());
        return page;
    }
}