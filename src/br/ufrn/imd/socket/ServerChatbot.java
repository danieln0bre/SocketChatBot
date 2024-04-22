package br.ufrn.imd.socket;

import java.io.*;
import java.net.*;

public class ServerChatbot {
	private ServerSocket serverSocket;
	private int port;
	private String serviceName = "ServiceChatbot";  // Nome do serviço
	private String serviceHost = "localhost";       // Host onde o serviço está rodando
	private int nameServicePort = 5050;             // Porta do serviço de nomes

	public ServerChatbot(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		System.out.println("Server running on port " + port);
	}

	public void registerService() throws IOException {
		Socket socket = new Socket(serviceHost, nameServicePort);
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

		out.writeUTF("register");
		out.writeUTF(serviceName);
		out.writeUTF(serviceHost);
		out.writeInt(port);
		out.flush();

		String response = in.readUTF();  // Leitura da resposta do serviço de nomes
		System.out.println("Registration response: " + response);

		out.close();
		in.close();
		socket.close();
	}

	public void start() throws IOException {
		registerService();  // Registra o serviço antes de iniciar
		while (true) {
			Socket clientSocket = serverSocket.accept();
			new Thread(new ClientHandler(clientSocket)).start();
		}
	}

	private class ClientHandler implements Runnable {
		private Socket clientSocket;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
				 ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

				String message = input.readUTF();
				System.out.println("Received: " + message);

				String chatBotResponse = ChatBot.chatBotResponse(message);

				output.writeUTF("ChatBot: " + chatBotResponse);
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
	}

	public static void main(String[] args) throws IOException {
		ServerChatbot server = new ServerChatbot(6070);
		server.start();
	}
}
