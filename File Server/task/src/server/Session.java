package server;

import java.io.*;
import java.net.Socket;

class Session extends Thread {
    private final Socket socket;
    private final Closeable server;

    public Session(Closeable server, Socket socketForClient) {
        this.server = server;
        this.socket = socketForClient;
    }

    public void run() {


        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            String msg=input.readUTF();

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
                        server.close();
                        break;

            }
            socket.close();
            } catch(IOException e){
                e.printStackTrace();
            }

        }

}
