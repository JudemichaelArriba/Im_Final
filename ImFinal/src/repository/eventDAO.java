/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import imfinal.HomeController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import models.archiveEvents;
import models.events;
import models.eventsTbl;

/**
 *
 * @author user
 */
public class eventDAO {

    private Connection connect;
    Alert alert;
    private PreparedStatement prepare;
    private ResultSet result;
    HomeController home = new HomeController();

  public boolean add(events event) {
    String add = "INSERT INTO events ("
                + "event_name, event_category, event_date, end_time, location, status"
                + ") VALUES ("
                + "CONCAT(UPPER(SUBSTRING(?, 1, 1)), LOWER(SUBSTRING(?, 2))), "
                + "CONCAT(UPPER(SUBSTRING(?, 1, 1)), LOWER(SUBSTRING(?, 2))), "
                + "?, ?, ?, ?"
                + ")";

    connect = mysqlConnector.connectDB();
    try {
        prepare = connect.prepareStatement(add);

       
        prepare.setString(1, event.getEvent_name());
        prepare.setString(2, event.getEvent_name()); 

        prepare.setString(3, event.getEvent_category()); 
        prepare.setString(4, event.getEvent_category()); 

        // Set other fields without transformation
        prepare.setString(5, event.getEvent_date()); 
        prepare.setString(6, event.getEnd_time()); 
        prepare.setString(7, event.getLocation()); 
        prepare.setString(8, event.getStatus()); 

        // Execute the update query
        prepare.executeUpdate();

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informative Message");
        alert.setHeaderText(null);
        alert.setContentText("Event Added!");
        alert.showAndWait();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

    public int getEventCount() {
        String sql = "SELECT COUNT(*) AS event_count FROM upcoming_events";
        int count = 0;
        try {
            connect = mysqlConnector.connectDB();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("event_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<eventsTbl> getAllEvents() {
        List<eventsTbl> eventList = new ArrayList<>();

        String sql = "SELECT * FROM upcoming_events";
        Connection connect = null;
        PreparedStatement prepare = null;
        ResultSet result = null;

        try {
            connect = mysqlConnector.connectDB();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                eventsTbl ev = new eventsTbl(
                        result.getString("eventid"),
                        result.getString("event_name"),
                        result.getString("event_category"),
                        result.getString("event_date"),
                        result.getString("end_time"),
                        result.getString("location"),
                        result.getString("status")
                );
                eventList.add(ev);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public boolean update(int eventId, events updatedEvent) {
        String updateSQL = "UPDATE events SET event_name = ?, event_category = ?, event_date = ?, end_time = ?, location = ?, status = ? WHERE eventid = ?";

        try (Connection conn = mysqlConnector.connectDB(); PreparedStatement ps = conn.prepareStatement(updateSQL)) {

            ps.setString(1, updatedEvent.getEvent_name());
            ps.setString(2, updatedEvent.getEvent_category());
            ps.setString(3, updatedEvent.getEvent_date());
            ps.setString(4, updatedEvent.getEnd_time());
            ps.setString(5, updatedEvent.getLocation());
            ps.setString(6, updatedEvent.getStatus());
            ps.setInt(7, eventId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
        public int getUserCount() {
        String sql = "SELECT COUNT(*) AS user_count FROM users";
        int count = 0;
        try {
            connect = mysqlConnector.connectDB();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("user_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    
    
    
      public int getArchiveCount() {
        String sql = "SELECT COUNT(*) AS archive_count FROM archive";
        int count = 0;
        try {
            connect = mysqlConnector.connectDB();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("archive_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    
    
    
    
    
        public List<archiveEvents> getAllArchive() {
        List<archiveEvents> eventList = new ArrayList<>();

        String sql = "SELECT * FROM event_archive";
        Connection connect = null;
        PreparedStatement prepare = null;
        ResultSet result = null;

        try {
            connect = mysqlConnector.connectDB();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                archiveEvents archive = new archiveEvents(
                result.getString("archive_id"),   
                result.getString("event_id"),     
                result.getString("event_name"),  
                result.getString("event_category"),
                result.getString("event_date"),  
                result.getString("end_time"),    
                result.getString("location"),    
                result.getString("status")   
                );
                eventList.add(archive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public boolean deleteMultiple(List<Integer> eventIds) {
        String sql = "DELETE FROM events WHERE eventid = ?";
        try (Connection conn = mysqlConnector.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (int eventId : eventIds) {
                pstmt.setInt(1, eventId);
                pstmt.addBatch();
            }

            int[] rowsAffected = pstmt.executeBatch();

            conn.commit();

            for (int rows : rowsAffected) {
                if (rows == 0) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
