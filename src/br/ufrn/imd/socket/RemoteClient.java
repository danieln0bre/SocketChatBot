package br.ufrn.imd.socket;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteClient {

    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry(null);

            // Lookup the remote object from the registry
            IChatBotServeRMI stub = (IChatBotServeRMI) registry.lookup("IChatBotServe");

            // Invoke the method on the remote object
            String response = stub.responseChatBot("mensagem");
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}