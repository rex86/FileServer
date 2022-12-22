package server;

import server.modes.ModeChanger;
import server.modes.TopLevelMode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void setIsEnd(boolean isEnd) {
        Main.isEndSrv = isEnd;
        System.out.println("isEndSrv: " + Main.isEndSrv);
    }

    private static boolean isEndSrv;
    public static void main(String[] args) {

        String address = "127.0.0.1";
        int port = 23456;

        System.out.println("Server started!");

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            boolean isEnd = isEndSrv;
            System.out.println("AfterTry isEndSrv: " + Main.isEndSrv);
            while (!Main.isEndSrv) {

                System.out.println("START LOOP");
                Session session = new Session(server.accept());
                session.start(); // it does not block this thread
                if(Main.isEndSrv == true) session.stop();
//                isEnd = session.isEnd();
//                System.out.println(isEnd);

            }
            System.out.println("AFTER LOOP");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
