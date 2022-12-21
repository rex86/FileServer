package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
// private static final String filesPath = System.getProperty("user.dir") +
//        File.separator + "src" + File.separator + "server" + File.separator + "data" + File.separator;

 static String path = "/home/rex/Programming/workplace/java/File Server/File Server/task/src/server/data/";
    public static boolean createFile(String filename, String data){

        try (FileWriter fileWriter = new FileWriter(path+filename)) {
            fileWriter.write(data);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static boolean deleteFile(String filename){

        return new File(path+filename).delete();
    }

    public static String readFile(String filename){

        try {
            return Files.readString(Path.of(path+filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean fileExists(String filename){
        File file = new File(path+filename);

        if(file.exists()) return true;

        return false;
    }

}
