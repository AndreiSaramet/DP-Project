package command;

public class CommandImpl implements Command {
    private final String message;
    private final Runnable function;

    public CommandImpl(final String message, final Runnable function) {
        this.message = message;
        this.function = function;
    }

    @Override
    public void execute() {
        this.function.run();
    }

    @Override
    public String getName() {
        return message;
    }
}
