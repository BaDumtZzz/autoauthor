package ru.rsreu.autoauthor.command;

import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.logic.PasswordEncryption;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ToRegistrationCommand implements ActionCommand {
    public static final String NICKNAME = "new_nickname";
    public static final String PASSWORD = "new_password";
    public static final String EMAIL = "new_email";
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.registration");
    }
}