package ru.rsreu.autoauthor.command.admin;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import ru.rsreu.autoauthor.command.ActionCommand;
import ru.rsreu.autoauthor.dao.oracle.OracleAuthorDao;
import ru.rsreu.autoauthor.dao.oracle.OracleFileDao;
import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.Author;
import ru.rsreu.autoauthor.resource.ConfigurationManager;
import ru.rsreu.autoauthor.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class UploadAuthorFileCommand implements ActionCommand {
    public static final String AUTHOR = "selected_author";
    public static final String NEW_AUTHOR_NAME = "new_author_name";
    public static final String NEW_AUTHOR_GROUP_ID = "new_author_group_id";
    public static final String GROUP = "group";

    @Override
    public String execute(HttpServletRequest request) {
        ServletFileUpload uploader = (ServletFileUpload) request.getSession().getServletContext().getAttribute("uploader");
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            Integer authorId = 0;
            String groupId = "";
            Integer newAuthorGroupId = 0;
            String newAuthorName = "";
            boolean newAuthorFlag = false;
            Author newAuthor = new Author();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                InputStream stream = fileItem.getInputStream();
                if (fileItem.getFieldName().equals(AUTHOR)) {
                    authorId = Integer.valueOf(Streams.asString(stream));
                } else if (fileItem.getFieldName().equals(NEW_AUTHOR_GROUP_ID)) {
                    newAuthorGroupId = Integer.valueOf(Streams.asString(stream));
                } else if (fileItem.getFieldName().equals(NEW_AUTHOR_NAME)) {
                    newAuthorName = String.valueOf(Streams.asString(stream));
                    OracleAuthorDao authorDao = (OracleAuthorDao) request.getSession().getServletContext().getAttribute("authorDao");

                    newAuthor.setName(newAuthorName);
                    newAuthor.setGroup(newAuthorGroupId);

                    if (!authorDao.save(newAuthor)) {
                        request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
                        ToAddFileCommand toAddFileCommand = new ToAddFileCommand();
                        return toAddFileCommand.execute(request);
                    }
                    newAuthor.setId(Integer.valueOf(authorDao.getLastId()));
                    if(!newAuthor.getName().isEmpty()){
                        newAuthorFlag = true;
                    }
                } else if (fileItem.getFieldName().equals("fileName")) {
                    if (!fileItem.getName().endsWith(".txt")){
                        request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addfileextensionerror"));
                        ToAddFileCommand toAddFileCommand = new ToAddFileCommand();
                        return toAddFileCommand.execute(request);
                    }
                    OracleAuthorDao authorDao = (OracleAuthorDao) request.getSession().getServletContext().getAttribute("authorDao");
                    Author author;
                    if (newAuthorFlag){
                        author = newAuthor;
                        groupId = String.valueOf(newAuthor.getGroup());
                    } else {
                        author = authorDao.getById(String.valueOf(authorId));
                        groupId = author.getGroup().toString();

                    }

                    String filePath = request.getServletContext().getAttribute("FILES_DIR") + File.separator + groupId + File.separator + "train";
                    if (!Files.exists(Path.of(filePath))) {
                        Files.createDirectories(Path.of(filePath));
                    }

                    int BUFF_SIZE = 100000;
                    byte[] buffer = new byte[BUFF_SIZE];

                    FileOutputStream fout = new FileOutputStream(filePath + File.separator + "(" + author.getName() + ")" + fileItem.getName());
                    while (true) {
                        synchronized (buffer) {
                            int amountRead = stream.read(buffer);
                            if (amountRead == -1) {
                                break;
                            }
                            fout.write(buffer, 0, amountRead);
                        }
                    }
                    String kek = fileItem.getName();


                    OracleFileDao fileDao = (OracleFileDao) request.getSession().getServletContext().getAttribute("fileDao");
                    ru.rsreu.autoauthor.domain.File newFile = new ru.rsreu.autoauthor.domain.File();
                    newFile.setName("(" + author.getName() + ")" + fileItem.getName());
                    newFile.setAuthor(author.getId());

                    if (!fileDao.save(newFile)) {
                        request.setAttribute("errorAddUserMessage", MessageManager.getProperty("message.addusererror"));
                        return ConfigurationManager.getProperty("path.page.add-file");
                    }
                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ToFileCommand toFileCommand = new ToFileCommand();
        return toFileCommand.execute(request);
    }
}