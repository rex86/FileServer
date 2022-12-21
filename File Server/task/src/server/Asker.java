package server;

import java.util.Scanner;

public class Asker {
    protected static Scanner scanner = new Scanner(System.in);

    public static String userInput(String question) {

        System.out.print(question);
        return scanner.nextLine().strip();
    }

    public static Scanner getScanner() {
        return scanner;
    }

}
