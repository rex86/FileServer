package client;

import server.Asker;
import server.FileManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static FileManager fileManager;
    static String responseTemplate="The response says that ";
    static String userInput="";
    static String message="";
//    static DataInputStream input;
//    static DataOutputStream output;
    public static void main(String[] args) {

        String address = "127.0.0.1";
        int port = 23456;
        System.out.println("Client started!");
        String actionTemplate="Enter action (1 - get a file, 2 - create a file, 3 - delete a file):";



        try (Socket socket = new Socket(InetAddress.getByName(address), port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            userInput = Asker.userInput(actionTemplate);

            String [] createdFileString={};


            switch (userInput){
                case "1":
                    //GET a file
                    createdFileString = printFile();
                    output.writeUTF(createdFileString[0]);
                    output.writeUTF(createdFileString[1]);
                    System.out.println("The request was sent.");

                    String httpCode = input.readUTF();
//                    System.out.printf("debug: %s\n",httpCode);
                    if ("200".equals(httpCode)) {
                        String fileContent = input.readUTF();
                        System.out.printf("The content of the file is: %s\n", fileContent);
                    } else if ("404".equals(httpCode)) {
                        System.out.println(responseTemplate+"the file was not found!");
                    }
                    break;
                case "2":
                    //CREATE a file
                    createdFileString = createFile();
                    output.writeUTF(createdFileString[0]);
                    output.writeUTF(createdFileString[1]);
                    output.writeUTF(createdFileString[2]);
                    System.out.println("The request was sent.");
                    httpCode = input.readUTF();

                    if ("200".equals(httpCode)) {
                        System.out.println(responseTemplate+"file was created!");
                    }
                    break;
                case "3":
                    //DELETE a file
                    String fileName = Asker.userInput("Enter filename:");
                    output.writeUTF("DELETE");
                    output.writeUTF(fileName);
                    System.out.println("The request was sent.");
                    httpCode = input.readUTF();

                    if ("200".equals(httpCode)) {
                        System.out.println(responseTemplate+"the file was successfully deleted!");
                    }else if ("404".equals(httpCode)) {
                        System.out.println(responseTemplate+"the file was not found!");
                    }
                    break;

                case "exit":
                    output.writeUTF("exit");
                    System.out.println("The request was sent.");
                    break;
            }

//            String userInputFileBody = Asker.userInput(actionTemplate);
//            String sentMessage="Give me everything you have!";




        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void deleteFile() {

    }

    private static String[] createFile() {
        String fileName = Asker.userInput("Enter filename:");
        String fileContent = Asker.userInput("Enter file content:");
        String [] result = {"PUT",fileName,fileContent};


        if("200".equals(message)){
            System.out.println(responseTemplate + "the file was created!");
        } else if ("403".equals(message)) {
            System.out.println(responseTemplate + "creating the file was forbidden!");

        }
        return result;
    }

    private static String[] printFile() {
        String fileName = Asker.userInput("Enter filename:");

        String[] result = {"GET", fileName};

//        if("200".equals(message)){
//            System.out.printf("The content of the file is: %s",message);
//        }
        return result;
    }
}
