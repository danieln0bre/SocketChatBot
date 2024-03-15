package br.ufrn.imd.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocket {
	
	private java.net.ServerSocket serverSocket;
	
	public void initAndStart() throws Exception {
		serverSocket = new java.net.ServerSocket(3030);
		System.out.println("Server is running...");
		while(true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client is connected");
			readMessageFromSocket(clientSocket);
		}
	}

	private void readMessageFromSocket(Socket clientSocket) throws Exception {

		while(clientSocket.isConnected()) {

			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String messageFromClient = reader.readLine();
			String chatBotResponse = ChatBot.chatBotResponse(messageFromClient);

			System.out.println("Received from client: " + messageFromClient);

			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(chatBotResponse);
			System.out.println("Sent to client: " + chatBotResponse);
		}
	}
	
	public void close() {
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ServerSocket serverSocket = new ServerSocket();
		
		try {
			serverSocket.initAndStart();
		}catch(Exception e) {
			serverSocket.close();
		}
	}
}
