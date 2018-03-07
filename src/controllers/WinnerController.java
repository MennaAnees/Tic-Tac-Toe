/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.DataBaseMainupulation;
import models.GameData;
import models.Player;

/**
 * FXML Controller class
 *
 * @author omran
 */
public class WinnerController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView tryAgain;
    @FXML
    private ImageView save;
    @FXML
    private ImageView img;
    
    boolean saved = false;
    Player winner;

    public WinnerController(Player p) {
        winner = p;
    }

    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (winner.name=="noWinner") {
            label.setText("Draw!");
            img.setImage(new Image ("/views/imgs/draw.png"));
        }
        else{
            if (!winner.equals(GameData.player1)) {
                img.setImage(new Image ("/views/imgs/loser.png"));

            }
            label.setText(winner.name);
        }
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
        else if (id.equals("tryAgain")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
           if(GameData.getMode()==1){fxmlLoader.setController(new controllers.SingleModeController1());}
           if(GameData.getMode()==2){fxmlLoader.setController(new controllers.LocalPlayersController());}
           if(GameData.getMode()==3){
               Thread repSend = new Thread(() -> {
                   while(true) {
                       GameData.ps.println("tryAgain");
                       try {
                           Thread.sleep(500);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(WinnerController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               });
               repSend.start();
               GameData.dis.readLine();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WinnerController.class.getName()).log(Level.SEVERE, null, ex);
                }
               repSend.stop();
               fxmlLoader.setController(new controllers.NetworkModeController());               
           }
           
            
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        }
        else if (id.equals("home")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Entry.fxml"));
            fxmlLoader.setController(new controllers.EntryController());
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);

        }
        else if (id.equals("save") && !saved) {
            String moves = String.join(":", GameData.getMoves());              
            DataBaseMainupulation db;
            try {
                db = new DataBaseMainupulation();
                db.insert(GameData.player1.name, GameData.player2.name, moves, winner.name);
                db.closeConn();
                save.setImage(new Image ("/views/imgs/saveok.png"));
                saved = true;
                System.out.println(saved);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
