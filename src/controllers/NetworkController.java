/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory.Result;
import models.*;
import network.*;

/**
 * FXML Controller class
 *
 * @author a7mad
 */
public class NetworkController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        Stage stage = (Stage) scene.getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/user_info.fxml"));
        fxmlLoader.setController(new controllers.UserInfoController());
        Parent root;
        try {
            root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            System.out.println("cann't fetch fxml file");
//            Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleCreateAction(ActionEvent event) {
        models.GameData.moveAllowance = true;
        models.GameData.isServer = true;
        models.GameData.networkChoiceFlag = true;
        try {
            GameData.server = new ServerSocket(65432);
        } catch (IOException ex) {
            System.out.println("cann't create socket");
//            Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameData.dgListener = new DatagramListener(true, new ArrayList<models.Peer>());
        Thread dgThread = new Thread(GameData.dgListener);
        dgThread.start();
        
        try {
            while(models.GameData.networkChoiceFlag) {
                GameData.connectionSocket = GameData.server.accept();
                GameData.dis = new DataInputStream(GameData.connectionSocket.getInputStream());
                Peer peer = new Peer(GameData.dis.readLine(), GameData.connectionSocket.getInetAddress());
                Alert alert = new Alert(AlertType.CONFIRMATION, "player: " + peer.name + " @ " + peer.ip.toString().substring(1) + "wants to connect", ButtonType.NO, ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.NO) {
                        GameData.dis.close();
                        GameData.connectionSocket.close();
                    } else {
                        System.out.println("game Start");
                        models.GameData.networkChoiceFlag = false;
                        GameData.moveAllowance = true;
                        GameData.ipScannerThread.stop();
                        GameData.dgListener.stop();
                        GameData.player2.name = peer.name;
                    }
                }
            } catch(Exception e) { System.out.println("exception"); }
    }
    
    @FXML
    private void handleJoinAction(ActionEvent event) {
        models.GameData.moveAllowance = false;
        models.GameData.isServer = false;
        models.GameData.networkChoiceFlag = true;
        try {
            ArrayList<models.Peer> servers = new ArrayList<>();

            GameData.dgListener = new DatagramListener(false, servers);
            GameData.dgListener.start();
            GameData.dgClient = new DatagramClient();
            GameData.dgClient.start();
            GameData.ipScannerThread = new Thread(new Runnable() {
                public void run() {
                try {
                    IpScanner.printReachable(IpScanner.displayInterfaceInformation(), IpScanner.ips);
                } catch (SocketException ex) {
                    System.out.println("no reachable network");
//                    Logger.getLogger(DatagramClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
            GameData.ipScannerThread.start();
            ObservableList items = FXCollections.observableArrayList();
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while(servers.isEmpty());
            for(models.Peer peer : servers) {
                items.add(peer.name + " @ " + peer.ip.toString().substring(1));
            }
//            servers.addAll(Arrays.asList(new models.Peer("a", InetAddress.getByName("10.118.49.160"))));            
            ListView<String> list = new ListView<String>();
            list.setItems(items);
            
            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(list);
            dialog.getDialogPane().getButtonTypes().addAll(new ButtonType("ok", ButtonBar.ButtonData.OK_DONE));
            dialog.setResizable(true);
            dialog.setWidth(200);
            dialog.setHeight(400);
            
            dialog.setResultConverter(button -> {
                return list.getSelectionModel().getSelectedItem();
            });
            Optional result = dialog.showAndWait();
            System.out.println(result.toString());
            result.ifPresent(choice -> {
                try {
                    GameData.connectionSocket = new Socket(choice.toString().substring(choice.toString().lastIndexOf("@") + 1), 65432);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            GameData.ps = new PrintStream(GameData.connectionSocket.getOutputStream());
//            System.out.println(GameData.player1.name);
            GameData.ps.println(GameData.player1.name);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
            fxmlLoader.setController(new controllers.NetworkModeController());
            Parent root = (Parent) fxmlLoader.load();
            Node node = (Node) event.getSource();
            Scene scene = node.getScene();
            GameData.networkChoiceFlag = false;
            GameData.moveAllowance = false;
            GameData.ipScannerThread.stop();
            GameData.dgListener.stop();
            GameData.dgClient.stop();
            scene.setRoot(root);
        } catch(IOException e) { System.out.println("exception"); }
    }

    
}
