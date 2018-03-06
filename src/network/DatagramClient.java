/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import models.GameData;

/**
 *
 * @author a7mad
 */
public class DatagramClient {
    
    DatagramSocket clientSocket;
    DatagramPacket sendPacket;

    public DatagramClient(ArrayList<String> ips) {
        try {
            clientSocket = new DatagramSocket();
        } catch (Exception e) {
        }
        byte[] sendMessage = new byte[1024];
        sendMessage = new String("hello_XO:" + GameData.player1.name).getBytes();
        // sendMessage = "hello_XO".getBytes();

        for (String ip : ips) {
            try {
                sendPacket = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(ip), 5555);
                clientSocket.send(sendPacket);
                System.out.println("client ");
            } catch (Exception e) { System.out.println("exception"); }
        }
    }
}
