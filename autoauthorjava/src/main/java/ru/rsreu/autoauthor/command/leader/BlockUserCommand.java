package ru.rsreu.autoauthor.command.leader;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements ActionCommand {
    public static final String ID = "block_user_id";
    @Override
    public String execute(HttpServletRequest request) {
        OracleUserDao userDao = (OracleUserDao) request.getSession().getServletContext().getAttribute("userDao");;
        if (!userDao.block(request.getParameter(ID))){
            request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
        }

        ToLeaderCommand toLeaderCommand = new ToLeaderCommand();
        return toLeaderCommand.execute(request);
    }
}