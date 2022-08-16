package ru.rsreu.autoauthor.command.factory;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.command.client.CommandEnum;
import ru.rsreu.autoauthor.command.EmptyCommand;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
// извлечение имени команды из запроса
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
// если команда не задана в текущем запросе
            return current;
        }
// получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }

    public Set<String>  defineRole(HttpServletRequest request) {
        Set<String> current = new HashSet<>();
// извлечение имени команды из запроса
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
// если команда не задана в текущем запросе
            return current;
        }
// получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getRole();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}