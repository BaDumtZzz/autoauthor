package ru.rsreu.autoauthor.command.admin;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.Author;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.logic.PasswordEncryption;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class AddAuthorCommand implements ActionCommand {
    public static final String NICKNAME = "new_nickname";
    public static final String GROUP = "new_group";
    @Override
    public String execute(HttpServletRequest request) {
        OracleAuthorDao authorDao = (OracleAuthorDao) request.getSession().getServletContext().getAttribute("authorDao");;
        Author newAuthor = new Author();
        newAuthor.setName(request.getParameter(NICKNAME));
        newAuthor.setGroup(Integer.valueOf(request.getParameter(GROUP)));


        if (!authorDao.save(newAuthor)){
            request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
            return ConfigurationManager.getProperty("path.page.add-author");
        }

        ToAuthorCommand toAuthorCommand = new ToAuthorCommand();
        return toAuthorCommand.execute(request);
    }
}