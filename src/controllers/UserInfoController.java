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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Player;

/**
 *
 * @author omran
 */
public class UserInfoController implements Initializable {
protected boolean player2Flag = true;


    @FXML
    private Label playerLabel;
    @FXML
    private Label playerSymbol;
    @FXML
    private TextField playerName;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
        fxmlLoader.setController(new controllers.LocalPlayersController());
        System.out.println(playerName.getText());
        if(GameData.getMode() == 1) {
            GameData.player1 = new Player(playerName.getText());
            GameData.player2 = new Player("CPU");
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);

        } else if(player2Flag && GameData.getMode() == 2) {
            playerSymbol.setText("𝖮");
            GameData.player1 = new Player(playerName.getText());
            playerName.setText("");
            playerLabel.setText("Player 2");
            player2Flag = false;
        } else if(!player2Flag && GameData.getMode() == 2) {
            GameData.player2 = new Player(playerName.getText());
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
