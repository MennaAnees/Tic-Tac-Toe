/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label label;
    @FXML
    private ImageView exit;
    @FXML
    private ImageView tryAgain;
    @FXML
    private ImageView save;
    @FXML
    private ImageView img;
    
    boolean saved = false;
    Player winner;
    
    FXMLLoader fxmlLoaderMain;
    FXMLLoader fxmlLoaderUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        models.GameData.networkChoiceFlag = true;
        fxmlLoaderMain = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
        fxmlLoaderMain.setController(new controllers.NetworkModeController());
        fxmlLoaderUser = new FXMLLoader(getClass().getResource("/views/user_into.fxml"));
        fxmlLoaderUser.setController(new controllers.UserInfoController());
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root;
        try {
            root = (Parent) fxmlLoaderUser.load();
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
        try {
            GameData.server = new ServerSocket(65432);
        } catch (IOException ex) {
            System.out.println("cann't create socket");
//            Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameData.dgListener = new DatagramListener(true, new ArrayList<models.Peer>());
        GameData.dgListener.start();
        
        try {
            while(models.GameData.networkChoiceFlag) {
                GameData.connectionSocket = GameData.server.accept();
                System.out.println("omran");
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
                        GameData.dgListener.stop();
                        GameData.player2 = new models.Player(peer.name);
                        System.out.println("game hi");
                        GameData.ps = new PrintStream(GameData.connectionSocket.getOutputStream());
//            System.out.println(GameData.player1.name);
//                        GameData.ps.println(GameData.player1.name);
                        Node node = (Node) event.getSource();
                        Scene scene = node.getScene();
                        GameData.networkChoiceFlag = false;
                        GameData.moveAllowance = true;
                        GameData.ps.println("start");  // 2nd 1st
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
                        fxmlLoader.setController(new controllers.NetworkModeController());
                        Parent root = (Parent) fxmlLoader.load();
                        scene.setRoot(root);
                    }
                }
            } catch(Exception e) { System.out.println("exception"); }
    }
    
    @FXML
    private void handleJoinAction(ActionEvent event) {
        models.GameData.moveAllowance = false;
        models.GameData.isServer = false;
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
            while(servers.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } ;
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
                    System.out.println("connecting to server");
                    System.out.println(choice.toString().substring(choice.toString().lastIndexOf("@") + 2));
                    GameData.connectionSocket = new Socket(choice.toString().substring(choice.toString().lastIndexOf("@") + 2), 65432);
                    GameData.dis = new DataInputStream(GameData.connectionSocket.getInputStream());
                    GameData.player2 = new models.Player(choice.toString().substring(0,choice.toString().indexOf("@") - 1).trim());
                    System.out.println(choice.toString().indexOf("@") - 1);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            GameData.ps = new PrintStream(GameData.connectionSocket.getOutputStream());
//            System.out.println(GameData.player1.name);
            GameData.ps.println(GameData.player1.name); // 1st 1st
            System.out.println(GameData.player1.name+"175"); // 1st 1st
            GameData.moveAllowance = false;
            GameData.ipScannerThread.stop();
            GameData.dgListener.stop();
            GameData.dgClient.stop();
            while(GameData.networkChoiceFlag) {
                String msg = GameData.dis.readLine();
                System.out.println(msg + "HHH");
                if (msg.indexOf("start") == 0) {
                    System.out.println(msg + "zzz");
                    Parent root = (Parent) fxmlLoaderMain.load();
                    Node node = (Node) event.getSource();
                    Scene scene = node.getScene();
                    GameData.networkChoiceFlag = false;
                    scene.setRoot(root);
                }
            }
        } catch(IOException e) { System.out.println("exception"); }
    }
    
    
     @FXML
    private void handleButtonAction(MouseEvent event) throws IOException, SQLException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = stage.getScene();
        String id = node.getId();
        if (id.equals("exit")) {
            System.exit(0);
        }
        else if (id.equals("tryAgain")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainGame.fxml"));
           if(GameData.getMode()==1){fxmlLoader.setController(new controllers.SingleModeController1());}
           if(GameData.getMode()==2){fxmlLoader.setController(new controllers.LocalPlayersController());}
            
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);
        }
        else if (id.equals("home")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Entry.fxml"));
            fxmlLoader.setController(new controllers.EntryController());
            Parent root = (Parent) fxmlLoader.load();
            scene.setRoot(root);

        }
        else if (id.equals("save") && !saved) {
            String moves = String.join(":", GameData.getMoves());              
            DataBaseMainupulation db;
            try {
                db = new DataBaseMainupulation();
                db.insert(GameData.player1.name, GameData.player2.name, moves, winner.name);
                db.closeConn();
                save.setImage(new Image ("/views/imgs/saveok.png"));
                saved = true;
                System.out.println(saved);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LocalPlayersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    
}
