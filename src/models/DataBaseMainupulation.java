/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 *
 * @author omran
 */
public class DataBaseMainupulation {
    private static final String dbClassName = "com.mysql.jdbc.Driver";
    private static final String USER = "java";
    private static final String PASSWORD = "java1234";
    private static final String CONNECTION = "jdbc:mysql://127.0.0.1/TicTacToe";
    private Connection conn;
    private PreparedStatement pst;
    public DataBaseMainupulation() throws ClassNotFoundException, SQLException {
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user",USER);
        p.put("password",PASSWORD);
        p.put("useSSL", "false");
        conn = DriverManager.getConnection(CONNECTION,p);

    }
    
    public void insert(String p1, String p2, String moves, String winner) throws SQLException{
        pst = conn.prepareStatement("insert into saved_games set `player_1`=?, `player_2`=?, `winner`=?, `moves`=?");
        pst.setString(1, p1);
        pst.setString(2, p2);
        pst.setString(3, winner);
        pst.setString(4, moves);
        pst.executeUpdate();
    }
    public void del(int id) throws SQLException{
        pst = conn.prepareStatement("delete from saved_games where `GID`=?");
        pst.setString(1, String.valueOf(id));
        pst.executeUpdate();
    }
    
    public ResultSet get() throws SQLException{
        pst = conn.prepareStatement("select * from saved_games");
        ResultSet rs = pst.executeQuery();
        return rs;
    }
    
    public void closeConn() throws SQLException{conn.close();}
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DataBaseMainupulation m = new DataBaseMainupulation();
//        m.insert("aa", "aaaaa", "aaaa", "aaaas");
    ResultSet rs = m.get();
     while (rs.next()){
        int id = rs.getInt("GID");
        String player1 = rs.getString("player_1");
        String player2 = rs.getString("player_1");
        String moves = rs.getString("moves");
        String winner = rs.getString("winner");
        Timestamp timestamp = rs.getTimestamp("timestamp");
//         String email = rs.getString("email");
//         int roomNum = rs.gtInt("Room_no");
         System.out.println(id+"aaa"+timestamp+"   "+moves);
       }
        
    }

}
