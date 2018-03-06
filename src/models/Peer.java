package models;

import java.io.*;
import java.net.*;
import java.util.*;

public class Peer {
	public InetAddress ip;
	// public String port;
	public String name;

	public Peer(String pname, InetAddress pip) {
		this.name = pname;
		this.ip = pip;
	}
}