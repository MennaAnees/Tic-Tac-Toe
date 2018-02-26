/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 *
 * @author menna
 */
public class TicTacToe extends Application {
    Parent entryRoot;
    Scene entryScene;

    Parent networkRoot;
    Scene networkScene;
    

    @Override
    public void start(Stage stage) throws Exception {
//        Parent entryRoot = FXMLLoader.load(getClass().getResource("Entry.fxml"));
        Parent entryRoot = new EntryBase();
        Scene entryScene = new Scene(entryRoot);

//        Parent networkRoot = FXMLLoader.load(getClass().getResource("test.fxml"));
        
        
       

        stage.setScene(entryScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
