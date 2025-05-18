/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package imfinal;

import com.mysql.cj.xdevapi.Table;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Users;
import repository.mysqlConnector;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button exitMain;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField categoryTxt1;
    @FXML
    private TextField timeTxt;

    @FXML
    private Button minimize;

    @FXML
    private TextField locationTxt;

    @FXML
    private Button addBtn;

    @FXML
    private ComboBox<String> statusBx;

    @FXML
    private PasswordField passwordSpTxt;

    @FXML
    private CheckBox showPass;
    @FXML
    private CheckBox pasword3;

    @FXML
    private TextField password2;

    @FXML
    private Button exitbtn;

    @FXML
    private Button ArchiveBtn;

    @FXML
    private AnchorPane content;

    @FXML
    private Button eventsBtn;
    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField text3;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private Table table1;

    @FXML
    private Pane crudPane;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TextField usernameSpTxt;
    @FXML
    private Button exitSp;

    @FXML
    private Button exitMf;

    @FXML
    private Button homeBtn;

    @FXML
    private Button logInBtn;

    @FXML
    private Label label;

    @FXML
    private Hyperlink signupLnk;

    @FXML
    private Hyperlink logLnk;

    Alert alert;
    private PreparedStatement prepare;
    private ResultSet result;
    private Connection connect;
        HomeController home = new HomeController();
    public void login(ActionEvent event) {
        String sql = "SELECT * FROM Users WHERE username = ? and password = ?";
        connect = mysqlConnector.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, usernameTxt.getText());
            prepare.setString(2, passwordTxt.getText());

            result = prepare.executeQuery();

            if (usernameTxt.getText().isEmpty() && passwordTxt.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();

            } else if (passwordTxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the password field");
                alert.showAndWait();

            } else if (usernameTxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the username field");
                alert.showAndWait();
            } else {

                if (result.next()) {

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    switchMain(event);

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password!");
                    alert.showAndWait();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signUp() {

        String sql = "INSERT INTO Users(first_name, last_name, username, password) VALUES (?,?,?,?)";

        connect = mysqlConnector.connectDB();

        try {

            if (firstNameTxt.getText().isEmpty() && lastNameTxt.getText().isEmpty()
                    && usernameSpTxt.getText().isEmpty() && passwordSpTxt.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();

            } else if (firstNameTxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the firstname field");
                alert.showAndWait();
            } else if (lastNameTxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the lastname field");
                alert.showAndWait();
            } else if (usernameSpTxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the username field");
                alert.showAndWait();
            } else if (passwordSpTxt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the password field");
                alert.showAndWait();
            } else {
                String checker = "SELECT username FROM Users WHERE username = '"
                        + usernameSpTxt.getText() + "'";

                prepare = connect.prepareStatement(checker);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(usernameSpTxt.getText() + "is already exist!");
                    alert.showAndWait();

                    usernameSpTxt.setText("");
                    usernameSpTxt.setText("");

                } else if (passwordSpTxt.getText().length() < 5) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You need atleast 5 characters for the password!");
                    alert.showAndWait();

                } else {

                    Users user = new Users(firstNameTxt.getText(), lastNameTxt.getText(), usernameSpTxt.getText(), passwordSpTxt.getText());

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, user.getFirst_name());
                    prepare.setString(2, user.getLast_name());
                    prepare.setString(3, user.getUsername());
                    prepare.setString(4, user.getPassword());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Informative Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Signed Up!");
                    alert.showAndWait();

                    firstNameTxt.setText("");
                    lastNameTxt.setText("");
                    usernameSpTxt.setText("");
                    passwordSpTxt.setText("");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void signUpPage(ActionEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Scene loginScene = new Scene(loginRoot);

            Stage stage = (Stage) signupLnk.getScene().getWindow();

            loginRoot.setOnMousePressed(e -> {
                stage.setUserData(new double[]{e.getSceneX(), e.getSceneY()});
            });

            loginRoot.setOnMouseDragged(e -> {
                double[] offset = (double[]) stage.getUserData();
                stage.setX(e.getScreenX() - offset[0]);
                stage.setY(e.getScreenY() - offset[1]);
            });

            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchLogIn(ActionEvent event) {
        try {

            Parent loginRoot = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(loginRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
            Parent loginRoot = loader.load();

            FXMLDocumentController controller = loader.getController();
            controller.initButtons();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loginRoot);

            loginRoot.setOnMousePressed(e -> {
                stage.setUserData(new double[]{e.getSceneX(), e.getSceneY()});
            });

            loginRoot.setOnMouseDragged(e -> {
                double[] offset = (double[]) stage.getUserData();
                stage.setX(e.getScreenX() - offset[0]);
                stage.setY(e.getScreenY() - offset[1]);
            });

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initButtons() {
        List<Button> buttons = Arrays.asList(homeBtn, eventsBtn, ArchiveBtn);

        for (Button btn : buttons) {
            btn.setOnAction(e -> {

                for (Button b : buttons) {
                    b.getStyleClass().remove("selected-button");
                }

                if (!btn.getStyleClass().contains("selected-button")) {
                    btn.getStyleClass().add("selected-button");
                }

                if (btn == eventsBtn) {

                    loadPane("Events.fxml");

                } else if (btn == homeBtn) {
                    loadPane("Home.fxml");
                  
                } else if(btn == ArchiveBtn){
                   loadPane("Archive.fxml");
                }

            });
        }

        homeBtn.getStyleClass().add("selected-button");
        loadPane("Home.fxml");
      
    }

    
        

    
    
    
    public void loadPane(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent pane = loader.load();

            Object controller = loader.getController();

            if (controller instanceof EventsController) {
                EventsController eventsController = (EventsController) controller;
                
            } else if (controller instanceof HomeController) {
                HomeController homeController = (HomeController) controller;
                
            } else if(controller instanceof ArchiveController){
                  ArchiveController archiveController = (ArchiveController) controller;
            }

            content.getChildren().clear();
            content.getChildren().add(pane);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    private void exitMain(ActionEvent event) {
        System.exit(0);

    }

    public void showPassword(ActionEvent event) {

        if (showPass.isSelected()) {

            password2.setText(passwordTxt.getText());
            password2.setVisible(true);
            passwordTxt.setVisible(false);
        } else {

            passwordTxt.setText(password2.getText());
            passwordTxt.setVisible(true);
            password2.setVisible(false);
        }
    }

    public void showPasswordSp(ActionEvent event) {

        if (pasword3.isSelected()) {

            text3.setText(passwordSpTxt.getText());
            text3.setVisible(true);
            passwordSpTxt.setVisible(false);
        } else {

            passwordSpTxt.setText(text3.getText());
            passwordSpTxt.setVisible(true);
            text3.setVisible(false);
        }
    }
    
    
    

          
          
          
          
          
          
          
          
          
          
          
          
    
    

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
