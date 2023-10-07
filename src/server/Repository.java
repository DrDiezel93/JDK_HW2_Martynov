package server;

public interface Repository {
    public void saveInLog(String text, Server server);
    public String readLog(Server server);
}
