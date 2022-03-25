package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.LiftRide;
import Model.Resorts;
import org.apache.commons.dbcp2.*;

public class ResortsDao {
    private static BasicDataSource dataSource;

    private static ResortsDao instance = null;
    public static ResortsDao getInstance() {
        if(instance == null) {
            instance = new ResortsDao();
        }
        return instance;
    }

    public ResortsDao() {
        dataSource = DBCPDataSource.getDataSource("resorts");
    }


    public List<Resorts> getResorts(int resortID, String seasonID, String dayID) throws SQLException {
        List<Resorts> ResortsList = new ArrayList<Resorts>();
        String selectResorts =

                "SELECT * FROM resorts.Resorts where resortsID=?;"
                ;

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = dataSource.getConnection();
            selectStmt = connection.prepareStatement(selectResorts);
            selectStmt.setInt(1, resortID);
            results = selectStmt.executeQuery();
            while(results.next()) {

                int rresortsID = results.getInt("ResortsID");
                String ryear = results.getString("seasonID");
                Resorts resorts = new Resorts(rresortsID, ryear);
                ResortsList.add(resorts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return ResortsList;
    }

    public void createResorts(Resorts newResorts) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String insertQueryStatement = "INSERT INTO Resorts (resortsId, seasonId) " +
                "VALUES (?,?)";
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