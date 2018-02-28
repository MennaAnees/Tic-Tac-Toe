package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserInfoBase extends VBox {

    protected final Pane pane;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final TextField textField;
    protected final HBox hBox;
    protected final Pane pane0;
    protected final Button button;
    protected final Pane pane1;
    protected final Button button0;

    public UserInfoBase(Stage s) {

        pane = new Pane();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        textField = new TextField();
        hBox = new HBox();
        pane0 = new Pane();
        button = new Button();
        pane1 = new Pane();
        button0 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(600.0);

        VBox.setVgrow(pane, javafx.scene.layout.Priority.ALWAYS);
        pane.setPrefHeight(323.0);
        pane.setPrefWidth(600.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(261.0);
        label.setLayoutY(127.0);
        label.setOpacity(0.5);
        label.setPrefHeight(58.0);
        label.setPrefWidth(161.0);
        label.setText("Name");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setFont(new Font(33.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(158.0);
        label0.setLayoutY(39.0);
        label0.setPrefHeight(68.0);
        label0.setPrefWidth(286.0);
        label0.setText("Tic Tac Toe");
        label0.setFont(new Font("System Bold", 33.0));

        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setId("player_token");
        label1.setLayoutX(188.0);
        label1.setLayoutY(110.0);
        label1.setOpacity(0.5);
        label1.setText("×");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label1.setFont(new Font(76.0));

        textField.setAlignment(javafx.geometry.Pos.CENTER);
        textField.setId("name_textfield");
        textField.setLayoutX(134.0);
        textField.setLayoutY(222.0);
        textField.setPrefHeight(68.0);
        textField.setPrefWidth(332.0);
        textField.setPromptText("Enter your name");
        textField.setFont(new Font(30.0));

        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        HBox.setHgrow(pane0, javafx.scene.layout.Priority.ALWAYS);
        pane0.setPrefHeight(200.0);
        pane0.setPrefWidth(200.0);
        pane0.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        button.setCancelButton(true);
        button.setId("cancel");
        button.setLayoutX(82.0);
        button.setLayoutY(16.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(58.0);
        button.setPrefWidth(137.0);
        button.setText("Cancel");
        button.setFont(new Font(25.0));

        HBox.setHgrow(pane1, javafx.scene.layout.Priority.ALWAYS);
        pane1.setPrefHeight(200.0);
        pane1.setPrefWidth(200.0);

        button0.setDefaultButton(true);
        button0.setId("next");
        button0.setLayoutX(82.0);
        button0.setLayoutY(16.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(58.0);
        button0.setPrefWidth(137.0);
        button0.setText("Next");
        button0.setFont(new Font(25.0));

        pane.getChildren().add(label);
        pane.getChildren().add(label0);
        pane.getChildren().add(label1);
        pane.getChildren().add(textField);
        getChildren().add(pane);
        pane0.getChildren().add(button);
        hBox.getChildren().add(pane0);
        pane1.getChildren().add(button0);
        hBox.getChildren().add(pane1);
        getChildren().add(hBox);

    }
}
