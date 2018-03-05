/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.DataBaseMainupulation;
import models.GameData;
import models.SaveGame;

/**
 * FXML Controller class
 *
 * @author omran
 */
public class SavedGamesController implements Initializable {

    @FXML
    private ImageView exit;
    @FXML
    private ImageView tryAgain;
    @FXML
    private ImageView save;
    @FXML
    private ImageView home;
    @FXML
    private ListView<String> list;

    private ArrayList<SaveGame> savedGamesArray = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList items = FXCollections.observableArrayList();
        DataBaseMainupulation db;
        try {
            db = new DataBaseMainupulation();
            ResultSet rs = db.get();
            while (rs.next()){
                int id = rs.getInt("GID");
                String player1 = rs.getString("player_1");
                String player2 = rs.getString("player_2");
                String moves = rs.getString("moves");
                String winner = rs.getString("winner");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                savedGamesArray.add(new SaveGame(id, player1, player2, moves, winner, timestamp));
                items.add(timestamp);
                
        }
                
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        list.setItems(items);
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void handleMouseClick(MouseEvent event) throws IOException {
      
        int index = list.getSelectionModel().getSelectedIndex();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
        fxmlLoader.setController(new ReplyGameContoller(savedGamesArray.get(index), stage));
        Parent root = (Parent) fxmlLoader.load();
        scene.setRoot(root);

    }
    
}
