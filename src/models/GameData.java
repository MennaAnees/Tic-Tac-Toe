/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.io.*;
import java.net.*;
import java.util.*;
import network.*;

/**
 *
 * @author omran
 */
public class GameData {
    public static Player player1, player2;
    private static int mode;
    private static int moveCounter = 1;
    private static volatile int moveArray [][] = {{10,10,10},{10,10,10},{10,10,10}};
    private static boolean clickArray[][] = {{false,false,false},{false,false,false},{false,false,false}};
    private static ArrayList<String> moves = new ArrayList<String>();
    public static ServerSocket server;
    public static DatagramListener dgListener;
    public static DatagramClient dgClient;
    
//    public static DatagramSocket dgserver;
    public static Socket client;
//    public static DatagramSocket dgclient;
    public static Thread netListenThread;
    public static boolean networkChoiceFlag = false;
    
    public static void reset(){
        moveCounter = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                moveArray[i][j] = 10;
                clickArray[i][j] = false;
                moves.removeAll(moves);
            }
        }
    }
    
    public static ArrayList<String> getMoves(){return moves;}
    public static void setMoves(String s){moves.add(s);}
    
    public static int getMode(){return mode;}
    public static void setMode(int m){mode = m;}

    public static int getCounter(){return moveCounter;}
    public static void incCounter(){moveCounter++;}
    
    public static int getMoveArray(int i, int j){return moveArray[i][j];}
    public static void setMoveArray(int i, int j, int k){moveArray[i][j] = k;}
    
    public static void setClickArray(int i, int j){clickArray[i][j] = true;}
    public static boolean isClicked(int i, int j){return clickArray[i][j];}
    public static boolean[][] getClicked(){return clickArray;}
    public static void reverseClicked(int i, int j){clickArray[i][j] = false;}
    
    
    
    
    public static Player whoWin(){
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum1 += moveArray[i][j];
                sum2 += moveArray[j][i];
            }
            if(sum1==3 || sum2==3){return player2;}
            else if(sum1==6 || sum2==6){return player1;}
            else{sum1=0; sum2=0;}
        }
        if(moveArray[0][0]+moveArray[1][1]+moveArray[2][2]==3){return player2;}
        if(moveArray[0][0]+moveArray[1][1]+moveArray[2][2]==6){return player1;}
        if(moveArray[0][2]+moveArray[1][1]+moveArray[2][0]==3){return player2;}
        if(moveArray[0][2]+moveArray[1][1]+moveArray[2][0]==6){return player1;}
        if(moveCounter==10){return new Player("noWinner");}
        return null;
    }
}
