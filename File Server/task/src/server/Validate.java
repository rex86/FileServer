package server;

public class Validate {

    public static boolean isValidFilename(String filename) {

        StringBuffer sb = new StringBuffer();
        boolean result = false;
        if (filename.matches("file([1-9]|10)$")) result = true;
        return result;

    }

}
