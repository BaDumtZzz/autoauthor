package ru.rsreu.autoauthor.dao.oracle;

import ru.rsreu.autoauthor.dao.UserDao;
import ru.rsreu.autoauthor.domain.User;
import ru.rsreu.autoauthor.resource.QueryManager;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OracleUserDao implements UserDao {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String GROUP = "group_id";
    public static final String GROUP_NAME = "group_name";
    public static final String STATUS = "status";
    public static final int FIRST_PARAM_INDEX = 1;
    public static final int SECOND_PARAM_INDEX = 2;
    public static final int THIRD_PARAM_INDEX = 3;
    public static final int FOURTH_PARAM_INDEX = 4;
    public static final int FIFTH_PARAM_INDEX = 5;

    public OracleUserDao() {

    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
//        Connection connection = OracleDao.instance().getConnection();
//        try (Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.user.selectall"))) {
//            while (rs.next()) {
//                User user = new User();
//                user.setId(BigInteger.valueOf(rs.getInt(ID)));
//                user.setNickname(rs.getString(NAME));
//                user.setPassword(rs.getString(PASSWORD));
//                user.setEmail(rs.getString(EMAIL));
//                user.setRole(rs.getString(ROLE));
//                user.setGroup(Integer.valueOf(rs.getString(GROUP)));
//                user.setStatus(rs.getString(STATUS));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            // LOGGER.error(e);
//        }
        return users;
    }

    public List<User> getAllWithOrgName() {
        List<User> users = new ArrayList<>();
        List<Integer> idList= new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.user.selectallid"))) {
            while (rs.next()) {
                idList.add(rs.getInt(ID));
            }
        }catch (SQLException e) {
            System.out.println("kek");
            // LOGGER.error(e);
        }
        for (Integer userId:idList) {
            User user = new User();
            try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.selectbyidwithorgname"))) {
                preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(userId));
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    int count = 0;
                    List<String> roleList = new ArrayList<>();
                    List<Integer> groupIdList = new ArrayList<>();
                    List<String> groupList = new ArrayList<>();
                    while (rs.next()) {
                        if (count == 0) {
                            user.setId(BigInteger.valueOf(rs.getInt(ID)));
                            user.setNickname(rs.getString(NAME));
                            user.setPassword(rs.getString(PASSWORD));
                            user.setEmail(rs.getString(EMAIL));
                            roleList.add(rs.getString(ROLE));
                            groupIdList.add(Integer.valueOf(rs.getString(GROUP)));
                            groupList.add(rs.getString(GROUP_NAME));
                            user.setStatus(rs.getString(STATUS));
                            count++;
                        } else {
                            roleList.add(rs.getString(ROLE));
                            groupIdList.add(Integer.valueOf(rs.getString(GROUP)));
                            groupList.add(rs.getString(GROUP_NAME));
                        }
                    }

                    user.setRole(roleList);
                    user.setGroup(groupIdList);
                    user.setGroupName(groupList);
                    users.add(user);
                }
            } catch (SQLException e) {
                // LOGGER.error(e);
            }
        }


        return users;
    }



    public Optional<User> getById(String id) {
        User user = new User();
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.selectbyidwithorgname"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, String.valueOf(id));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                int count = 0;
                List<String> roleList = new ArrayList<>();
                List<Integer> groupIdList = new ArrayList<>();
                List<String> groupList = new ArrayList<>();
                while (rs.next() && (count == 0)) {
                    user.setId(BigInteger.valueOf(rs.getInt(ID)));
                    user.setNickname(rs.getString(NAME));
                    user.setPassword(rs.getString(PASSWORD));
                    user.setEmail(rs.getString(EMAIL));
                    roleList.add(rs.getString(ROLE));
                    groupIdList.add(Integer.valueOf(rs.getString(GROUP)));
                    groupList.add(rs.getString(GROUP_NAME));
                    user.setStatus(rs.getString(STATUS));
                    count++;
                }
                while (rs.next()) {
                    roleList.add(rs.getString(ROLE));
                    groupIdList.add(Integer.valueOf(rs.getString(GROUP)));
                    groupList.add(rs.getString(GROUP_NAME));
                }
                user.setRole(roleList);
                user.setGroup(groupIdList);
                user.setGroupName(groupList);
            }
        }  catch (SQLException e) {
            // LOGGER.error(e);
        }
        return Optional.of(user);
    }

    public boolean save(User user) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.add"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, user.getNickname());
            preparedStatement.setString(SECOND_PARAM_INDEX, user.getEmail());
            preparedStatement.setString(THIRD_PARAM_INDEX, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(String userId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.delete"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean block(String userId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.block"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unlock(String userId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.unlock"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserInformation(String id, String email, String role) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.updateuserinformation"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, email);
            preparedStatement.setString(SECOND_PARAM_INDEX, role);
            preparedStatement.setString(THIRD_PARAM_INDEX, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setOfflineStatus(String userId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.setofflinestatus"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setOnlineStatus(String userId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.user.setonlinestatus"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, userId);
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
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.user.selectlast"))) {
            while (rs.next()) {
                result = rs.getString(ID);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return result;
    }
}
