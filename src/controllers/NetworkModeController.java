/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import models.GameData;
import models.Player;

/**
 * FXML Controller class
 *
 * @author omran
 */
public class NetworkModeController implements Initializable {

    @FXML
    private Button cell00;
    @FXML
    private Button cell01;
    @FXML
    private Button cell02;
    @FXML
    private Button cell10;
    @FXML
    private Button cell11;
    @FXML
    private Button cell12;
    @FXML
    private Button cell20;
    @FXML
    private Button cell21;
    @FXML
    private Button cell22;
    @FXML
    private Label player1label;
    @FXML
    private Label player2label;
    
    private int[] computersMove = new int[2];
    Player winner =null;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(models.GameData.isServer) {
            player1label.setText(GameData.player1.name + ": X");
            player2label.setText(GameData.player2.name + ": O");
        } else {
            player1label.setText(GameData.player1.name + ": O");
            player2label.setText(GameData.player2.name + ": X");
        }
        GameData.networkMainGameThread = new Thread(() -> {
            while(true) {
                try {
                    String move = GameData.dis.readLine();
                    System.out.println(move);
                    System.out.println("#cell" + move.substring(0, 1) + move.substring(1, 2));
                    final Button cell = (Button) cell00.getParent().lookup("#cell" + move);
                    System.out.println(cell);
                    int xPos = Character.getNumericValue(cell.getId().charAt(4));
                    int yPos =  Character.getNumericValue(cell.getId().charAt(5));
                    GameData.setClickArray(xPos, yPos);
                    if(GameData.getCounter() % 2 == 0 && GameData.isServer){
                        Platform.runLater(() -> {
                            cell.setTextFill(javafx.scene.paint.Color.WHITE);
                            cell.setText("O");
                            });
                        GameData.setMoveArray(xPos, yPos, 1);
                    } else {
                        Platform.runLater(() -> {
                            cell.setText("X");
                        });
                        GameData.setMoveArray(xPos, yPos, 2);
                    }
                    GameData.incCounter();
                    GameData.setMoves(move);
                    GameData.moveAllowance = true;
                    winner = GameData.whoWin();

                    if (winner != null) {
                        Stage stage = (Stage) cell.getScene().getWindow();
                        Scene scene = stage.getScene();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Winner.fxml"));
                        fxmlLoader.setController(new controllers.WinnerController(winner));
                        Parent root = (Parent) fxmlLoader.load();
                        scene.setRoot(root);
                    }
                } catch (IOException ex) {
                    System.out.println("can't connect now");
    //                Logger.getLogger(NetworkModeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        GameData.networkMainGameThread.start();
        GameData.reset();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
//        System.out.println("hi game");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Winner.fxml"));
        Button cell = (Button) event.getTarget();
        String move = cell.getId().substring(4,6);

        int xPos = Character.getNumericValue(cell.getId().charAt(4));
        int yPos =  Character.getNumericValue(cell.getId().charAt(5));
        if (!GameData.isClicked(xPos, yPos) && GameData.moveAllowance) {
            GameData.setClickArray(xPos, yPos);
            System.out.println("hi game" + GameData.moveAllowance);
            if(GameData.getCounter() % 2 == 0 || !GameData.isServer){
                cell.setTextFill(javafx.scene.paint.Color.WHITE);
                cell.setText("O");
                GameData.setMoveArray(xPos, yPos, 1);
            } else {
                cell.setText("X");
                GameData.setMoveArray(xPos, yPos, 2);
            }
            GameData.incCounter();
            GameData.setMoves(move);
            GameData.moveAllowance = false;
            System.out.println("hi before" + cell.getId().substring(4, 6));
            models.GameData.ps.println(cell.getId().substring(4,6));
            System.out.println("hi after" + cell.getId().substring(4, 6));
            winner = GameData.whoWin();
            
            if (winner != null) {
                fxmlLoader.setController(new controllers.WinnerController(winner));
                Parent root = (Parent) fxmlLoader.load();
                scene.setRoot(root);
            }
        }
        
        
//        if(GameData.getCounter()%2==0){
//            Parent p =  cell00.getParent();
//            String id = "#cell"+computersMove[0]+computersMove[1];
//            Button btn  =  (Button) p.lookup(id);
//            if(winner==null){
//            btn.fire();
//            }
//        }
//        
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println(GameData.getClicked()[i][j]);
//            }
//        }
//        System.out.println(computersMove[0]+"      "+computersMove[1]);
                
    }
}
