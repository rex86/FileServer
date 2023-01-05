package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class Session extends Thread {
    private final Socket socket;

    public AtomicBoolean getRunning() {
        return running;
    }

    private final AtomicBoolean running = new AtomicBoolean(true);

    public Session(Socket socketForClient) {
        this.socket = socketForClient;
//        this.isEnd = isEnd;
    }

//    @Override
//    public void interrupt() {
//        running.set(false);
//    }

    public void run() {


        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

//            running.set(true);

//            isEnd.set(false);
            String msg=input.readUTF();

//            System.out.println("COMM: " + msg);

                File file;
                switch (msg) {
                    case "PUT":
                        //Create file
                        String fileName = input.readUTF();
                        String fileContent = input.readUTF();
                        if (FileManager.createFile(fileName, fileContent)) {
                            output.writeUTF("200");
                        } else {
                            output.writeUTF("403");
                        }

                        break;
                    case "GET":

                        fileName = input.readUTF();
                        file = new File(fileName);

                        if (FileManager.fileExists(fileName)) {
//                            System.out.println("File exists: " + file.getName());
                            output.writeUTF("200");
                            output.writeUTF(FileManager.readFile(fileName));
                        } else {
                            output.writeUTF("404");

                        }

                        //GET file
                        break;
                    case "DELETE":
                        //DELETE file
                        fileName = input.readUTF();
                        if (FileManager.fileExists(fileName)) {
                            output.writeUTF("200");
                            FileManager.deleteFile(fileName);
                        } else {
                            output.writeUTF("404");
                        }
                        break;
                    case "exit":
//
////                        System.out.println("SRV exit");
                        running.set(false);
////                        socket.close();
////                        interrupt();
////                        stop();
////                        System.exit(0);
                        break;

//                msg=input.readUTF();
//            System.out.println("Received: " + msg);
//
//            String response="All files were sent!";
//            output.writeUTF(response);
//            System.out.println("Sent: " + response);
            }
            socket.close();
//            System.out.println("itt "+isEnd);
            } catch(IOException e){
                e.printStackTrace();
            }

        }

}
