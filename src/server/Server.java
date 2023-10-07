package server;

import client.Client;
import client.ClientView;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private Repository repository;
    private ServerView serverView;
    List<Client> clients;

    public Server(ServerView serverView, Repository repository) {
        this.serverView = serverView;
        this.repository = repository;
        clients = new ArrayList<>();
    }

    public boolean connectUser(Client client){
        if (!serverView.connect()){
            return false;
        }
        clients.add(client);
        return true;
    }

    public String getHistory() {
        return repository.readLog(this);
    }

    public void sendMessage(String text){
        if (!serverView.connect()){
            return;
        }
        serverView.appendLog(text);
        answerAll(text);
        repository.saveInLog(text, this);
    }

    private void answerAll(String text){
        for (Client clients: clients){
           clients.printText(text);
        }
    }

    public void disconnectUser(Client client){
        if (client != null){
            serverView.appendLog(client.getName() + " " + "отключился");
        }
        answerAll(client.getName() + " " + "покинул чат");
        clients.remove(client);
        ServerStopped(clients);
    }

    public boolean serverWork(){
        return serverView.connect();
    }

    public void ServerStopped (List<Client> clients){
        if(clients.size() == 0){
            serverView.appendLog("Клиенты отсуствуют. Сервер остановлен");
            serverView.serverStop();
        }
    }
}
