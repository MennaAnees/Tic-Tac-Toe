/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.List;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
import models.Player;

/**
 * FXML Controller class
 *
 * @author omran
 */
public class SingleModeController implements Initializable {

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
    
    private int[] computersMove = new int[2];
    Player winner =null;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        player1label.setText(GameData.player1.name+ ": X");
        player2label.setText(GameData.player2.name+ ": O");
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
            minimax(0,1);
            Parent p =  cell00.getParent();
            String id = "#cell"+computersMove[0]+computersMove[1];
            Button btn  =  (Button) p.lookup(id);
            if(winner==null){
            btn.fire();
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(GameData.getClicked()[i][j]);
            }
        }
        System.out.println(computersMove[0]+"      "+computersMove[1]);
        
        
    }
    
    

    public int minimax(int depth,int turn){
    //     HashMap<Integer,Integer> available=new HashMap<Integer,Integer>(); 
       
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
//        System.out.println(min);
        ArrayList<Integer[]> available = new ArrayList<Integer[]>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(GameData.getClicked()[i][j]==false){
                    Integer[] tmp = {i,j};
                    available.add(tmp);
                }
            }
        }
        if (available.isEmpty()) return 0;
        int currentScore;
        
        Integer[] integers ;
//        for (Integer[] integers : available) {
    //        System.out.println(Arrays.toString(integers));
          for (int i = 0; i < available.size(); ++i){
              integers = available.get(i);
           if (turn == 1) {
                GameData.setClickArray(integers[0],integers[1]);
                currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);
 
                if(currentScore >= 0){
                    if(depth == 0) {
                        computersMove[0] = integers[0];
                        computersMove[1] = integers[1];
                    }
                }
                if(currentScore == 1){GameData.reverseClicked(integers[0],integers[1]); break;}
                if(i == available.size()-1 && max < 0){
                    if(depth == 0){
                        computersMove[0] = integers[0];
                        computersMove[1] = integers[1];
                    }
                }
            } else if (turn == 2) {
                GameData.setClickArray(integers[0],integers[1]);
                currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if(min == -1){GameData.reverseClicked(integers[0],integers[1]); break;}
            }
           GameData.reverseClicked(integers[0],integers[1]);

        }

         return turn == 1?max:min;

    }

    
}
