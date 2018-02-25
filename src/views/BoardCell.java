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
    private boolean isClicked = false;
    private static int moveCounter = 1;

    ArrayList<Integer> a ;
    public BoardCell() {
        this.setOnAction(e -> {
            if (!isClicked) {
                isClicked = true;
                if(moveCounter%2 == 0){
                    this.setText("O");
                }
                else{
                    this.setText("X");
                }
                moveCounter++;
            }
        });
    }
    
}

