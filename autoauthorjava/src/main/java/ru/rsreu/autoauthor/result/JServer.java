package ru.rsreu.autoauthor.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JServer implements Runnable{
    ServerSocket ss;
    public Handler handler;
    static Socket s;
    static PrintWriter pw;
    boolean stop = true;


    public JServer(String unknownFileName) throws Exception {
        ss = new ServerSocket(8086);
        new Thread(this).start();
        s = new Socket("localhost", 2000);
        pw = new PrintWriter(s.getOutputStream());
        pw.write(unknownFileName);
        pw.flush();
        pw.close();
        s.close();
    }

    @Override
    public void run() {
        int i = 0;
        System.out.println("server startup.");
        while (stop) {
            try {
                Socket s = ss.accept();
                // each client a processing thread
                handler = new Handler(s, i);
                handler.start();
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        try {
            stop = false;
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
