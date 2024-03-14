package br.ufrn.imd.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket server;
	
	public void initAndStart() throws Exception {
		server = new ServerSocket(3030);
		System.out.println("Server is running...");
		while(true) {
			Socket clientSocket = server.accept();
			System.out.println("Client is connected");
			readMessageFromSocket(clientSocket);
		}
	}
	
	private void readMessageFromSocket(Socket clientSocket) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		for(int chr = reader.read(); reader.ready(); chr = reader.read()) {
			System.out.println((char) chr);
		}
	}
	
	public void close() {
		if(server != null) {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		
		try {
			server.initAndStart();
		}catch(Exception e) {
			server.close();
		}
	}
}
