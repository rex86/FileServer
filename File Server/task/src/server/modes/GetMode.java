package server.modes;

import java.util.List;

public class GetMode implements Mode{
    List<String> files;

    public GetMode(List<String> files) {
        this.files = files;
    }
    @Override
    public void execute(String command) {
       if(files.contains(command)){
           System.out.printf("The file %s was sent\n",command);
       }else {
           System.out.printf("The file %s not found\n",command);
       }
    }
}
