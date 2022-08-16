package ru.rsreu.autoauthor.dao.oracle;

import ru.rsreu.autoauthor.dao.GroupDao;
import ru.rsreu.autoauthor.domain.Author;
import ru.rsreu.autoauthor.domain.Group;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.QueryManager;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Dao for Oracle DataBase for getting data from the category table
 */
public class OracleGroupDao implements GroupDao {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USER_NAME = "user_name";
    public static final String GROUP_NAME = "group_name";
    public static final String GROUP_ID = "group_id";
    public static final String USER_ID = "user_id";
    public static final int FIRST_PARAM_INDEX = 1;
    public static final int SECOND_PARAM_INDEX = 2;

    public OracleGroupDao() {

    }

    /**
     * Gets all records from the category table
     *
     * @return the list of groups
     */
    public List<Group> getAll() {
        List<Group> groupList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.group.selectall"))) {
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt(ID));
                group.setName(rs.getString(NAME));
                groupList.add(group);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return groupList;
    }

    public List<String> getAllGroupIdByLeaderId(String idLeader) {
        List<String> groupIdList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.group.selectidbyleaderid"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(idLeader));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    groupIdList.add(rs.getString(GROUP_ID));
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return groupIdList;
    }

    public List<Group> getAllGroupByLeaderId(String idLeader) {
        List<Group> groupList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.group.selectbyleaderid"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(idLeader));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getInt(GROUP_ID));
                    group.setName(rs.getString(NAME));
                    groupList.add(group);
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return groupList;
    }

    public List<Group> getAllGroupByUserId(String idUser) {
        List<Group> groupList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.group.selectbyuserid"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(idUser));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getInt(GROUP_ID));
                    group.setName(rs.getString(NAME));
                    groupList.add(group);
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return groupList;
    }



    public List<User> getAllMembersByGroupId(List<String> idLeaderList) {
        List<User> userList = new ArrayList<>();
        String idLeaders = "(";
        for (String id : idLeaderList) {
            idLeaders += id + ",";
        }
        idLeaders = idLeaders.substring(0, idLeaders.length() - 1) + ")";
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.usergroup.selectmembersbygroupid") + idLeaders)) {
//            preparedStatement.setString(FIRST_PARAM_INDEX, idLeaders);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(BigInteger.valueOf(rs.getInt(USER_ID)));
                    user.setGroupId(Integer.valueOf(rs.getInt(GROUP_ID)));
                    user.setNickname(rs.getString(USER_NAME));
                    user.setSingleGroupName(rs.getString(GROUP_NAME));
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return userList;
    }

    /**
     * Add new category to category table
     *
     * @param category the name to be added to the category table
     * @return true if category is successfully added
     */
    public boolean save(String category) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.group.add"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, category);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addGroupLeader(String leaderId, String groupId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.usergroup.addleader"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, leaderId);
            preparedStatement.setString(SECOND_PARAM_INDEX, groupId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addMember(String memberId, String groupId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.usergroup.addmember"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, memberId);
            preparedStatement.setString(SECOND_PARAM_INDEX, groupId);
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
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.group.selectlast"))) {
            while (rs.next()) {
                result = rs.getString(ID);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return result;
    }

    /**
     * Delete category from category table
     *
     * @param name the name of the category to be removed
     * @return true if category is successfully deleted
     */
    public boolean delete(String name) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.group.delete"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean kick(String group_id, String user_id) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.usergroup.kick"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, group_id);
            preparedStatement.setString(SECOND_PARAM_INDEX, user_id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
