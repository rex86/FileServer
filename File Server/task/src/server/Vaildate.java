package server;

public class Vaildate {

    public static boolean isValidFilename(String filename){
        StringBuffer sb = new StringBuffer();
        boolean result=false;
        System.out.println(filename.contains("file"));
        if(!filename.contains("file")) result=false;
        for (int i=0;i<filename.length();i++){

            if(Character.isDigit(filename.charAt(i))) sb.append(filename.charAt(i));
        }
        if(Integer.parseInt(sb.toString()) >0 && Integer.parseInt(sb.toString())<11) result=true;

        return result;

    }
    public static boolean isValidInput(String input){
        return input.length()>0;
    }
}
