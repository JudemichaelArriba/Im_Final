/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class archiveEvents {
    
    
  
    
    
        private final SimpleStringProperty event_name;
    private final SimpleStringProperty event_category;
    private final SimpleStringProperty event_date;
    private final SimpleStringProperty end_time;
    private final SimpleStringProperty location;
    private final SimpleStringProperty status;
    private final SimpleStringProperty event_id; 
     private final SimpleStringProperty archive_id; 

    public archiveEvents(String archive_id, String event_id, String event_name, 
                         String event_category, String event_date, String end_time, 
                         String location, String status) {
        this.archive_id = new SimpleStringProperty(archive_id);
        this.event_id = new SimpleStringProperty(event_id);
        this.event_name = new SimpleStringProperty(event_name);
        this.event_category = new SimpleStringProperty(event_category);
        this.event_date = new SimpleStringProperty(event_date);
        this.end_time = new SimpleStringProperty(end_time);
        this.location = new SimpleStringProperty(location);
        this.status = new SimpleStringProperty(status);
    }

    
    
    
    
     public StringProperty event_nameProperty() {
        return event_name;
    }

    public StringProperty event_categoryProperty() {
        return event_category;
    }

    public StringProperty event_dateProperty() {
        return event_date;
    }

    public StringProperty end_timeProperty() {
        return end_time;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty event_idProperty() {
        return event_id;
    }

    public StringProperty archive_idProperty() {
        return archive_id;
    }

    // Optional: getters for actual string values
    public String getEvent_name() {
        return event_name.get();
    }

    public String getEvent_category() {
        return event_category.get();
    }

    public String getEvent_date() {
        return event_date.get();
    }

    public String getEnd_time() {
        return end_time.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getEvent_id() {
        return event_id.get();
    }

    public String getArchive_id() {
        return archive_id.get();
    }

    
    
    
    
    
    
}
