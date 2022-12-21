package server.modes;

public class UnsupportedCommandException extends RuntimeException {
    private final String command;

    public UnsupportedCommandException(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
