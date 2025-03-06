package task;

/**
 * The {@code Deadlines} class represents a task with a specific due date.
 * It extends the {@code Tasks} class and is categorized as a "D" (Deadline) task.
 */
public class Deadlines extends Tasks {
    private String by; // The deadline date/time for the task

    /**
     * Constructs a {@code Deadlines} task with a description, deadline, and completion status.
     *
     * @param task     The description of the deadline task.
     * @param deadline The due date/time of the task as a string.
     * @param mark     {@code true} if the task is marked as completed, {@code false} otherwise.
     */
    public Deadlines(String task, String deadline, boolean mark) {
        super(task, "D", mark);
        this.by = deadline;
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline as a string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Converts the {@code Deadlines} task into a formatted string suitable for storage.
     *
     * @return A string representation of the task in the format: {@code D | 1/0 | task description | deadline}.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (mark ? "1" : "0") + " | " + task + " | " + by;
    }

}
