package ru.rsreu.autoauthor.dao.oracle;

import ru.rsreu.autoauthor.domain.File;
import ru.rsreu.autoauthor.domain.Model;
import ru.rsreu.autoauthor.resource.QueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleModelDao {
    public static final String ID = "id";
    public static final String GROUP = "group_id";
    public static final String STATUS = "status";
    public static final int FIRST_PARAM_INDEX = 1;
    public static final int SECOND_PARAM_INDEX = 2;
    public static final int THIRD_PARAM_INDEX = 3;

    public OracleModelDao() {

    }

    public Model getByGroupId(String groupId) {
        Model model = new Model();
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.model.selectbygroupid"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, groupId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    model.setId(rs.getInt(ID));
                    model.setGroupId(rs.getInt(GROUP));
                    model.setStatus(rs.getString(STATUS));
                }
            }
        } catch (SQLException e) {
            // LOGGER.error(e);
        }
        return model;
    }

    public boolean setStatusTrain(String groupId) {
        Connection connection = OracleDao.instance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryManager.getProperty("query.model.setstatustrain"))) {
            preparedStatement.setString(FIRST_PARAM_INDEX, groupId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
