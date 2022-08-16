package ru.rsreu.autoauthor.command;

import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

public class ToAboutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty("path.page.about");
    }
}