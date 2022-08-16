package ru.rsreu.autoauthor.command;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import ru.rsreu.autoauthor.command.admin.ToAddFileCommand;
import ru.rsreu.autoauthor.command.admin.ToFileCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleFileDao;
import ru.rsreu.autoauthor.dao.oracle.OracleGroupDao;
import ru.rsreu.autoauthor.dao.oracle.OracleModelDao;
import ru.rsreu.autoauthor.handler.MapProbabilityComparator;
import ru.rsreu.autoauthor.handler.ResultDeterminingAuthorHandler;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;
import ru.rsreu.autoauthor.result.JServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class UploadUnknownFileCommand implements ActionCommand {
    public static final String GROUP = "new_group";
    public static final String AUTHOR = "new_author";
    public static final String PATH = "new_path";

    @Override
    public String execute(HttpServletRequest request) {
        ServletFileUpload uploader = (ServletFileUpload) request.getSession().getServletContext().getAttribute("uploader");
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            String groupId = "";
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                InputStream stream = fileItem.getInputStream();
                if (fileItem.getFieldName().equals(GROUP)) {
                    groupId = Streams.asString(stream);

                    OracleModelDao modelDao = (OracleModelDao) request.getSession().getServletContext().getAttribute("modelDao");

                    if (modelDao.getByGroupId(groupId).getStatus().equals("training")){
                        ToIdentificationCommand toIdentificationCommand = new ToIdentificationCommand();
                        return toIdentificationCommand.execute(request);
                    }

                } else if (fileItem.getFieldName().equals("fileName")) {
                    if (!fileItem.getName().endsWith(".txt")){
                        request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addfileextensionerror"));
                        ToIdentificationCommand toIdentificationCommand = new ToIdentificationCommand();
                        return toIdentificationCommand.execute(request);
                    }
                    String filePath = request.getServletContext().getAttribute("FILES_DIR") + File.separator + groupId;

                    int BUFF_SIZE = 100000;
                    byte[] buffer = new byte[BUFF_SIZE];

                    FileOutputStream fout = new FileOutputStream(filePath + File.separator + fileItem.getName());

                    while (true) {
                        synchronized (buffer) {
                            int amountRead = stream.read(buffer);
                            if (amountRead == -1) {
                                break;
                            }
                            fout.write(buffer, 0, amountRead);
                        }
                    }
                    fout.close();
                    String messageToPython = "(" + groupId + ")" + "[" + fileItem.getName() + "]"  + "<identify>";
                    // Create client socket
                    try {
                        JServer server = new JServer(messageToPython);
                        int i = 0;
                        Thread.sleep(2000);
                        while (server.handler.message.isEmpty()) {
                            Thread.sleep(200);
                        }
                        String resultFromPython = server.handler.message;
                        Map<String, Double> authorProbabilityMap = MapProbabilityComparator.
                                sortByValue(ResultDeterminingAuthorHandler.getResultDetermingAuthor(resultFromPython));
                        request.setAttribute("authorProbabilityMap", authorProbabilityMap);
                        server.stop();

                        Path path = Path.of(filePath + File.separator + fileItem.getName());
                        Files.delete(path);

                        File folder = new File(filePath);
                        List<File> files = Arrays.asList(folder.listFiles());
                        for(File file: files){
                            if(file.isFile()){
                                file.delete();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        OracleGroupDao groupDao = (OracleGroupDao) request.getSession().getServletContext().getAttribute("groupDao");;
        List<String> currentRole = (List<String>) session.getAttribute("role");
        if (currentRole.contains("admin")){
            request.setAttribute("groups", groupDao.getAll());
        } else {
            request.setAttribute("groups", groupDao.getAllGroupByLeaderId(String.valueOf(session.getAttribute("id"))));
        }

        return ConfigurationManager.getProperty("path.page.identification-result");
    }
}