package server;

public interface ServerView {
    void appendLog(String text);
    boolean connect ();
    void serverStop();
}
