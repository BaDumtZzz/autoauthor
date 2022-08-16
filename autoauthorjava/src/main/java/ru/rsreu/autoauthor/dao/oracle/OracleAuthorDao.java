package ru.rsreu.autoauthor.dao.oracle;

import ru.rsreu.autoauthor.dao.AuthorDao;
import ru.rsreu.autoauthor.domain.Author;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.QueryManager;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OracleAuthorDao implements AuthorDao {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GROUP = "group_id";
    public static final int FIRST_PARAM_INDEX = 1;
    public static final int SECOND_PARAM_INDEX = 2;

    public OracleAuthorDao() {

    }

    /**
     * Gets all records from the category table
     * @return the list of groups
     */
    public List<Author> getAll() {
        List<Author> authorList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.author.selectall"))) {
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt(ID));
                author.setName(rs.getString(NAME));
                author.setGroup(rs.getInt(GROUP));
                authorList.add(author);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return authorList;
    }

    public List<Author> getByGroupId(List<String> groupIdList) {
        List<Author> authorList = new ArrayList<>();
        String idGroups = "(";
        for (String id : groupIdList) {
            idGroups += id + ",";
        }
        idGroups = idGroups.substring(0, idGroups.length() - 1) + ")";
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.author.selectbyorgid") + idGroups)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(Integer.valueOf(rs.getInt(ID)));
                    author.setName(rs.getString(NAME));
                    author.setGroup(Integer.valueOf(rs.getString(GROUP)));
                    authorList.add(author);
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return authorList;
    }

    public Author getById(String id) {
        Author author = new Author();
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.author.selectbyid"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(id));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    author.setId(Integer.valueOf(rs.getInt(ID)));
                    author.setName(rs.getString(NAME));
                    author.setGroup(Integer.valueOf(rs.getString(GROUP)));
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return author;
    }

    /**
     * Add new category to category table
     * @param author the name to be added to the category table
     * @return true if category is successfully added
     */
    public boolean save(Author author) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.author.add"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, author.getName());
            preparedStatement.setString(SECOND_PARAM_INDEX, String.valueOf(author.getGroup()));
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.author.delete"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public String getLastId() {
        String result = "";
        Connection connection = OracleDao.instance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.author.selectlast"))) {
            while (rs.next()) {
                result = rs.getString(ID);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return result;
    }
}
