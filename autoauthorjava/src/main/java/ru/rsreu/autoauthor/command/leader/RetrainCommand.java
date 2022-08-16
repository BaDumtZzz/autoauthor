package ru.rsreu.autoauthor.command.leader;

import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.command.admin.ToFileCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleFileDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.dao.oracle.OracleModelDao;
import ru.rsreu.autoauthor.handler.MapProbabilityComparator;
import ru.rsreu.autoauthor.handler.ResultDeterminingAuthorHandler;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.result.JServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RetrainCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OracleModelDao modelDao = (OracleModelDao) session.getServletContext().getAttribute("modelDao");

        List<Integer> groupIdList = (List<Integer>) session.getAttribute("group");
//        modelDao.setStatusTrain(String.valueOf(groupIdList.get(0)));
        String messageToPython = "(" + String.valueOf(groupIdList.get(0)) + ")" + "<train>";
        // Create client socket
        try {
            JServer server = new JServer(messageToPython);
            request.setAttribute("messageAboutTraining", "Переобучение нейронной сети началось");
            server.stop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ToFileCommand toFileCommand = new ToFileCommand();
        return toFileCommand.execute(request);
    }
}