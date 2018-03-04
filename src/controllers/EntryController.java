/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import models.GameData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author omran
 */
public class EntryController implements Initializable {
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/user_info.fxml"));
        fxmlLoader.setController(new controllers.UserInfoController());
        Parent root = (Parent) fxmlLoader.load();
        System.out.println(node.getId());
        if (node.getId().equals("single")) {
            GameData.setMode(1);
        }
        else if (node.getId().equals("players")) {
            GameData.setMode(2);
        }
        else if (node.getId().equals("online")) {
            GameData.setMode(3);
        }
        else if (node.getId().equals("savedGames")) {
            fxmlLoader = new FXMLLoader(getClass().getResource("/views/SavedGames.fxml"));
            fxmlLoader.setController(new controllers.SavedGamesController());
            root = (Parent) fxmlLoader.load();
        }
        scene.setRoot(root);

    }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
