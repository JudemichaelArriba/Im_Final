/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package imfinal;

import static com.mysql.cj.util.SaslPrep.prepare;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import repository.mysqlConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import models.events;
import models.eventsTbl;
import repository.eventDAO;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EventsController implements Initializable {

    @FXML
    private Pane crudPane;
    
        @FXML
    private DatePicker datePicker;
    
    
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField SearchTxt;
    @FXML
    private TextField categoryTxt1;
    @FXML
    private TextField timeTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;
    @FXML
    private ComboBox<String> statusBx;
    @FXML
    private TableView<eventsTbl> table1;

    @FXML
    private TableColumn<eventsTbl, String> colName;
    @FXML
    private TableColumn<eventsTbl, String> colCategory;
    @FXML
    private TableColumn<eventsTbl, String> colDate;
    @FXML
    private TableColumn<eventsTbl, String> colEndTime;
    @FXML
    private TableColumn<eventsTbl, String> colLocation;
    @FXML
    private TableColumn<eventsTbl, String> colStatus;
    private Connection connect;
    Alert alert;
    private PreparedStatement prepare;
    private ResultSet result;

    private ObservableList<eventsTbl> eventList = FXCollections.observableArrayList();

    @FXML
    private void populateStatusComboBox() {
        ObservableList<String> statusOptions = FXCollections.observableArrayList("Active", "Inactive");
        statusBx.setItems(statusOptions);
        statusBx.setPromptText("Select Status");
    }

    @FXML
    public void addEvent(ActionEvent event) {
        
        
        
        eventDAO eventdao = new eventDAO();

connect = mysqlConnector.connectDB();

try {
    if (nameTxt.getText().isEmpty() && datePicker.getValue() == null && categoryTxt1.getText().isEmpty()
            && timeTxt.getText().isEmpty() && locationTxt.getText().isEmpty()
            && (statusBx.getValue() == null || statusBx.getValue().isEmpty())) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all the fields");
        alert.showAndWait();

    } else if (nameTxt.getText().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill  the name field");
        alert.showAndWait();
    } else if (datePicker.getValue() == null) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill  the Date field");
        alert.showAndWait();
    } else if (categoryTxt1.getText().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill  the Category field");
        alert.showAndWait();
    } else if (timeTxt.getText().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill  the Time field");
        alert.showAndWait();
    } else if (locationTxt.getText().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill  the Location field");
        alert.showAndWait();
    } else if (statusBx.getValue() == null || statusBx.getValue().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all the Location field");
        alert.showAndWait();
    } else {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = datePicker.getValue().format(formatter);

      
        String nameCheck = "SELECT * FROM events WHERE event_name = ?";
        prepare = connect.prepareStatement(nameCheck);
        prepare.setString(1, nameTxt.getText());
        result = prepare.executeQuery();

        if (result.next()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Event already exists.");
            alert.showAndWait();

        } else {

            String check = "SELECT event_date FROM events WHERE event_date = '"
                    + formattedDate + " " + dateTxt.getText() + "'";
            prepare = connect.prepareStatement(check);
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Theere are event on that date please enter a different date");
                alert.showAndWait();

            } else {

                String count = "SELECT COUNT(*) FROM events";

                events events = new events(nameTxt.getText(), categoryTxt1.getText(), formattedDate +  " "+ dateTxt.getText() , timeTxt.getText(), locationTxt.getText(), statusBx.getValue());

                eventdao.add(events);
                loadEventData();
                nameTxt.setText("");
                categoryTxt1.setText("");
                dateTxt.setText("");
                timeTxt.setText("");

            }
        }
    }

} catch (Exception e) {
    e.printStackTrace();
}
        
        
  
        
        
        


    }

    private void loadEventData() {
        try {

            eventList.clear();
            eventList.addAll(new eventDAO().getAllEvents());
            table1.setItems(eventList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showSelectedRowData() {

        eventsTbl selectedEvent = table1.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {

            nameTxt.setText(selectedEvent.getEvent_name());
            categoryTxt1.setText(selectedEvent.getEvent_category());
          
            timeTxt.setText(selectedEvent.getEnd_time());
            locationTxt.setText(selectedEvent.getLocation());
            statusBx.setValue(selectedEvent.getStatus());
        } else {
            nameTxt.setText("");
            categoryTxt1.setText("");
            datePicker.setValue(null);
            timeTxt.setText("");
            locationTxt.setText("");
            statusBx.setValue("");
        }

    }

    @FXML
    private void searchEvents() {
        String searchText = SearchTxt.getText().toLowerCase();

        ObservableList<eventsTbl> filteredList = FXCollections.observableArrayList();

        for (eventsTbl event : eventList) {

            if (event.getEvent_name().toLowerCase().contains(searchText)
                    || event.getEvent_category().toLowerCase().contains(searchText)
                    || event.getEvent_date().toLowerCase().contains(searchText)
                    || event.getEnd_time().toLowerCase().contains(searchText)
                    || event.getLocation().toLowerCase().contains(searchText)
                    || event.getStatus().toLowerCase().contains(searchText)) {

                filteredList.add(event);
            }
        }

        table1.setItems(filteredList);
    }

    @FXML
    public void updateEvent(ActionEvent event) {

        eventsTbl selectedEvent = table1.getSelectionModel().getSelectedItem();

        if (selectedEvent == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select an event to update.");
            alert.showAndWait();
            return;
        }
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String formattedDate = datePicker.getValue().format(formatter);
        if (nameTxt.getText().isEmpty() || formattedDate.isEmpty() || categoryTxt1.getText().isEmpty()
                || timeTxt.getText().isEmpty() || locationTxt.getText().isEmpty() || statusBx.getValue() == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields before updating.");
            alert.showAndWait();
            return;
        }
String eventDateTime = formattedDate + " " + dateTxt.getText();
        events updatedEvent = new events(
                nameTxt.getText(),
                categoryTxt1.getText(),
           eventDateTime,
                timeTxt.getText(),
                locationTxt.getText(),
                statusBx.getValue()
        );

        eventDAO eventdao = new eventDAO();
        try {

            int eventId = Integer.parseInt(selectedEvent.getEvent_id());
            boolean success = eventdao.update(eventId, updatedEvent);
            if (success) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Event updated successfully.");
                alert.showAndWait();
                loadEventData();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update the event.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while updating the event.");
            alert.showAndWait();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
@FXML
public void deleteEvent(ActionEvent event) {
  
    ObservableList<eventsTbl> selectedEvents = table1.getSelectionModel().getSelectedItems();

    if (selectedEvents.isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select at least one event to delete.");
        alert.showAndWait();
        return;
    }

   
    Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
    confirmAlert.setTitle("Confirm Deletion");
    confirmAlert.setHeaderText(null);
    confirmAlert.setContentText("Are you sure you want to delete the selected event(s)?");

    confirmAlert.showAndWait().ifPresent(response -> {
        if (response == javafx.scene.control.ButtonType.OK) {
            eventDAO eventdao = new eventDAO();
            try {
                List<Integer> eventIds = new ArrayList<>();

               
                for (eventsTbl selectedEvent : selectedEvents) {
                    int eventId = Integer.parseInt(selectedEvent.getEvent_id());
                    eventIds.add(eventId);
                }

               
                boolean success = eventdao.deleteMultiple(eventIds);

                if (success) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Selected event(s) deleted successfully.");
                    alert.showAndWait();
                    loadEventData(); 
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to delete some events.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while deleting the events.");
                alert.showAndWait();
            }
        }
    });
}
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateStatusComboBox();

        colName.setCellValueFactory(cellData -> cellData.getValue().event_nameProperty());
        colCategory.setCellValueFactory(cellData -> cellData.getValue().event_categoryProperty());
        colDate.setCellValueFactory(cellData -> cellData.getValue().event_dateProperty());
        colEndTime.setCellValueFactory(cellData -> cellData.getValue().end_timeProperty());
        colLocation.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        eventDAO eventdao = new eventDAO();

        loadEventData();

        table1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showSelectedRowData();
        });

        table1.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        table1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {

                eventsTbl selectedEvent = table1.getSelectionModel().getSelectedItem();
                if (selectedEvent != null) {
                    int selectedIndex = table1.getSelectionModel().getSelectedIndex();

                    if (selectedIndex != -1) {
                        table1.getSelectionModel().clearSelection(selectedIndex);
                    } else {
                        table1.getSelectionModel().select(selectedEvent);
                    }
                }
            }
        });

        SearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            searchEvents();
        });

    }

}
