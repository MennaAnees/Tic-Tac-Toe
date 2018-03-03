/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.GameData;
import models.DataBaseMainupulation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String move = cell.getId().substring(4,6);
        System.out.println(move);
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
            GameData.setMoves(move);
            String winner = GameData.whoWin();
            if (winner != null) {
                String moves = String.join(":", GameData.getMoves());
                DataBaseMainupulation db;
                try {
                    db = new DataBaseMainupulation();
                    String winnername  = GameData.getCounter() % 2 == 0 ? GameData.player1.name : GameData.player2.name;
                    db.insert(GameData.player1.name, GameData.player2.name, moves, winnername);
                    db.closeConn();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(winner);
                System.out.println(moves);
            }
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1label.setText(GameData.player1.name+ ": X");
        player2label.setText(GameData.player2.name+ ": O");
    }    
}
