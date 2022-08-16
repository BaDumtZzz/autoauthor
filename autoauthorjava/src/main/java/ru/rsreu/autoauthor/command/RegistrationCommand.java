package ru.rsreu.autoauthor.command;

import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.logic.PasswordEncryption;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {
    public static final String NICKNAME = "new_nickname";
    public static final String PASSWORD = "new_password";
    public static final String EMAIL = "new_email";
    @Override
    public String execute(HttpServletRequest request) {
        OracleUserDao userDao = (OracleUserDao) request.getSession().getServletContext().getAttribute("userDao");
        User newUser = new User();
        newUser.setNickname(request.getParameter(NICKNAME));
        newUser.setPassword(PasswordEncryption.encryptPassword(request.getParameter(PASSWORD)));
        newUser.setEmail(request.getParameter(EMAIL));

        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");

        if (userDao.save(newUser)){
            if (!groupDao.addMember(String.valueOf( userDao.getLastId()), "5")) {
                request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
                return ConfigurationManager.getProperty("path.page.registration");
            }
        }

        return ConfigurationManager.getProperty("path.page.login");
    }
}