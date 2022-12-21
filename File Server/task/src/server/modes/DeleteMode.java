package server.modes;

import java.util.List;

public class DeleteMode implements Mode {
    List<String> files;

    public DeleteMode(List<String> files) {
        this.files = files;
    }

    @Override
    public void execute(String command) {
        if(files.contains(command)){
            System.out.printf("The file %s was deleted\n",command);
            files.remove(command);
        }else {
            System.out.printf("The file %s not found\n",command);
        }

    }
}
