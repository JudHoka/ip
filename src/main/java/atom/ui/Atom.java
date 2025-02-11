package atom.ui;

import java.util.Scanner;

public class Atom {
    private static final int MAX_TASKS = 100;
    private static final String LINE_SEPARATOR = "  ____________________________________________________________";

    private static final String LOGO = """
                ____     _                        /\\   \s
               / __ \\   | |__   ____   __  __  \\ /  \\ / \s
              / /__\\ \\  |  __| / __ \\ |  \\/  | <      >\s
             / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\ \s
            /_/      \\_\\ \\____|\\____/ |_|  |_|    \\/   \s
            """;
    private static Tasks[] taskList = new Tasks[MAX_TASKS];
    private static int taskCount = 0;

    private static boolean isValidTaskCommand(String line) {
        return line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ");
    }

    private static void addTaskToList(Tasks task) {
        if (taskCount >= MAX_TASKS) {
            AtomException.taskArrayFull();
            return;
        }

        taskList[taskCount] = task;
        taskCount++;

        System.out.println("    Nice! I've added this task:");
        printTask(taskCount);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
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
            Todo newTask = new Todo(taskDescription);
            addTaskToList(newTask);
        }
    }

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

                Deadlines newTask = new Deadlines(taskDescription, deadline);
                addTaskToList(newTask);
            }
        }
    }

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

                Events newTask = new Events(taskDescription, from, to);
                addTaskToList(newTask);
            }
        }
    }

    public static void markTask(String line, boolean markStatus) {
        String[] words = line.split(" ");

        if (words.length < 2 || !words[1].matches("\\d+")) {
            AtomException.markError("no number");
            return;
        }

        int taskNum = Integer.parseInt(words[1]);
        if (taskNum > taskCount) {
            AtomException.markError("out of bounds");
            return;
        }

        taskList[taskNum - 1].setMark(markStatus);
        System.out.println(LINE_SEPARATOR);
        System.out.println("    " + (markStatus ? "Awesome! I've marked this task as done" : "Alright, this task has been unmarked") + ":");
        printTask(taskNum);
        System.out.println(LINE_SEPARATOR);

    }

    public static void printTask(int TaskNumber) {
        System.out.print("    " + TaskNumber + ".[" + taskList[TaskNumber - 1].category + "][" + taskList[TaskNumber - 1].marked() + "] " + taskList[TaskNumber - 1].getName());
        if (taskList[TaskNumber - 1].getCategory().equals("D")) {
            System.out.println(" (by: " + ((Deadlines) taskList[TaskNumber - 1]).getBy() + ")");
        } else if (taskList[TaskNumber - 1].getCategory().equals("E")) {
            System.out.println(" (from: " + ((Events) taskList[TaskNumber - 1]).getFrom() + " to: " + ((Events) taskList[TaskNumber - 1]).getTo() + ")");
        } else {
            System.out.println();
        }

    }

    public static void printList() {
        if (taskCount == 0) {
            AtomException.taskEmpty();
            return;
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Below will be the tasks in your list:");
        for (int i = 1; i <= taskCount; i++) {
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
        } else if (isValidTaskCommand(line)) {
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
