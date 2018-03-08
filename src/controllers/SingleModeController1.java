/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.GameData;
import static models.GameData.player1;
import static models.GameData.player2;
import models.Player;

/**
 * FXML Controller class
 *
 * @author omran
 */
public class SingleModeController1 implements Initializable {

    @FXML
    private Button cell00;
    @FXML
    private Button cell01;
    @FXML
    private Button cell02;
    @FXML
    private Button cell10;
    @FXML
    private Button cell11;
    @FXML
    private Button cell12;
    @FXML
    private Button cell20;
    @FXML
    private Button cell21;
    @FXML
    private Button cell22;
    @FXML
    private Label player1label;
    @FXML
    private Label player2label;
    
    private Integer[] computersMove = new Integer[2];
    Player winner =null;
    private ArrayList<Integer[]> available;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        player1label.setText(GameData.player1.name+ ": ×");
        player2label.setText(GameData.player2.name+ ": 🞅");
        GameData.reset();
        GameData.isServer = true;

    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Winner.fxml"));
        Button cell = (Button) event.getTarget();
        String move = cell.getId().substring(4,6);

        int xPos = Character.getNumericValue(cell.getId().charAt(4));
        int yPos =  Character.getNumericValue(cell.getId().charAt(5));
        if (!GameData.isClicked(xPos, yPos)) {
            GameData.setClickArray(xPos, yPos);
            if(GameData.getCounter() % 2 == 0){
                cell.setTextFill(javafx.scene.paint.Color.WHITE);
                cell.setText("🞅");
                GameData.setMoveArray(xPos, yPos, 1);
            }
            else{
                cell.setText("×");
                GameData.setMoveArray(xPos, yPos, 2);
            }
            GameData.incCounter();
            GameData.setMoves(move);
            winner = GameData.whoWin();
            
            if (winner != null) {
                fxmlLoader.setController(new controllers.WinnerController(winner));
                Parent root = (Parent) fxmlLoader.load();
                scene.setRoot(root);
            }
            
        }
        
        
        if(GameData.getCounter()%2==0){
            minimax();
            Parent p =  cell00.getParent();
            String id = "#cell"+computersMove[0]+computersMove[1];
            Button btn  =  (Button) p.lookup(id);
            if(winner==null){
            btn.fire();
            }
        }
    }
        
       
       
    
    

    public void minimax(){
        
        available = new ArrayList<Integer[]>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(GameData.getClicked()[i][j]==false){
                    Integer[] tmp = {i,j};
                    available.add(tmp);
                }
            }
        }
       
            
        computersMove = whoWillWin();
        if (computersMove.length!=0) {
            System.out.println(Arrays.toString(computersMove));
        }

    }
    
    public Integer[] whoWillWin(){
    
        int sum1 = 0;
        int sum2 = 0;
        int tmpY;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum1 += GameData.getMoveArray(i, j);
                sum2 += GameData.getMoveArray(j, i);
            }
            if(sum1==12 || sum2==12){}
            if (sum1==14) {Integer[] a = whoIs10(i, "y");return a;}
            else if (sum2==14){Integer[] a = whoIs10(i, "x");return a;}
            else{sum1=0; sum2=0;}
        }
        if(GameData.getMoveArray(0, 0)+GameData.getMoveArray(1, 1)+GameData.getMoveArray(2, 2)==14){
            if (GameData.getMoveArray(0, 0)==10) {Integer[] a = {0, 0};return a;}
            if (GameData.getMoveArray(1, 1)==10) {Integer[] a = {1, 1};return a;}
            if (GameData.getMoveArray(2, 2)==10) {Integer[] a = {2, 2};return a;}
        }
        if(GameData.getMoveArray(0, 2)+GameData.getMoveArray(1, 1)+GameData.getMoveArray(2, 0)==14){
            if (GameData.getMoveArray(0, 2)==10) {Integer[] a = {0, 2};return a;}
            if (GameData.getMoveArray(1, 1)==10) {Integer[] a = {1, 1};return a;}
            if (GameData.getMoveArray(2, 0)==10) {Integer[] a = {2, 0};return a;}
        
        }
        if (available.isEmpty()) {
            Integer[] a = {0,0};
            return a;
        }
        Integer[] a = randomMove(available.size());
        return a;
    }

    public Integer[] whoIs10(int i, String s){
        for (int j = 0; j < 3; j++) {
            if (s.equals("y")) {
                if (GameData.getMoveArray(i, j)==10) {
                    Integer[] res = {i,j};
                    return res;
                }
            }
            if (s.equals("x")) {
                if (GameData.getMoveArray(j, i)==10) {
                    Integer[] res = {j,i};
                    return res;
                }
            }
        }
        Integer[] res = {-1,-1};
        return res;
    }
    
    public Integer[] randomMove(int s){
        if (!GameData.isClicked(1, 1)) {
            Integer[] tmp = {1,1};
            return tmp;
        }
        if (!GameData.isClicked(0, 0)) {
            Integer[] tmp = {0,0};
            return tmp;
        }
        if (!GameData.isClicked(0, 2)) {
            Integer[] tmp = {0,2};
            return tmp;
        }
        if (!GameData.isClicked(2, 0)) {
            Integer[] tmp = {2,0};
            return tmp;
        }
        if (!GameData.isClicked(2, 2)) {
            Integer[] tmp = {2,2};
            return tmp;
        }
       int res =  (int)(Math.random() * s);
       return available.get(res);
    }
}
