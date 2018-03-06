package network;

import java.io.*;
import java.net.*;
import java.util.*;
import models.GameData;
import models.Peer;


public class DatagramListener extends Thread {
// public class DatagramListener implements Runnable {

    ArrayList<models.Peer> servers;
    byte[] receiveData;
    DatagramPacket receivePacket;
    DatagramSocket listenerSocket;
    boolean isServer;

    public DatagramListener(boolean pisServer, ArrayList<models.Peer> pservers) {
        try {
            this.isServer = pisServer;
            this.servers = pservers;
            listenerSocket = new DatagramSocket(5555);
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
        } catch(Exception e) {}
    }
    
    public static void sendMessage(String message, String ip, int port) throws SocketException, IOException {
        DatagramPacket sendPacket;
        // byte[] sendData = new byte[1024];
        byte[] sendData = message.getBytes();
        (new DatagramSocket()).send(new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), port));
    }
    
    public void run() {
        try {
            while(true) {
                System.out.println("listening");
                listenerSocket.receive(receivePacket);
                String message = new String(receivePacket.getData());
                System.out.println(message);
                if(message.indexOf("hello_XO:") == 0) {
                    // servers.add(receivePacket.getAddress());
                    System.out.println(isServer);
                    if(isServer) {
                        System.out.println("hi there");
                        System.out.println(receivePacket.getAddress().toString());
                    	sendMessage("I am server:" + GameData.player1.name, receivePacket.getAddress().toString().substring(1), 5555);
                    }
                } else if(message.indexOf("I am server:") == 0 && isServer == false) {
                    System.out.println("server added");
                    this.servers.add(new models.Peer(message.substring(12), receivePacket.getAddress()));
                    System.out.println(message.substring(12) + receivePacket.getAddress().toString());
                }
                	
                System.out.println(message.substring(12));
//                Thread.sleep(1000);
            }
        } catch(Exception e) {}
    }
}
