package ru.rsreu.autoauthor.dao.oracle;

import ru.rsreu.autoauthor.dao.FileDao;
import ru.rsreu.autoauthor.domain.File;
import ru.rsreu.autoauthor.resource.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleFileDao implements FileDao {
    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String NAME = "name";
    public static final String AUTHOR_NAME = "author_name";
    public static final String GROUP = "group_id";
    public static final int FIRST_PARAM_INDEX = 1;
    public static final int SECOND_PARAM_INDEX = 2;

    public OracleFileDao() {

    }

    /**
     * Gets all records from the category table
     * @return the list of groups
     */
    public List<File> getAll() {
        List<File> fileList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.file.selectall"))) {
            while (rs.next()) {
                File file = new File();
                file.setId(rs.getInt(ID));
                file.setName(rs.getString(NAME));
                file.setAuthor(rs.getInt(AUTHOR));
                file.setAuthorName(rs.getString(AUTHOR_NAME));
                file.setGroup(rs.getInt(GROUP));
                fileList.add(file);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return fileList;
    }

    public List<File> getByGroupId(List<String> groupIdList) {
        List<File> fileList = new ArrayList<>();
        String idGroups = "(";
        for (String id : groupIdList) {
            idGroups += id + ",";
        }
        idGroups = idGroups.substring(0, idGroups.length() - 1) + ")";
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.file.selectallbyorgid") + idGroups)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    File file = new File();
                    file.setId(rs.getInt(ID));
                    file.setName(rs.getString(NAME));
                    file.setAuthor(rs.getInt(AUTHOR));
                    file.setAuthorName(rs.getString(AUTHOR_NAME));
                    file.setGroup(rs.getInt(GROUP));
                    fileList.add(file);
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return fileList;
    }

    /**
     * Add new category to category table
     * @param file the name to be added to the category table
     * @return true if category is successfully added
     */
    public boolean save(File file) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.file.add"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(file.getAuthor()));
            preparedStatement.setString(SECOND_PARAM_INDEX, file.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete category from category table
     * @param name the name of the category to be removed
     * @return true if category is successfully deleted
     */
    public boolean delete(String name) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.file.delete"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
