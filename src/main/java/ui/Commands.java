package ui;

import exceptions.AtomException;
import task.TaskList;
import misc.Others;

/**
 * The {@code Commands} class is responsible for processing user input
 * and delegating task-related operations to the {@code TaskList} class.
 * It determines the type of command and executes the corresponding action.
 */
public class Commands {

    /**
     * Checks whether the given input line corresponds to a valid task creation command.
     *
     * @param line The task input command entered by the user.
     * @return {@code true} if the command is a valid task creation command (todo, deadline, event),
     * {@code false} otherwise.
     */
    private static boolean isValidCreateTaskCommand(String line) {
        return line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ");
    }

    /**
     * Processes a user command and performs the corresponding task operation.
     * Supports listing tasks, marking/unmarking tasks, removing tasks,
     * and creating new tasks.
     *
     * @param line The user command to process.
     */
    public static void processCommand(String line) {
        if (line.equals("list")) {
            TaskList.printList();
        } else if (line.startsWith("mark ")) {
            TaskList.markTask(line, true);
        } else if (line.startsWith("unmark ")) {
            TaskList.markTask(line, false);
        } else if (line.startsWith("remove ")) {
            TaskList.removeTaskFromList(line);
        } else if (line.startsWith("find ")) {
            TaskList.findTask(line);
        } else if (isValidCreateTaskCommand(line)) {
            System.out.println(Others.LINE_SEPARATOR);
            TaskList.createTask(line);
            System.out.println(Others.LINE_SEPARATOR);
        } else {
            AtomException.notImplemented();
        }
    }
}
