/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.GameData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author omran
 */
public class LocalPlayersController implements Initializable {
    
    @FXML
    protected Label player1label;
    @FXML
    protected Label player2label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button cell = (Button) event.getTarget();
        int xPos = Character.getNumericValue(cell.getId().charAt(4));
        int yPos =  Character.getNumericValue(cell.getId().charAt(5));
        if (!GameData.isClicked(xPos, yPos)) {
            GameData.setClickArray(xPos, yPos);
            if(GameData.getCounter() % 2 == 0){
                cell.setTextFill(javafx.scene.paint.Color.WHITE);
                cell.setText("O");
                GameData.setMoveArray(xPos, yPos, 1);
            }
            else{
                cell.setText("X");
                GameData.setMoveArray(xPos, yPos, 2);
            }
            GameData.incCounter();
            String winner = GameData.whoWin();
            if (winner != null) {
                System.out.println(winner);
            }
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1label.setText(GameData.player1.name+ ": X");
        player2label.setText(GameData.player2.name+ ": O");
    }    
}
