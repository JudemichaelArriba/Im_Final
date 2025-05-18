/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class eventsTbl {

    private final SimpleStringProperty event_name;
    private final SimpleStringProperty event_category;
    private final SimpleStringProperty event_date;
    private final SimpleStringProperty end_time;
    private final SimpleStringProperty location;
    private final SimpleStringProperty status;
    private final SimpleStringProperty event_id; 

    // Modified constructor to include event_id
    public eventsTbl(String event_id, String event_name, String event_category, 
            String event_date, String end_time, String location, String status) {
        this.event_id = new SimpleStringProperty(event_id); // Initialize event_id
        this.event_name = new SimpleStringProperty(event_name);
        this.event_category = new SimpleStringProperty(event_category);
        this.event_date = new SimpleStringProperty(event_date);
        this.end_time = new SimpleStringProperty(end_time);
        this.location = new SimpleStringProperty(location);
        this.status = new SimpleStringProperty(status);
    }
    
    
     public String getEvent_id() {
        return event_id.get();
    }


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

    public SimpleStringProperty event_nameProperty() {
        return event_name;
    }

    public SimpleStringProperty event_categoryProperty() {
        return event_category;
    }

    public SimpleStringProperty event_dateProperty() {
        return event_date;
    }

    public SimpleStringProperty end_timeProperty() {
        return end_time;
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }
    public SimpleStringProperty event_idProperty() {
        return event_id;
    }
}
