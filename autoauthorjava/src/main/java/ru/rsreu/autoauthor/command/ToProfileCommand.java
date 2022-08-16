package ru.rsreu.autoauthor.command;

import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.result.JServer;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ToProfileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            return ConfigurationManager.getProperty("path.page.login");
        }
        // Create client socket
        return ConfigurationManager.getProperty("path.page.profile");
    }
}
