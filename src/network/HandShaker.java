package network;

import java.io.*;
import java.net.*;
import java.util.*;


public class HandShaker {
	public static void main(String[] args) {
		Runnable server = new Listener();
		Thread serverThread = new Thread(server);
		serverThread.start();

		try {
			Client client = new Client(IpScanner.printReachable(IpScanner.displayInterfaceInformation()));
		} catch(Exception e) {}
	}
}

class Listener implements Runnable {

	ArrayList<InetAddress> gamers;
	byte[] receiveData;
	DatagramPacket receivePacket;
	DatagramSocket listenerSocket;

	Listener() {
		try {
			ArrayList<InetAddress> gamers = new ArrayList<InetAddress>();
			listenerSocket = new DatagramSocket(5555);
			receiveData = new byte[1024];
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
		} catch(Exception e) {}
	}

	public void run() {
		try {
			while(true) {
				System.out.println("listening");
				listenerSocket.receive(receivePacket);
				String message = new String(receivePacket.getData());
				if(message.equals("hello_XO")) {
					gamers.add(receivePacket.getAddress());
				}
				System.out.println(message);
				Thread.sleep(1000);
			}
		} catch(Exception e) {}
	}
}

class Client {

	DatagramSocket clientSocket;
	DatagramPacket sendPacket;

	Client(ArrayList<String> ips) {
		try {
			clientSocket = new DatagramSocket();
		} catch(Exception e) {}
		byte[] sendMessage = new byte[1024];
		sendMessage = "hello_XO".getBytes();

		for(String ip : ips) {
			try {
				sendPacket = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(ip), 5555);
				clientSocket.send(sendPacket);
				System.out.println("client send");
			} catch(Exception e) {}
		}
		clientSocket.close();
	}
}

