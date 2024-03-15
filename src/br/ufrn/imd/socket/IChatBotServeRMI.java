package br.ufrn.imd.socket;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatBotServeRMI extends Remote {
    String responseChatBot(String question) throws RemoteException;
}
