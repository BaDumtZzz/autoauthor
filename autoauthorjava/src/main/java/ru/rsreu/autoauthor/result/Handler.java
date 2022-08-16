package ru.rsreu.autoauthor.result;

import java.io.*;
import java.net.Socket;

public class Handler extends Thread {
    Socket s;
    int id;
    public String message = "";

    public Handler(Socket s, int id) {
        this.s = s;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("in handling..");

        FileOutputStream fos = null;
        try {
            InputStream is = s.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "windows-1251"));
            // read from the client sent by file name
            message = in.readLine();
            System.out.println("read line " + id + " :" + message);
            System.out.println("done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // server do not hand off the socket or cheap Python error Errno 10054 will allow the client to switch off to Come
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
