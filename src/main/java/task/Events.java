package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Events} class represents a task that occurs within a specific time range.
 * It extends the {@code Tasks} class and is categorized as an "E" (Event) task.
 */
public class Events extends Tasks {
    private LocalDateTime from; // The starting time of the event
    private LocalDateTime to;   // The ending time of the event

    /**
     * Constructs an {@code Events} task with a description, start time, end time, and completion status.
     *
     * @param task The description of the event task.
     * @param from The start time of the event as a LocalDateTime.
     * @param to   The end time of the event as a LocalDateTime.
     * @param mark {@code true} if the task is marked as completed, {@code false} otherwise.
     */
    public Events(String task, LocalDateTime from, LocalDateTime to, boolean mark) {
        super(task, "E", mark);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time as a LocalDateTime.
     */
    public String getFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return from.format(formatter);
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a LocalDateTime.
     */
    public String getTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return to.format(formatter);
    }

    /**
     * Converts the {@code Events} task into a formatted LocalDateTime suitable for storage.
     *
     * @return A string representation of the task in the format:
     *         {@code E | 1/0 | task description | from | to}.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm)");
        return "E | " + (mark ? "1" : "0") + " | " + task + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
