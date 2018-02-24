package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class MainGame extends BorderPane {

    protected final Pane pane;
    protected final Label label;
    protected final CellLabel label0;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Pane pane0;
    protected final CellLabel label1;
    protected final Pane pane1;
    protected final CellLabel label2;
    protected final Pane pane2;
    protected final CellLabel label3;
    protected final Pane pane3;
    protected final CellLabel label4;
    protected final Pane pane4;
    protected final CellLabel label5;
    protected final Pane pane5;
    protected final CellLabel label6;
    protected final Pane pane6;
    protected final CellLabel label7;
    protected final Pane pane7;
    protected final CellLabel label8;
    protected final Pane pane8;
    protected final CellLabel label9;

    public MainGame() {

        pane = new Pane();
        label = new CellLabel();
        label0 = new CellLabel();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        pane0 = new Pane();
        label1 = new CellLabel();
        pane1 = new Pane();
        label2 = new CellLabel();
        pane2 = new Pane();
        label3 = new CellLabel();
        pane3 = new Pane();
        label4 = new CellLabel();
        pane4 = new Pane();
        label5 = new CellLabel();
        pane5 = new Pane();
        label6 = new CellLabel();
        pane6 = new Pane();
        label7 = new CellLabel();
        pane7 = new Pane();
        label8 = new CellLabel();
        pane8 = new Pane();
        label9 = new CellLabel();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(610.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(50.0);
        pane.setPrefWidth(600.0);

        label.setLayoutX(25.0);
        label.setLayoutY(10.0);
        label.setPrefHeight(40.0);
        label.setPrefWidth(250.0);
        label.setText("Player1: X");
        label.setFont(new Font(34.0));

        label0.setLayoutX(325.0);
        label0.setLayoutY(10.0);
        label0.setPrefHeight(40.0);
        label0.setPrefWidth(250.0);
        label0.setText("Player1: O");
        label0.setFont(new Font(34.0));
        setTop(pane);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setDisable(true);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(200.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(200.0);
        columnConstraints0.setPercentWidth(33.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(200.0);

        rowConstraints.setMaxHeight(200.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(200.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(200.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        pane0.setPrefHeight(200.0);
        pane0.setPrefWidth(200.0);
        GridPane.setMargin(pane0, new Insets(0.0, 5.0, 5.0, 0.0));

        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setPrefHeight(180.0);
        label1.setPrefWidth(195.0);
        label1.setText("");
        label1.setFont(new Font(96.0));

        GridPane.setColumnIndex(pane1, 1);
        pane1.setPrefHeight(429.0);
        pane1.setPrefWidth(198.0);
        GridPane.setMargin(pane1, new Insets(0.0, 5.0, 5.0, 5.0));

        label2.setAlignment(javafx.geometry.Pos.CENTER);
        label2.setPrefHeight(180.0);
        label2.setPrefWidth(190.0);
        
        label2.setFont(new Font(96.0));

        GridPane.setColumnIndex(pane2, 2);
        pane2.setPrefHeight(200.0);
        pane2.setPrefWidth(200.0);
        GridPane.setMargin(pane2, new Insets(0.0, 0.0, 5.0, 5.0));

        label3.setAlignment(javafx.geometry.Pos.CENTER);
        label3.setPrefHeight(180.0);
        label3.setPrefWidth(195.0);
        
        label3.setFont(new Font(96.0));

        GridPane.setRowIndex(pane3, 1);
        pane3.setPrefHeight(200.0);
        pane3.setPrefWidth(200.0);
        GridPane.setMargin(pane3, new Insets(5.0, 5.0, 5.0, 0.0));

        label4.setAlignment(javafx.geometry.Pos.CENTER);
        label4.setLayoutY(-1.0);
        label4.setPrefHeight(180.0);
        label4.setPrefWidth(195.0);
        
        label4.setFont(new Font(96.0));

        GridPane.setColumnIndex(pane4, 1);
        GridPane.setRowIndex(pane4, 1);
        pane4.setPrefHeight(45.0);
        pane4.setPrefWidth(198.0);
        GridPane.setMargin(pane4, new Insets(5.0));

        label5.setAlignment(javafx.geometry.Pos.CENTER);
        label5.setLayoutX(-1.0);
        label5.setLayoutY(-1.0);
        label5.setPrefHeight(180.0);
        label5.setPrefWidth(190.0);
        
        label5.setFont(new Font(96.0));

        GridPane.setColumnIndex(pane5, 2);
        GridPane.setRowIndex(pane5, 1);
        pane5.setPrefHeight(200.0);
        pane5.setPrefWidth(200.0);
        GridPane.setMargin(pane5, new Insets(5.0, 0.0, 5.0, 5.0));

        label6.setAlignment(javafx.geometry.Pos.CENTER);
        label6.setLayoutY(-1.0);
        label6.setPrefHeight(180.0);
        label6.setPrefWidth(195.0);
        label6.setText("X");
        label6.setFont(new Font(96.0));

        GridPane.setRowIndex(pane6, 2);
        pane6.setPrefHeight(200.0);
        pane6.setPrefWidth(200.0);
        GridPane.setMargin(pane6, new Insets(5.0, 5.0, 0.0, 0.0));

        label7.setAlignment(javafx.geometry.Pos.CENTER);
        label7.setLayoutY(1.0);
        label7.setPrefHeight(180.0);
        label7.setPrefWidth(195.0);
        
        label7.setFont(new Font(96.0));

        GridPane.setColumnIndex(pane7, 1);
        GridPane.setRowIndex(pane7, 2);
        pane7.setPrefHeight(200.0);
        pane7.setPrefWidth(200.0);
        GridPane.setMargin(pane7, new Insets(5.0, 5.0, 0.0, 5.0));

        label8.setAlignment(javafx.geometry.Pos.CENTER);
        label8.setLayoutX(-1.0);
        label8.setLayoutY(1.0);
        label8.setPrefHeight(180.0);
        label8.setPrefWidth(190.0);
        
        label8.setFont(new Font(96.0));

        GridPane.setColumnIndex(pane8, 2);
        GridPane.setRowIndex(pane8, 2);
        pane8.setPrefHeight(200.0);
        pane8.setPrefWidth(200.0);
        GridPane.setMargin(pane8, new Insets(5.0, 0.0, 0.0, 5.0));

        label9.setAlignment(javafx.geometry.Pos.CENTER);
        label9.setLayoutY(1.0);
        label9.setPrefHeight(180.0);
        label9.setPrefWidth(195.0);
        
        label9.setFont(new Font(96.0));
        setCenter(gridPane);

        gridPane.setStyle("-fx-background-color: #0DA192;");
        ArrayList<Pane> obj = new ArrayList<Pane>(Arrays.asList(pane0, pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8));
        ArrayList<CellLabel> cells = new ArrayList<CellLabel>(Arrays.asList(label1,label2,label3, label4,label5,label6,label7,label8,label9));
        
        
        for (Pane p : obj) {
            p.setStyle("-fx-background-color: #14BDAC;");
        }
        
        for (CellLabel cell : cells) {
            cell.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("aaaaaaaaaaaa");
                }
            
            });
            cell.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    cell.Press("X");
                    System.out.println("xxxxxxxxxx");
                }
            });
        }
        
        
        pane.getChildren().add(label);
        pane.getChildren().add(label0);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        pane0.getChildren().add(label1);
        gridPane.getChildren().add(pane0);
        pane1.getChildren().add(label2);
        gridPane.getChildren().add(pane1);
        pane2.getChildren().add(label3);
        gridPane.getChildren().add(pane2);
        pane3.getChildren().add(label4);
        gridPane.getChildren().add(pane3);
        pane4.getChildren().add(label5);
        gridPane.getChildren().add(pane4);
        pane5.getChildren().add(label6);
        gridPane.getChildren().add(pane5);
        pane6.getChildren().add(label7);
        gridPane.getChildren().add(pane6);
        pane7.getChildren().add(label8);
        gridPane.getChildren().add(pane7);
        pane8.getChildren().add(label9);
        gridPane.getChildren().add(pane8);

    }
    class CellLabel extends Label{
        boolean isPressed;
        public void Press(String s){
            this.setText("X");
        }
    }
}
