/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import models.GameData;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Player;
import models.SaveGame;

/**
 *
 * @author omran
 */
public class ReplayGameContoller implements Initializable {
    
    private final SaveGame game;
    private Stage stage;
    
    @FXML
    private GridPane grid;
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
    protected Label player1label;
    @FXML
    protected Label player2label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

    }

    public ReplayGameContoller(SaveGame g, Stage s) {
        game = g;
        stage = s;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1label.setText(game.player1+ ": X");
        player2label.setText(game.player2+ ": O");
        GameData.reset();
        Parent p =  cell00.getParent();
        final Scene scene = stage.getScene();
        
        final ArrayList<Button> moveButtons = new ArrayList<>();
        for (int i = 0; i < game.moves.size(); i++) {
            String id = "#cell"+game.moves.get(i)[0]+game.moves.get(i)[1];
            Button btn = (Button) p.lookup(id);
            moveButtons.add(btn);
        }

        Thread th = new Thread(new Runnable() {
            
            @Override
            public void run() {
                for (int i = 0; i < moveButtons.size(); i++) {
                    final  Button currentBtn = moveButtons.get(i);
                    final int j = i;
                    Platform.runLater(()->{
                        currentBtn.setText("X");
                    if(j % 2 != 0){
                        currentBtn.setTextFill(javafx.scene.paint.Color.WHITE);
                        currentBtn.setText("O");
                    }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ReplayGameContoller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Winner.fxml"));
                fxmlLoader.setController(new controllers.WinnerSavedController(game));
                Parent root;
                try {
                    root = (Parent) fxmlLoader.load();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ReplayGameContoller.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        th.start();

    }    
}
