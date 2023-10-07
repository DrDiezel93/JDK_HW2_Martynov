import client.Client;
import client.ClientGUI;
import server.Log;
import server.Server;
import server.SeverGUI;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new SeverGUI(), new Log());
        new Client(new ClientGUI(server), server);
        new Client(new ClientGUI(server), server);
    }
}