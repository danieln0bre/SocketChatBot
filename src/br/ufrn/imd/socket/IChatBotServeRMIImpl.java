package br.ufrn.imd.socket;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IChatBotServeRMIImpl extends UnicastRemoteObject implements IChatBotServeRMI {

    public IChatBotServeRMIImpl() throws RemoteException {
    }

    public String responseChatBot(String question) throws RemoteException {
        return ChatBot.chatBotResponse(question);
    }
}
