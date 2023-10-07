package server;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeverGUI extends JFrame implements ServerView {

    private Server server;

    private Repository repository;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    JButton btnStart, btnStop;
    JTextArea log;
    boolean connect;

    public SeverGUI() {
        this.server = new Server(this, repository);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        add(createLog());
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connect){
                    appendLog("Сервер уже был запущен");
//                    System.out.println(server.work);
                } else {
                    connect = true;
                    appendLog("Сервер запущен!");
//                    System.out.println(server.work);
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!connect){
                    appendLog("Сервер уже был остановлен");
                } else {
                    connect = false;
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
    @Override
    public void appendLog(String text) {
        log.append(text + "\n");
    }

    @Override
    public boolean connect() {
        return connect;
    }

    @Override
    public void serverStop() {
        connect = false;
    }
}
