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
import javafx.scene.input.*;
import javafx.event.*;

/**
 *
 * @author menna
 */
public class TicTacToe extends Application {
    int counter = 0;

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setResizable(false);   
        stage.setScene(new Scene(new views.EntryBase(stage)));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
