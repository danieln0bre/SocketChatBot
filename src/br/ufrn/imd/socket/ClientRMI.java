package br.ufrn.imd.socket;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            while(!input.equalsIgnoreCase("quit")) {

                IChatBotServeRMI iChatBotServeRMI = (IChatBotServeRMI) Naming.lookup("rmi://localhost:1099/Chat");
                System.out.println(iChatBotServeRMI.responseChatBot(input));

                input = scanner.nextLine();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
