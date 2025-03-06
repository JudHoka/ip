package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Tasks {
    private LocalDateTime by;

    public Deadlines(String task, LocalDateTime deadline, boolean mark) {
        super(task, "D", mark);
        this.by = deadline;
    }

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return by.format(formatter);
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return "D | " + (mark ? "1" : "0") + " | " + task + " | " + by.format(formatter);
    }
}
