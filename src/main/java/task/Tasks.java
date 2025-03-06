package task;

/**
 * The {@code Tasks} class serves as an abstract base class for different types of tasks.
 * It provides common attributes such as task description, mark status, and category.
 * Subclasses must implement the {@code toFileFormat()} method to define how tasks are stored.
 */
public abstract class Tasks {
    public String task; // Task description
    public boolean mark; // Mark status (true = completed, false = not completed)
    public String category; // Task category (e.g., "T" for Todo, "D" for Deadline, "E" for Event)

    /**
     * Converts the task into a formatted string for storage purposes
     * This method must be implemented by subclasses
     *
     * @return A string representation of the task for saving to a file.
     */
    public abstract String toFileFormat();

    /**
     * Returns a completed status of a task
     *
     * @return "X" if completed, " " if not completed.
     */
    public String marked() {
        return (mark) ? "X" : " ";
    }

    /**
     * Constructs a {@code Tasks} object with a name, category, and mark status.
     *
     * @param name     The description of the task.
     * @param category The category of the task (T = Todo, D = Deadline, E = Event).
     * @param mark     {@code true} if the task is marked completed, {@code false} otherwise.
     */
    public Tasks(String name, String category, boolean mark
    ) {
        this.task = name;
        this.mark = mark;
        this.category = category;
    }

    /**
     * Sets the mark status of the task.
     *
     * @param mark {@code true} to mark as completed, {@code false} to unmark.
     */
    public void setMark(boolean mark) {
        this.mark = mark;
    }

    /**
     * Retrieves the name (description) of the task.
     *
     * @return The task description.
     */
    public String getName() {
        return task;
    }

    /**
     * Retrieves the category of the task.
     *
     * @return The task category (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */
    public String getCategory() {
        return category;
    }
}