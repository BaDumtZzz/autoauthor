package ru.rsreu.autoauthor.dao.oracle;

import ru.rsreu.autoauthor.dao.RoleDao;
import ru.rsreu.autoauthor.resource.QueryManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleRoleDao implements RoleDao {

    public OracleRoleDao() {
    }

    public static final String ROLE = "name";

    public List<String> getAll() {
        List<String> roleList = new ArrayList<>();
        Connection connection = OracleDao.instance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(QueryManager.getProperty("query.role.selectall"))) {
            while (rs.next()) {
                String role = rs.getString(ROLE);
                roleList.add(role);
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return roleList;
    }
}
