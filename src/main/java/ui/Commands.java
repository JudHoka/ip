package ui;

import exceptions.AtomException;
import task.TaskList;
import misc.Others;

public class Commands {
    private static boolean isValidCreateTaskCommand(String line) {
        return line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ");
    }

    public static void processCommand(String line) {
        if (line.equals("list")) {
            TaskList.printList();
        } else if (line.startsWith("mark ")) {
            TaskList.markTask(line, true);
        } else if (line.startsWith("unmark ")) {
            TaskList.markTask(line, false);
        } else if (line.startsWith("remove ")) {
            TaskList.removeTaskFromList(line);
        } else if (isValidCreateTaskCommand(line)) {
            System.out.println(Others.LINE_SEPARATOR);
            TaskList.createTask(line);
            System.out.println(Others.LINE_SEPARATOR);
        } else {
            AtomException.notImplemented();
        }
    }
}
