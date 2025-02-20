package task;

public class Todo extends Tasks {
    public Todo(String task, boolean mark) {
        super(task, "T", mark);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (mark ? "1" : "0") + " | " + task;
    }
}
