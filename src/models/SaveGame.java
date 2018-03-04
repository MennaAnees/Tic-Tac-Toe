/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author omran
 */
public class SaveGame {
    public String player1;
    public String player2;
    public ArrayList<int []> moves = new ArrayList<int []>();
    public String winner;
    public Timestamp time;

    public SaveGame(String p1, String p2, String m, String w, Timestamp t) {
        player1 = p1;
        player2 = p2;
        winner = w;
        time = t;
        StringTokenizer input = new StringTokenizer(m,":");
        while(input.hasMoreTokens()){
            String tmp = input.nextToken();
            int xPos = Character.getNumericValue(tmp.charAt(0));
            int yPos = Character.getNumericValue(tmp.charAt(1));
            int [] arr =  {xPos, yPos};
            moves.add(arr);
        }
    }
    
    
//    public static void main(String[] args) {
//        SaveGame s = new SaveGame("pla1", "pla2", "00:12:22", "win");
//        System.err.println(s.moves.get(0)[1]);
//    }
    
}
