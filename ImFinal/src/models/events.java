/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author user
 */
public class events {
    
    private String event_name;
    private String event_category;
    private String event_date;
    private String end_time;
    private String location;
      private String status;

    public events(String event_name, String event_category, String event_date, String end_time, String location, String status) {
        this.event_name = event_name;
        this.event_category = event_category;
        this.event_date = event_date;
        this.end_time = end_time;
        this.location = location;
        this.status = status;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_category() {
        return event_category;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setEvent_category(String event_category) {
        this.event_category = event_category;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
    
    
    
    
}
