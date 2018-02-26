/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = new FXMLDocumentBase();
        Parent userRoot = new views.UserInfoBase();

//        Scene scene = new Scene(root);
//        Scene netScene = new Scene(netRoot);
        Scene[] sceneArr = {new Scene(userRoot), new Scene(root)};

        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                  System.out.println("hi");
                  stage.setScene(sceneArr[counter++%2]);
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
