package server.modes;

public class ModeChanger {

    public void run(Mode mode, String command) {
        try {
            mode.execute(command);
        } catch (UnsupportedCommandException ex) {
            System.out.printf("Unknown command: %s", ex.getCommand());
        }
    }
}
