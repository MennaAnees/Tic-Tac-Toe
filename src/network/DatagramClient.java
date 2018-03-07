/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.GameData;

/**
 *
 * @author a7mad
 */
public class DatagramClient extends Thread {
    
    DatagramSocket clientSocket;
    DatagramPacket sendPacket;
//    ArrayList<String> ips;

    public DatagramClient() {
//    public DatagramClient(ArrayList<String> pips) {
//        ips = pips;
        
        try {
            clientSocket = new DatagramSocket();
        } catch (Exception e) {
        }
    }
    
    public void run() {
        byte[] sendMessage = new byte[1024];
        sendMessage = new String("hello_XO:" + GameData.player1.name).getBytes();
        // sendMessage = "hello_XO".getBytes();

        while (GameData.networkChoiceFlag) {
            if(!IpScanner.ips.isEmpty()) {
//                for (String ip : IpScanner.ips) {
                    try {
                        String ip = IpScanner.ips.get(0);
                        sendPacket = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(ip), 65432);
                        IpScanner.ips.remove(IpScanner.ips.indexOf(ip));
                        clientSocket.send(sendPacket);
                        System.out.println("client ");
                    } catch (Exception e) {
                        System.out.println("exception");
                    }
//                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("sleep exception");
//                    Logger.getLogger(DatagramClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
