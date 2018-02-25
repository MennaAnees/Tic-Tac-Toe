package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class MainGame extends GridPane {

    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final ColumnConstraints columnConstraints2;
    protected final ColumnConstraints columnConstraints3;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final BoardCell button;
    protected final BoardCell button0;
    protected final BoardCell button1;
    protected final BoardCell button2;
    protected final BoardCell button3;
    protected final BoardCell button4;
    protected final BoardCell button5;
    protected final BoardCell button6;
    protected final BoardCell button7;
    protected final Label label;
    protected final Label label0;

    public MainGame() {

        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        columnConstraints2 = new ColumnConstraints();
        columnConstraints3 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        button = new BoardCell();
        button0 = new BoardCell();
        button1 = new BoardCell();
        button2 = new BoardCell();
        button3 = new BoardCell();
        button4 = new BoardCell();
        button5 = new BoardCell();
        button6 = new BoardCell();
        button7 = new BoardCell();
        label = new Label();
        label0 = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #0DA192;");

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPercentWidth(5.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMinWidth(10.0);
        columnConstraints2.setPrefWidth(100.0);

        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMinWidth(10.0);
        columnConstraints3.setPercentWidth(5.0);
        columnConstraints3.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPercentHeight(20.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPercentHeight(30.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPercentHeight(30.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPercentHeight(30.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPercentHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setColumnIndex(button, 1);
        GridPane.setRowIndex(button, 1);
        button.setId("cell1");
        button.setPrefHeight(120.0);
        button.setPrefWidth(200.0);
        button.setStyle("-fx-background-color: #14BDAC;");
        //button.setText("X");
        button.setFont(new Font(96.0));
        button.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button, new Insets(0.0, 5.0, 5.0, 0.0));

        GridPane.setColumnIndex(button0, 1);
        GridPane.setRowIndex(button0, 2);
        button0.setId("cell4");
        button0.setPrefHeight(120.0);
        button0.setPrefWidth(200.0);
        button0.setStyle("-fx-background-color: #14BDAC;");
        //button0.setText("X");
        button0.setFont(new Font(96.0));
        button0.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button0, new Insets(5.0, 5.0, 5.0, 0.0));

        GridPane.setColumnIndex(button1, 1);
        GridPane.setRowIndex(button1, 3);
        button1.setId("cell7");
        button1.setPrefHeight(120.0);
        button1.setPrefWidth(200.0);
        button1.setStyle("-fx-background-color: #14BDAC;");
        //button1.setText("X");
        button1.setFont(new Font(96.0));
        button1.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button1, new Insets(5.0, 5.0, 0.0, 0.0));

        GridPane.setColumnIndex(button2, 2);
        GridPane.setRowIndex(button2, 1);
        button2.setId("cell2");
        button2.setPrefHeight(120.0);
        button2.setPrefWidth(200.0);
        button2.setStyle("-fx-background-color: #14BDAC;");
        //button2.setText("X");
        button2.setFont(new Font(96.0));
        button2.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button2, new Insets(0.0, 5.0, 5.0, 5.0));

        GridPane.setColumnIndex(button3, 2);
        GridPane.setRowIndex(button3, 2);
        button3.setId("cell5");
        button3.setPrefHeight(120.0);
        button3.setPrefWidth(200.0);
        button3.setStyle("-fx-background-color: #14BDAC;");
        //button3.setText("X");
        button3.setFont(new Font(96.0));
        button3.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button3, new Insets(5.0));

        GridPane.setColumnIndex(button4, 2);
        GridPane.setRowIndex(button4, 3);
        button4.setId("cell8");
        button4.setPrefHeight(120.0);
        button4.setPrefWidth(200.0);
        button4.setStyle("-fx-background-color: #14BDAC;");
        //button4.setText("X");
        button4.setFont(new Font(96.0));
        button4.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button4, new Insets(5.0, 5.0, 0.0, 5.0));

        GridPane.setColumnIndex(button5, 3);
        GridPane.setRowIndex(button5, 1);
        button5.setId("cell3");
        button5.setPrefHeight(120.0);
        button5.setPrefWidth(200.0);
        button5.setStyle("-fx-background-color: #14BDAC;");
        //button5.setText("X");
        button5.setFont(new Font(96.0));
        button5.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button5, new Insets(0.0, 0.0, 5.0, 5.0));

        GridPane.setColumnIndex(button6, 3);
        GridPane.setRowIndex(button6, 2);
        button6.setId("cell6");
        button6.setPrefHeight(120.0);
        button6.setPrefWidth(200.0);
        button6.setStyle("-fx-background-color: #14BDAC;");
        //button6.setText("X");
        button6.setFont(new Font(96.0));
        button6.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button6, new Insets(5.0, 0.0, 5.0, 5.0));

        GridPane.setColumnIndex(button7, 3);
        GridPane.setRowIndex(button7, 3);
        button7.setId("cell9");
        button7.setPrefHeight(120.0);
        button7.setPrefWidth(200.0);
        button7.setStyle("-fx-background-color: #14BDAC;");
        //button7.setText("X");
        button7.setFont(new Font(96.0));
        button7.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));
        GridPane.setMargin(button7, new Insets(5.0, 0.0, 0.0, 5.0));

        GridPane.setColumnIndex(label, 1);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(83.0);
        label.setPrefWidth(207.0);
        label.setText("Player1 : X");
        label.setFont(new Font(29.0));

        GridPane.setColumnIndex(label0, 3);
        label0.setPrefHeight(83.0);
        label0.setPrefWidth(207.0);
        label0.setText("Player2 : O");
        label0.setFont(new Font(29.0));

        getColumnConstraints().add(columnConstraints);
        getColumnConstraints().add(columnConstraints0);
        getColumnConstraints().add(columnConstraints1);
        getColumnConstraints().add(columnConstraints2);
        getColumnConstraints().add(columnConstraints3);
        getRowConstraints().add(rowConstraints);
        getRowConstraints().add(rowConstraints0);
        getRowConstraints().add(rowConstraints1);
        getRowConstraints().add(rowConstraints2);
        getRowConstraints().add(rowConstraints3);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(button3);
        getChildren().add(button4);
        getChildren().add(button5);
        getChildren().add(button6);
        getChildren().add(button7);
        getChildren().add(label);
        getChildren().add(label0);

    }
}
