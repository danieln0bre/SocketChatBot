package br.ufrn.imd.socket;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRMI{

    public static void main(String[] args) {
        try {

            IChatBotServeRMI stub = new IChatBotServeRMIImpl();
            String objName = "rmi://localhost/Chat";

            LocateRegistry.createRegistry(1099);
            Naming.rebind(objName, stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
