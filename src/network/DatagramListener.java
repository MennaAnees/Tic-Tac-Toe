package network;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
            if(models.GameData.isServer) {
                listenerSocket = new DatagramSocket(65432);
            } else {
                listenerSocket = new DatagramSocket(65431);
            }
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
            while(GameData.networkChoiceFlag) {
                System.out.println("listening");
                try {
                    listenerSocket.receive(receivePacket);
                } catch (IOException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "You are disconnected!", ButtonType.CLOSE);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.NO) {
                            try {
                                GameData.dgListener.stop();
                                if (GameData.isServer) {
                                    GameData.netListenThread.stop();
                                } else {
                                    GameData.dgClient.stop();
                                }
                            } catch (Exception e) {
                            }
                        }
                    });
                    Logger.getLogger(DatagramListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                String message = new String(receivePacket.getData());
                System.out.println(message);
                if(message.indexOf("hello_XO:") == 0) {
                    // servers.add(receivePacket.getAddress());
                    System.out.println(isServer);
                    if(isServer) {
                        System.out.println("hi there");
                        System.out.println(receivePacket.getAddress().toString());
                        try {
                            sendMessage("I am server:" + GameData.player1.name, receivePacket.getAddress().toString().substring(1), 65431);
                        } catch (IOException ex) {
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "You are disconnected!", ButtonType.CLOSE);
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.NO) {
                                    try {
                                        GameData.dgListener.stop();
                                        if (GameData.isServer) {
                                            GameData.netListenThread.stop();
                                        } else {
                                            GameData.dgClient.stop();
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            });
                            Logger.getLogger(DatagramListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if(message.indexOf("I am server:") == 0 && isServer == false) {
                    System.out.println("server added");
                    this.servers.add(new models.Peer(message.substring(12), receivePacket.getAddress()));
                    System.out.println(message.substring(12) + receivePacket.getAddress().toString());
                } else {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "You are disconnected!", ButtonType.CLOSE);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.NO) {
                            try {
                                GameData.dgListener.stop();
                                if(GameData.isServer) {
                                    GameData.netListenThread.stop();
                                } else {
                                    GameData.dgClient.stop();
                                }
                            } catch(Exception e) {}
                        }
                    });
                }
                	
                System.out.println(message.substring(12));
//                Thread.sleep(1000);
            }
    }
}
