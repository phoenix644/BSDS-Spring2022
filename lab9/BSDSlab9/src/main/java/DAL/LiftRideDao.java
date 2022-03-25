package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.LiftRide;
import org.apache.commons.dbcp2.*;

public class LiftRideDao {
    private static BasicDataSource dataSource;
    private static LiftRideDao instance = null;
    public static LiftRideDao getInstance() {
        if(instance == null) {
            instance = new LiftRideDao();
        }
        return instance;
    }

    public LiftRideDao() {
        dataSource = DBCPDataSource.getDataSource("skier");
    }



    public List<LiftRide> getSkierswithSkierID(int resortID, String seasonID, String dayID, int skierID) throws SQLException {
        List<LiftRide> LiftRides = new ArrayList<LiftRide>();
        String selectSkiers =

        "SELECT  * FROM skier.LiftRide where resortID=? and seasonID=? and dayID=? and skierID=?;"
                ;
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = dataSource.getConnection();
            selectStmt = connection.prepareStatement(selectSkiers);
            selectStmt.setInt(1, resortID);
            selectStmt.setString(2, seasonID);
            selectStmt.setString(3, dayID);
            selectStmt.setInt(4, skierID);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int rskierID = results.getInt("skierID");
                int rresortID = results.getInt("resortID");
                String rseasonID = results.getString("seasonID");
                String rdayID = results.getString("dayID");
                int rwaitTime = results.getInt("waitTime");
                int rliftID = results.getInt("liftID");
                LiftRide liftRide = new LiftRide(rskierID, rresortID, rseasonID, rdayID, rwaitTime, rliftID);
                LiftRides.add(liftRide);
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
        return LiftRides;
    }

    public List<LiftRide> getSkierswithoutSkierID(int resortID, String seasonID, String dayID) throws SQLException {
        List<LiftRide> LiftRides = new ArrayList<LiftRide>();
        String selectSkiers =

                "SELECT  * FROM skier.LiftRide where resortID=? and seasonID=? and dayID=?;"
                ;
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = dataSource.getConnection();
            selectStmt = connection.prepareStatement(selectSkiers);
            selectStmt.setInt(1, resortID);
            selectStmt.setString(2, seasonID);
            selectStmt.setString(3, dayID);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int rskierID = results.getInt("skierID");
                int rresortID = results.getInt("resortID");
                String rseasonID = results.getString("seasonID");
                String rdayID = results.getString("dayID");
                int rwaitTime = results.getInt("waitTime");
                int rliftID = results.getInt("liftID");
                LiftRide liftRide = new LiftRide(rskierID, rresortID, rseasonID, rdayID, rwaitTime, rliftID);
                LiftRides.add(liftRide);
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
        return LiftRides;
    }


    public List<LiftRide> getSkierVertical(int skierID) throws SQLException {
        List<LiftRide> LiftRides = new ArrayList<LiftRide>();
        String selectSkiers =

                "SELECT  * FROM skier.LiftRide where skierID=?;"
                ;
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = dataSource.getConnection();
            selectStmt = connection.prepareStatement(selectSkiers);
            selectStmt.setInt(1, skierID);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int rskierID = results.getInt("skierID");
                int rresortID = results.getInt("resortID");
                String rseasonID = results.getString("seasonID");
                String rdayID = results.getString("dayID");
                int rwaitTime = results.getInt("waitTime");
                int rliftID = results.getInt("liftID");
                LiftRide liftRide = new LiftRide(rskierID, rresortID, rseasonID, rdayID, rwaitTime, rliftID);
                LiftRides.add(liftRide);
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
        return LiftRides;
    }



    public void createLiftRide(LiftRide newLiftRide) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String insertQueryStatement = "INSERT INTO LiftRide (skierId, resortId, seasonId, dayId, waitTime, liftId) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(insertQueryStatement);
            preparedStatement.setInt(1, newLiftRide.getSkierID());
            preparedStatement.setInt(2, newLiftRide.getResortID());
            preparedStatement.setString(3, newLiftRide.getSeasonID());
            preparedStatement.setString(4, newLiftRide.getDayID());
            preparedStatement.setInt(5, newLiftRide.getWaitTime());
            preparedStatement.setInt(6, newLiftRide.getLiftID());

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