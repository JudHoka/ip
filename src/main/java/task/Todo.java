package task;

/**
 * The {@code Todo} class represents a basic task without any specific deadline or event duration.
 * It extends the {@code Tasks} class and is categorized as a "T" (Todo) task.
 */
public class Todo extends Tasks {

    /**
     * Constructs a {@code Todo} task with the given task description and mark status.
     *
     * @param task The description of the todo task.
     * @param mark {@code true} if the task is marked as completed, {@code false} otherwise.
     */
    public Todo(String task, boolean mark) {
        super(task, "T", mark);
    }

    /**
     * Converts the {@code Todo} task into a formatted string suitable for storage.
     *
     * @return A string representation of the task in the format: {@code T | 1/0 | task description}.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (mark ? "1" : "0") + " | " + task;
    }
}
