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
        Parent userRoot = new views.UserInfoBase(stage);
        Parent mainRoot = new views.MainGame(stage);
        Parent entryRoot = new views.EntryBase(stage);
        
        Scene[] sceneArr = {new Scene(entryRoot), new Scene(userRoot), new Scene(mainRoot)};

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                  System.out.println("hi");
                  stage.setScene(sceneArr[counter++%3]);
            }
        });      
        stage.setScene(sceneArr[0]);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
