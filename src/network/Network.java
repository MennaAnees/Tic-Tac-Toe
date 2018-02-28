package network;

import java.io.*;
import java.net.*;
import java.util.*;

public class Network {

	static String displayInterfaceInformation() throws SocketException {
		// System.out.printf("Display name: %s\n", netint.getDisplayName());
		// System.out.printf("Name: %s\n", netint.getName());
		String result = "";
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets)) {
		    // displayInterfaceInformation(netint);

			Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
			boolean flag = false;
			for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			    // System.out.printf("InetAddress: %s\n", inetAddress);
			    if(flag && !inetAddress.toString().substring(inetAddress.toString().lastIndexOf('/') + 1).equals("127.0.0.1")) {
				    System.out.println(inetAddress.toString().substring(inetAddress.toString().lastIndexOf('/') + 1));
				    result += inetAddress.toString().substring(inetAddress.toString().lastIndexOf('/') + 1);
				    flag = false;
				    // System.out.println(inetAddress);
			    } else { flag = true; }
			}
			// System.out.printf("\n");
		}
		return result;

	}

	public static ArrayList<String> printReachable(String ipAddress) {
		ArrayList<String> ips = new ArrayList<String>();
		String ipBase = ipAddress.substring(0, ipAddress.lastIndexOf('.')) + ".";
		for (int i = 0; i < 256; i++) {
			String testAddress = ipBase + i;
			try {
				if(InetAddress.getByName(testAddress.toString()).isReachable(50)) {
					System.out.println(testAddress);
					ips.add(testAddress);
				}
			} catch(UnknownHostException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return ips;
	}
}


class Listener implements Runnable {

	byte[] receiveData;
	DatagramPacket receivePacket;
	DatagramSocket listenerSocket;

	Listener() {
		try {
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
				System.out.println(new String(receivePacket.getData()));
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
		sendMessage = "hello".getBytes();
		for(String ip : ips) {
			System.out.println("hi");
			try {
				sendPacket = new DatagramPacket(sendMessage, sendMessage.length, InetAddress.getByName(ip), 5555);
				clientSocket.send(sendPacket);
				System.out.println("client send");
			} catch(Exception e) {}
			// String reply = new String(receivePacket.getData());
			// System.out.println("FROM SERVER:" + modifiedSentence);
		}
		clientSocket.close();
	}
}

class Test {
	public static void main(String[] args) {
		Runnable server = new Listener();
		Thread serverThread = new Thread(server);
		serverThread.start();

		try {
			Client client = new Client(Network.printReachable(Network.displayInterfaceInformation()));
		} catch(Exception e) {}
	}
}
