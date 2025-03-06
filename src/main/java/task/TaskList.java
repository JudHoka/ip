package task;

import exceptions.AtomException;
import storage.Storage;
import misc.*;

import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a collection of tasks, including adding, removing,
 * marking tasks as completed, and displaying task details. It also interacts with storage
 * to persist tasks and provides utility methods for handling different task types.
 */
public class TaskList {
    public static ArrayList<Tasks> taskList;

    /**
     * Constructs a {@code TaskList} object and initializes the task list.
     *
     * @param list The list of tasks to be managed.
     */
    public TaskList(ArrayList<Tasks> list) {
        taskList = new ArrayList<>(list) ;
    }

    /**
     * Adds a task to the task list and saves the updated list.
     *
     * @param task The task to be added.
     */
    public static void addTaskToList(Tasks task) {
        taskList.add(task);
        System.out.println("    Nice! I've added this task:");
        printTask(taskList.size());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");

        Storage.saveTasks(taskList);
    }

    /**
     * Removes a task from the task list based on the given input number.
     *
     * @param words The input string containing the task number to be removed.
     */
    public static void removeTaskFromList(String words) {
        int taskNum = Parser.parseInt(words);

        if (taskNum == -1) {
            return;
        }

        System.out.println(Others.LINE_SEPARATOR);
        System.out.println("    Got it. I have removed this task:");
        printTask(taskNum);
        taskList.remove(taskNum - 1);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Others.LINE_SEPARATOR);

        Storage.saveTasks(taskList);
    }

    /**
     * Creates a new task and chooses between the three categories based on the given user input.
     *
     * @param line The command entered by the user to create a task.
     */
    public static void createTask(String line) {
        if (line.startsWith("todo ")) {
            createTodo(line);
        } else if (line.startsWith("deadline ")) {
            createDeadline(line);
        } else if (line.startsWith("event ")) {
            createEvent(line);
        }
    }

    /**
     * Creates a new Todo task.
     *
     * @param line The command containing the todo task details.
     */
    public static void createTodo(String line) {
        String taskDescription = line.substring(5).trim();

        if (taskDescription.isEmpty()) {
            AtomException.taskMissingDesc("t");
        } else {
            Todo newTask = new Todo(taskDescription, false);
            addTaskToList(newTask);
        }
    }

    /**
     * Creates a new Deadline task.
     *
     * @param line The command containing the deadline task details.
     */
    public static void createDeadline(String line) {
        String trimmedLine = line.substring(9).trim();

        if (trimmedLine.isEmpty()) {
            AtomException.taskMissingDesc("d");
        } else {
            String[] parts = trimmedLine.split(" /by ", 2);

            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                AtomException.taskIncomplete("d");
            } else {
                String taskDescription = parts[0].trim();
                String deadline = parts[1].trim();

                Deadlines newTask = new Deadlines(taskDescription, deadline, false);
                addTaskToList(newTask);
            }
        }
    }

    /**
     * Creates a new Event task.
     *
     * @param line The command containing the event task details.
     */
    public static void createEvent(String line) {
        String trimmedLine = line.substring(6).trim();

        if (trimmedLine.isEmpty()) {
            AtomException.taskMissingDesc("e");
        } else {
            String[] parts = trimmedLine.split(" /from | /to ", 3);

            if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                AtomException.taskIncomplete("e");
            } else {
                String taskDescription = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();

                Events newTask = new Events(taskDescription, from, to, false);
                addTaskToList(newTask);
            }
        }
    }

    /**
     * Marks or unmarks a task as completed.
     *
     * @param line       The command containing the task number.
     * @param markStatus {@code true} to mark the task as done, {@code false} to unmark it.
     */
    public static void markTask(String line, boolean markStatus) {
        int taskNum = Parser.parseInt(line);

        if (taskNum == -1) {
            return;
        }

        taskList.get(taskNum - 1).setMark(markStatus);
        Storage.saveTasks(taskList);
        System.out.println(Others.LINE_SEPARATOR);
        System.out.println("    " + (markStatus ? "Awesome! I've marked this task as done" : "Alright, this task has been unmarked") + ":");
        printTask(taskNum);
        System.out.println(Others.LINE_SEPARATOR);
        Storage.saveTasks(taskList);
    }

    /**
     * Prints the details of a specific task.
     *
     * @param taskNum The task number to be printed.
     */
    public static void printTask(int taskNum) {
        System.out.print("    " + taskNum + ".[" + taskList.get(taskNum - 1).category + "][" + taskList.get(taskNum - 1).marked() + "] " + taskList.get(taskNum - 1).getName());
        if (taskList.get(taskNum - 1).getCategory().equals("D")) {
            System.out.println(" (by: " + ((Deadlines) taskList.get(taskNum - 1)).getBy() + ")");
        } else if (taskList.get(taskNum - 1).getCategory().equals("E")) {
            System.out.println(" (from: " + ((Events) taskList.get(taskNum - 1)).getFrom() + " to: " + ((Events) taskList.get(taskNum - 1)).getTo() + ")");
        } else {
            System.out.println();
        }

    }

    /**
     * Prints the entire list of tasks.
     * If the task list is empty, an error message is displayed.
     */
    public static void printList() {
        if (taskList.isEmpty()) {
            AtomException.taskEmpty();
            return;
        }
        System.out.println(Others.LINE_SEPARATOR);
        System.out.println("    Below will be the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            printTask(i);
        }
        System.out.println(Others.LINE_SEPARATOR);
    }
}
