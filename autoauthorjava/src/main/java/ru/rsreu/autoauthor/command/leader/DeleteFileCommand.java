package ru.rsreu.autoauthor.command.leader;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.command.admin.ToAuthorCommand;
import ru.rsreu.autoauthor.command.admin.ToFileCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleFileDao;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteFileCommand implements ActionCommand {
    public static final String ID = "delete_file_id";
    @Override
    public String execute(HttpServletRequest request) {
        OracleFileDao fileDao = (OracleFileDao) request.getSession().getServletContext().getAttribute("fileDao");

        if (!fileDao.delete(request.getParameter(ID))){
            request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
        }

        ToFileCommand toFileCommand = new ToFileCommand();
        return toFileCommand.execute(request);
    }
}
