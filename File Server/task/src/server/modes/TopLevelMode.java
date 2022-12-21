package server.modes;

import server.Asker;
import server.Validate;
import java.util.ArrayList;
import java.util.List;

public class TopLevelMode implements Mode {

    List<String> files = new ArrayList<>();

    @Override
    public void execute(String command) {
        String input = "";
        String mode = "";
        String fileName = "";
        String[] splitted_input = new String[2];

        ModeChanger modeChanger = new ModeChanger();
        AddMode addMode = new AddMode(files);
        GetMode getMode = new GetMode(files);
        DeleteMode deleteMode = new DeleteMode(files);

        if ("topLevel".equals(command)) {
            while (true) {
                input = Asker.userInput("");
                splitted_input = input.split(" ");

                if(!"exit".equals(input) &&
                        splitted_input.length>1){

                    mode = splitted_input[0];
                    fileName = splitted_input[1];
                }else{
                    mode = "exit";
                }


                switch (mode) {
                    case "add":
                        modeChanger.run(addMode, fileName);
                        break;
                    case "get":
                        modeChanger.run(getMode, fileName);
                        break;
                    case "delete":
                        modeChanger.run(deleteMode, fileName);
                        break;
                    case "exit":
//                        System.out.println("EX");
                        System.exit(0);
                    default:
                        throw new UnsupportedCommandException(command);
                }
            }

        }
    }
}
