public class Events extends Tasks{
    private String from;
    private String to;

    public Events(String name, String from, String to) {
        super(name, "E");
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
