package network;

import java.io.*;
import java.net.*;
import java.util.*;

public class IpScanner {

	static String displayInterfaceInformation() throws SocketException {

		String result = "";
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

		for (NetworkInterface netint : Collections.list(nets)) {
			Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
			boolean flag = false;

			for (InetAddress inetAddress : Collections.list(inetAddresses)) { // to make it send to all but itself

			    if(flag && !inetAddress.toString().substring(inetAddress.toString().lastIndexOf('/') + 1).equals("127.0.0.1")) {
				    System.out.println(inetAddress.toString().substring(inetAddress.toString().lastIndexOf('/') + 1));
				    result += inetAddress.toString().substring(inetAddress.toString().lastIndexOf('/') + 1);
				    flag = false;
			    } else { flag = true; }
			}
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
