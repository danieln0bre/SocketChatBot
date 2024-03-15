package br.ufrn.imd.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private Socket socket;
	
	public void init() throws Exception {
		socket = new Socket("127.0.0.1", 3030);
		System.out.println("Connecting to the server");
	}
	
	public void sendMessage(String msg) throws Exception {
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(msg);

		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		String message = (String) ois.readObject();
		System.out.println(message);
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

			Scanner scanner = new Scanner(System.in);

			String input = scanner.nextLine();

			while(!input.equalsIgnoreCase("quit")) {
				client.sendMessage(input);

				input = scanner.nextLine();
			}

			System.out.println("End session!!");
			
		}catch(Exception e) {
			client.close();
		}
	}
}
