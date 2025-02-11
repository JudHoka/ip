package atom.ui;

public class Deadlines extends Tasks {
    private String by;

    public Deadlines(String task, String deadline) {
        super(task, "D");
        this.by = deadline;
    }

    public String getBy() {
        return by;
    }

}
