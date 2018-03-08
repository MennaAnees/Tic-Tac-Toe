/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory.Result;
import models.*;
import network.*;

/**
 * FXML Controller class
 *
 * @author a7mad
 */
public class SingleLevel implements Initializable {
    
    @FXML
    private ImageView exit;
   
    
    
    
    FXMLLoader fxmlLoaderMain;
    FXMLLoader fxmlLoaderUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
   
     @FXML
    private void handleButtonAction(MouseEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        String id = node.getId();
        if (id.equals("exit")) {
            System.exit(0);
        }
        
        else if (id.equals("home")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Entry.fxml"));
            fxmlLoader.setController(new controllers.EntryController());
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);

        }
        else if(id.equals("easy")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
            fxmlLoader.setController(new controllers.SingleModeController());
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        }
        else if(id.equals("hard")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
            fxmlLoader.setController(new controllers.SingleModeController1());
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        }
       

    }


    
}
