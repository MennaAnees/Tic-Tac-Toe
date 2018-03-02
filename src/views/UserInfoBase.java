package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Player;
import model.GameData;

public class UserInfoBase extends VBox {

    protected final Pane pane;
    protected final Label playerLabel;
    protected final Label label0;
    protected final Label playerToken;
    protected final TextField nameField;
    protected final HBox hBox;
    protected final Pane pane0;
    protected final Button cancel;
    protected final Pane pane1;
    protected final Button next;
    protected boolean player2Flag = true;


    public UserInfoBase(Stage s) {

        pane = new Pane();
        playerLabel = new Label();
        label0 = new Label();
        playerToken = new Label();
        nameField = new TextField();
        hBox = new HBox();
        pane0 = new Pane();
        cancel = new Button();
        pane1 = new Pane();
        next = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #61DED2;");

        VBox.setVgrow(pane, javafx.scene.layout.Priority.ALWAYS);
        pane.setPrefHeight(323.0);
        pane.setPrefWidth(600.0);

        playerLabel.setAlignment(javafx.geometry.Pos.CENTER);
        playerLabel.setLayoutX(220);
        playerLabel.setLayoutY(127.0);
        playerLabel.setOpacity(0.5);
        playerLabel.setPrefHeight(58.0);
        playerLabel.setPrefWidth(161.0);
        playerLabel.setText("Player 1");
        playerLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playerLabel.setFont(new Font(33.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(158.0);
        label0.setLayoutY(39.0);
        label0.setPrefHeight(68.0);
        label0.setPrefWidth(286.0);
        label0.setText("Tic Tac Toe");
        label0.setFont(new Font("System Bold", 33.0));

//        playerToken.setAlignment(javafx.geometry.Pos.CENTER);
//        playerToken.setId("player_token");
//        playerToken.setLayoutX(188.0);
//        playerToken.setLayoutY(110.0);
//        playerToken.setOpacity(0.5);
//        playerToken.setText("×");
//        playerToken.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
//        playerToken.setFont(new Font(76.0));

        nameField.setAlignment(javafx.geometry.Pos.CENTER);
        nameField.setId("name_textfield");
        nameField.setLayoutX(134.0);
        nameField.setLayoutY(222.0);
        nameField.setPrefHeight(68.0);
        nameField.setPrefWidth(332.0);
        nameField.setPromptText("Enter your name");
        nameField.setFont(new Font(30.0));

        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        HBox.setHgrow(pane0, javafx.scene.layout.Priority.ALWAYS);
        pane0.setPrefHeight(200.0);
        pane0.setPrefWidth(200.0);
        pane0.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        cancel.setCancelButton(true);
        cancel.setId("cancel");
        cancel.setLayoutX(82.0);
        cancel.setLayoutY(16.0);
        cancel.setMnemonicParsing(false);
        cancel.setPrefHeight(58.0);
        cancel.setPrefWidth(137.0);
        cancel.setText("Cancel");
        cancel.setFont(new Font(25.0));
        cancel.setOnAction((ActionEvent e) -> {
            if(GameData.mode == 0) {
                s.setScene(new Scene(new EntryBase(s)));
            } else if (!player2Flag && GameData.mode == 1) {
//                    playerToken.setText("𝖮");
                GameData.player2 = new Player(nameField.getText());
                playerLabel.setText("Player 1");
                player2Flag = true;
            } else if(GameData.mode == 1) {
                s.setScene(new Scene(new EntryBase(s)));
            }
        });

        HBox.setHgrow(pane1, javafx.scene.layout.Priority.ALWAYS);
        pane1.setPrefHeight(200.0);
        pane1.setPrefWidth(200.0);

        next.setDefaultButton(true);
        next.setId("next");
        next.setLayoutX(82.0);
        next.setLayoutY(16.0);
        next.setMnemonicParsing(false);
        next.setPrefHeight(58.0);
        next.setPrefWidth(137.0);
        next.setText("Next");
        next.setFont(new Font(25.0));
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                if(GameData.mode == 0) {
                    GameData.player1 = new Player(nameField.getText());
                    GameData.player2 = new Player("CPU");
                    s.setScene(new Scene(new MainGame(s)));
                } else if(player2Flag && GameData.mode == 1) {
//                    playerToken.setText("𝖮");
                    GameData.player1 = new Player(nameField.getText());
                    nameField.setText("");
                    playerLabel.setText("Player 2");
                    player2Flag = false;
                } else if(!player2Flag && GameData.mode == 1) {
                    GameData.player2 = new Player(nameField.getText());
                    System.out.println(nameField.getText() + " " + GameData.player2.name);
                    s.setScene(new Scene(new MainGame(s)));
                }
//                } else if(GameData.mode == 1) {
//                    s.setScene(new Scene(new MainGame(s)));
//                }
            }
        });
        
        pane.getChildren().add(playerLabel);
        pane.getChildren().add(label0);
        pane.getChildren().add(playerToken);
        pane.getChildren().add(nameField);
        getChildren().add(pane);
        pane0.getChildren().add(cancel);
        hBox.getChildren().add(pane0);
        pane1.getChildren().add(next);
        hBox.getChildren().add(pane1);
        getChildren().add(hBox);

    }
}
