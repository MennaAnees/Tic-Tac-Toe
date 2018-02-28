package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntryBase extends Pane {

    protected final Label label;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Stage stage;


    public EntryBase() {

        label = new Label();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        stage = new Stage(); 
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                 Parent testRoot = new testBase();
                 Scene networkScene = new Scene(testRoot);
                 stage.setScene(networkScene);
                 stage.show();
            }
        });
//        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(475.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: green;");

        label.setLayoutX(80.0);
        label.setLayoutY(20.0);
        label.setPrefHeight(70.0);
        label.setPrefWidth(394.0);
        label.setText("Tic Tac Toe..");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#effc05"));
        label.setFont(new Font(57.0));

        button.setLayoutX(194.0);
        button.setLayoutY(311.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(53.0);
        button.setPrefWidth(206.0);
        button.setText("Play Online");
        button.setFont(new Font(24.0));

        button0.setLayoutX(190.0);
        button0.setLayoutY(228.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(53.0);
        button0.setPrefWidth(206.0);
        button0.setText("2 Players ");
        button0.setFont(new Font(24.0));

        button1.setLayoutX(191.0);
        button1.setLayoutY(150.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(53.0);
        button1.setPrefWidth(206.0);
        button1.setText("Single mode");
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#111111"));
        button1.setFont(new Font(24.0));

        getChildren().add(label);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);

    }
}
