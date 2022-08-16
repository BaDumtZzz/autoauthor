package ru.rsreu.autoauthor.command;

import ru.rsreu.autoauthor.dao.oracle.OracleDao;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToMainCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty("path.page.main");
    }
}