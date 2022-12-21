package server.modes;



import server.Validate;

import java.util.List;

public class AddMode implements Mode {
    List<String> files;

    public AddMode(List<String> files) {
        this.files = files;
    }
    @Override
    public void execute(String command) {
//        System.out.println("Enter to add mode");
            String fileName=command;
            if(!files.contains(fileName) && Validate.isValidFilename(fileName)){
                files.add(fileName);
                System.out.printf("The file %s added successfully\n", fileName);
            }else  {
                System.out.printf("Cannot add the file %s\n",fileName);
            }

    }

}
