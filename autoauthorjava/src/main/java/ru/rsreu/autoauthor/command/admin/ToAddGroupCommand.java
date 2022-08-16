package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToAddGroupCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.add-group");
    }
}