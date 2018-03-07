/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import models.GameData;

/**
 *
 * @author omran
 */
public class BoardCell extends Button{
    private static int moveCounter = 1;
    private static int moveArray [][] = {{10,10,10},{10,10,10},{10,10,10}};
    private boolean clickedFlag = false;

    private int xPos;
    private int yPos;
    ArrayList<Integer> a ;
    
    
    
    public BoardCell(int x, int y) {
        

            
        xPos = x;
        yPos = y;
        this.setOnAction(e -> {
            if (!clickedFlag) {
                clickedFlag = true;
                if(moveCounter%2 == 0){
                    this.setText("O");
                    moveArray[xPos][yPos] = 1;

                }
                else{
                    this.setText("X");
                    moveArray[xPos][yPos] = 2;

                }
                moveCounter++;
                String winner = whoWin();
                if (winner != null) {
                    System.out.println(whoWin());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Winner");
                    alert.setHeaderText(winner + " wins");
                    alert.showAndWait();
                }
            }
        });
    }
    

    
    static String whoWin(){
        


        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum1 += moveArray[i][j];
                sum2 += moveArray[j][i];
            }
            if(sum1==3 || sum2==3){return "O";}
            else if(sum1==6 || sum2==6){return "X";}
            else{sum1=0; sum2=0;}
        }
        if(moveArray[0][0]+moveArray[1][1]+moveArray[2][2]==3){return "O";}
        if(moveArray[0][0]+moveArray[1][1]+moveArray[2][2]==6){return "X";}
        if(moveArray[0][2]+moveArray[1][1]+moveArray[2][0]==3){return "O";}
        if(moveArray[0][2]+moveArray[1][1]+moveArray[2][0]==6){return "X";}
                     
                     
        return null;
   
    }    
    
    public boolean isClicked(){return clickedFlag;}



}       


