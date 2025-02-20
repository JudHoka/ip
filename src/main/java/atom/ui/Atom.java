package atom.ui;

import exceptions.AtomException;
import storage.Storage;
import task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Atom {
    private static final String LINE_SEPARATOR = "  ____________________________________________________________";
    private static final String LOGO = """
                ____     _                        /\\   \s
               / __ \\   | |__   ____   __  __  \\ /  \\ / \s
              / /__\\ \\  |  __| / __ \\ |  \\/  | <      >\s
             / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\ \s
            /_/      \\_\\ \\____|\\____/ |_|  |_|    \\/   \s
            """;
    public static final ArrayList<Tasks> taskList = new ArrayList<>();

    private static boolean isInvalidNumber(int taskNumber){
        return taskNumber < 1 || taskNumber > taskList.size();
    }

    private static boolean isValidCreateTaskCommand(String line) {
        return line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ");
    }

    private static int parseInt(String line) {
        try {
            String[] words = line.split(" ");

            if (words.length < 2) {
                AtomException.numError("no number");
                throw new NumberFormatException();
            }

            if (!words[1].matches("\\d+")) {
                AtomException.numError("invalid number format");
                throw new NumberFormatException();
            }

            int taskNum = Integer.parseInt(words[1]);

            if (taskList.isEmpty()) {
                AtomException.numError("empty task list");
                throw new IllegalStateException();
            }

            if (taskNum > taskList.size() || taskNum <= 0) {
                AtomException.numError("out of bounds");
                throw new IndexOutOfBoundsException();
            }

            return taskNum;
        } catch (NumberFormatException | IndexOutOfBoundsException | IllegalStateException e) {
            return -1;
        }
    }

    private static void addTaskToList(Tasks task) {
        taskList.add(task);
        System.out.println("    Nice! I've added this task:");
        printTask(taskList.size());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");

        Storage.saveTasks(taskList);
    }

    private static void removeTaskFromList(String words) {
        int taskNum = parseInt(words);

        if(taskNum == -1){
            return;
        }

        System.out.println(LINE_SEPARATOR);
        System.out.println("    Got it. I have removed this task:");
        printTask(taskNum);
        taskList.remove(taskNum - 1);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);

        Storage.saveTasks(taskList);
    }

    private static void createTask(String line) {
        if (line.startsWith("todo ")) {
            createTodo(line);
        } else if (line.startsWith("deadline ")) {
            createDeadline(line);
        } else if (line.startsWith("event ")) {
            createEvent(line);
        }
    }

    private static void createTodo(String line) {
        String taskDescription = line.substring(5).trim();

        if (taskDescription.isEmpty()) {
            AtomException.taskMissingDesc("t");
        } else {
            Todo newTask = new Todo(taskDescription, false);
            addTaskToList(newTask);
        }
    }

    private static void createDeadline(String line) {
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

    private static void createEvent(String line) {
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

    private static void markTask(String line, boolean markStatus) {
        int taskNum = parseInt(line);

        if(taskNum == -1){
            return;
        }

        taskList.get(taskNum - 1).setMark(markStatus);
        Storage.saveTasks(taskList);
        System.out.println(LINE_SEPARATOR);
        System.out.println("    " + (markStatus ? "Awesome! I've marked this task as done" : "Alright, this task has been unmarked") + ":");
        printTask(taskNum);
        System.out.println(LINE_SEPARATOR);
        Storage.saveTasks(taskList);
    }

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

    public static void printList() {
        if (taskList.isEmpty()) {
            AtomException.taskEmpty();
            return;
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Below will be the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            printTask(i);
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void printMessageWithLineSeperator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("    " + message);
        System.out.println(LINE_SEPARATOR);
    }

    public static void processCommand(String line) {
        if (line.equals("list")) {
            printList();
        } else if (line.startsWith("mark ")) {
            markTask(line, true);
        } else if (line.startsWith("unmark ")) {
            markTask(line, false);
        } else if(line.startsWith("remove ")) {
            removeTaskFromList(line);
        }
        else if (isValidCreateTaskCommand(line)) {
            System.out.println(LINE_SEPARATOR);
            createTask(line);
            System.out.println(LINE_SEPARATOR);
        } else {
            AtomException.notImplemented();
        }
    }

    public static String receiveInput() {
        System.out.println();
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);
        printMessageWithLineSeperator("Hey hey there! Its your favourite chatbot assistant, Atom! :D\n" +
                "    Is there anything I can help you with? Just let me know.");

        Storage.loadTasks();

        while (true) {

            String line = receiveInput();

            if (line.equals("bye")) {
                break;
            }
            processCommand(line);
        }

        printMessageWithLineSeperator("Alright, I'll catch ya next time, Have a nice day!");
    }
}
