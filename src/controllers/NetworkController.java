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
    private void handleCancelAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        Stage stage = (Stage) scene.getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/user_info.fxml"));
        fxmlLoader.setController(new controllers.UserInfoController());
        Parent root = (Parent) fxmlLoader.load();
        scene.setRoot(root);
    }
    
    @FXML
    private void handleCreateAction(ActionEvent event) throws IOException {
        GameData.server = new ServerSocket(65432);
        models.GameData.networkChoiceFlag = true;
        GameData.dgListener = new DatagramListener(true, new ArrayList<models.Peer>());
        Thread dgThread = new Thread(GameData.dgListener);
        dgThread.start();
        
        try {
            while(models.GameData.networkChoiceFlag) {
                Socket s = GameData.server.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                Peer peer = new Peer(dis.readLine(), s.getInetAddress());
                Alert alert = new Alert(AlertType.CONFIRMATION, "player: " + peer.name + " @ " + peer.ip.toString().substring(1) + "wants to connect", ButtonType.NO, ButtonType.OK);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.NO) {
                        s.close();
                    } else {
                        System.out.println("game Start");
                        models.GameData.networkChoiceFlag = false;
                    }
                }
            } catch(Exception e) {}
    }
    
    @FXML
    private void handleJoinAction(ActionEvent event) throws InterruptedException {
        try {
            ArrayList<models.Peer> servers = new ArrayList<>();
//            System.out.println(servers.toString() + "hi");
//            ArrayList<String> ips = IpScanner.printReachable(IpScanner.displayInterfaceInformation());
            
//            for(String ip : ips) {
//                System.out.println(ip + "\n");
//            }

            GameData.dgListener = new DatagramListener(false, servers);
            GameData.dgListener.start();
            GameData.dgClient = new DatagramClient();
//            GameData.dgClient = new DatagramClient(ips);
            GameData.dgClient.start();
//            GameData.dgClient = new DatagramClient(new ArrayList<String>(Arrays.asList(
//                    "10.118.49.160",
//                    "10.118.49.27",
//                    "10.118.49.29")));
//            servers.add(new models.Peer("a", InetAddress.getByName("10.118.49.160")));
            ObservableList items = FXCollections.observableArrayList();
            do {
                Thread.sleep(1000);
            } while(servers.isEmpty());
            System.out.println("hi");
            for(models.Peer peer : servers) {
//                System.out.println(peer.name + "hi here 1");
                items.add(peer.name + " @ " + peer.ip.toString().substring(1));
            }
//            servers.addAll(Arrays.asList(new models.Peer("a", InetAddress.getByName("10.118.49.160"))));            
            ListView<String> list = new ListView<String>();
            list.setItems(items);
            
//            list.setCellFactory(param -> new ListCell() {
//                protected void updateItem(models.Peer peer, boolean empty) {
//                    super.updateItem(peer, empty);
//                    if (peer != null) {
//                        setText(peer.name);
//                    }
//                }
//            });

            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(list);
            dialog.getDialogPane().getButtonTypes().addAll(new ButtonType("ok", ButtonBar.ButtonData.OK_DONE));
            dialog.setResizable(true);
            dialog.setWidth(200);
            dialog.setHeight(400);
            
            dialog.setResultConverter(button -> {
                return list.getSelectionModel().getSelectedItem();
//                return new Result(list.getSelectionModel().getSelectedItem());
            });
            Optional result = dialog.showAndWait();
            GameData.networkChoiceFlag = false;
            System.out.println(result.toString());
            result.ifPresent(choice -> {
                try {
                    GameData.client = new Socket(choice.toString().substring(choice.toString().lastIndexOf("@") + 1), 65432);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
//            GameData.client = new Socket(, 65432);
            PrintStream ps = new PrintStream(GameData.client.getOutputStream());
            System.out.println(GameData.player1.name);
            ps.println(GameData.player1.name);
        } catch(IOException e) {}
    }

    
}
