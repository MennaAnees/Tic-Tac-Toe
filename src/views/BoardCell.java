/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.ArrayList;
import javafx.scene.control.Button;

/**
 *
 * @author omran
 */
public class BoardCell extends Button{
    private static int moveCounter = 1;
    private static int moveArray [][] = {{10,10,10},{10,10,10},{10,10,10}};
    private boolean isClicked = false;

    private int xPos;
    private int yPos;
    ArrayList<Integer> a ;
    public BoardCell(int x, int y) {
        xPos = x;
        yPos = y;
        this.setOnAction(e -> {
            if (!isClicked) {
                isClicked = true;
                if(moveCounter%2 == 0){
                    this.setText("O");
                    moveArray[xPos][yPos] = 1;

                }
                else{
                    this.setText("X");
                    moveArray[xPos][yPos] = 2;

                }
                moveCounter++;
                System.out.println(moveArray[xPos][yPos]);
            }
        });
    }
    
}

