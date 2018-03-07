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
import models.SaveGame;

/**
 * FXML Controller class
 *
 * @author omran
 */
public class WinnerSavedController implements Initializable {

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
    SaveGame game;

    public WinnerSavedController(SaveGame sg) {
        game = sg;
    }

    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        save.setImage(new Image("/views/imgs/del.png"));

        if (game.winner.equals("noWinner")) {
            label.setText("Draw!");
            img.setImage(new Image ("/views/imgs/draw.png"));
        }
        else{
            label.setText(game.winner);
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
            fxmlLoader.setController(new controllers.LocalPlayersController());
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
            DataBaseMainupulation db;
            try {
                db = new DataBaseMainupulation();
                System.out.println(game.gId);
                db.del(game.gId);
                db.closeConn();
                save.setImage(new Image ("/views/imgs/delok.png"));
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
