public class Deadlines extends Tasks {
    private String by;

    public Deadlines(String name, String deadline) {
        super(name, "D");
        this.by = deadline;
    }

    public String getBy() {
        return by;
    }

}
