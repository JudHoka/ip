package task;

public class Events extends Tasks {
    private String from;
    private String to;

    public Events(String task, String from, String to, boolean mark) {
        super(task, "E", mark);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (mark ? "1" : "0") + " | " + task + " | " + from + " | " + to;
    }
}
