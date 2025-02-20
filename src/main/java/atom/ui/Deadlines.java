package atom.ui;

public class Deadlines extends Tasks {
    private String by;

    public Deadlines(String task, String deadline, boolean mark) {
        super(task, "D", mark);
        this.by = deadline;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (mark ? "1" : "0") + " | " + task + " | " + by;
    }

}
