/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package imfinal;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import repository.eventDAO;
import repository.mysqlConnector;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Text eventCtr;
@FXML
    private Text userCount;


@FXML
    private Text archiveCount;
    private Connection connect;
    Alert alert;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        eventDAO eventdao = new eventDAO();

        int count = eventdao.getEventCount();
        int count2 = eventdao.getUserCount();
        int count3 = eventdao.getArchiveCount();
        eventCtr.setText(String.valueOf(count));
        userCount.setText(String.valueOf(count2));
        archiveCount.setText(String.valueOf(count3));

    }

}
