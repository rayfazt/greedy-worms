package za.co.entelect.challenge.command;

public class SnowballCommand implements Command {

    private final int x;
    private final int y;

    public SnowballCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String render() { return String.format("snowball %d %d", x, y); }
}
