package DAL;

import java.sql.*;

import Model.LiftRide;
import Model.Resorts;
import org.apache.commons.dbcp2.*;

public class ResortsDao {
    private static BasicDataSource dataSource;

    public ResortsDao() {
        dataSource = DBCPDataSource.getDataSource();
    }

    public void createResorts(Resorts newResorts) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String insertQueryStatement = "INSERT INTO Resorts (resortId, seasonId) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(insertQueryStatement);
            preparedStatement.setInt(1, newResorts.getResortID());
            preparedStatement.setString(2, newResorts.getYear());
            // execute insert SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}