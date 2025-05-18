/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package imfinal;

import com.mysql.cj.xdevapi.Table;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.archiveEvents;
import models.eventsTbl;
import repository.eventDAO;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ArchiveController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
  @FXML
    private TableView<archiveEvents> archiveTable;
 

    @FXML
    private TableColumn<archiveEvents, String> eventName;
    @FXML
    private TableColumn<archiveEvents, String> eventCategoryColumn;
    @FXML
    private TableColumn<archiveEvents, String> eventDateColumn;
    @FXML
    private TableColumn<archiveEvents, String> endTimeColumn;
    @FXML
    private TableColumn<archiveEvents, String> locationColumn;
    @FXML
    private TableColumn<archiveEvents, String> statusColumn;

    private ObservableList<archiveEvents> eventList = FXCollections.observableArrayList();

    
    
    
    
  public void loadEventData() {
        try {
            // Clear the list first
            eventList.clear();

            // Add data from DAO or service
            eventList.addAll(new eventDAO().getAllArchive());

            // Set items in the table
            archiveTable.setItems(eventList);

            // Bind columns to properties of archiveEvents
        eventName.setCellValueFactory(cellData -> cellData.getValue().event_nameProperty());
      eventCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().event_categoryProperty());
eventDateColumn.setCellValueFactory(cellData -> cellData.getValue().event_dateProperty());
endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().end_timeProperty());
locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        
        
        
          loadEventData();
        
        
        
        
        
        
        
        
        
        
        
    }    
    
}
