public class Events extends Tasks {
    private String from;
    private String to;

    public Events(String task, String from, String to) {
        super(task, "E");
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
