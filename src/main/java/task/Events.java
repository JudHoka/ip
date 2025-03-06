package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Tasks {
    private LocalDateTime from;
    private LocalDateTime to;

    public Events(String task, LocalDateTime from, LocalDateTime to, boolean mark) {
        super(task, "E", mark);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return from.format(formatter);
    }

    public String getTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return to.format(formatter);
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return "E | " + (mark ? "1" : "0") + " | " + task + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
