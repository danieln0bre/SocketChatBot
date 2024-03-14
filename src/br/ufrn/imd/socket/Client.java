package br.ufrn.imd.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private Socket socket;
	
	public void init() throws Exception {
		socket = new Socket("127.0.0.1", 3030);
		System.out.println("Connecting to the server");
	}
	
	public void sendMessage(String msg) throws Exception {
		System.out.println("Sending message to the server");
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(msg);
	}
	
	public void close() {
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.init();
			client.sendMessage("Hello World");
			
		}catch(Exception e) {
			client.close();
		}
	}
}
