package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

public class Main {



    public static AtomicReference<Boolean> isEndSrv;
    public static Session session;
    public static void main(String[] args) {

        String address = "127.0.0.1";
        int port = 23456;

        System.out.println("Server started!");

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {

//            System.out.println("AfterTry isEndSrv: " + Main.isEndSrv);
//            isEndSrv.set(false);

            boolean running = true;
            while (running) {

//                System.out.println(isEndSrv.get());
//                System.out.println("START LOOP");
                session = new Session(server.accept());
                session.start(); // it does not block this thread
                running = session.getRunning().get();
            }
            //                if(Main.isEndSrv == true) session.stop();
//                isEnd = session.isEnd();
//                System.out.println(isEnd);
//                System.out.println("in loop: " + isEndSrv.get());

//            while (session.isAlive());


//            System.out.println("AFTER LOOP");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
