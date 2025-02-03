import java.util.Scanner;

public class Atom {
    private static final int MAX_TASKS = 100;
    private static final String LINE_SEPARATOR = "  ____________________________________________________________";
    static Tasks[] list = new Tasks[MAX_TASKS];
    static int index = 0;

    public static boolean isValidTaskCommand(String line) {
        return line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ");
    }

    public static void createTodo(String line) {
        String taskDescription = line.substring(5).trim();

        if (taskDescription.isEmpty()) {
            System.out.println("    Sorry! The description of a 'todo' cannot be empty.");
        } else {
            Tasks newTask = new Todo(taskDescription);
            list[index] = newTask;
            index++;

            System.out.println("    Nice mate! I've added this task: ");
            System.out.println("      [" + newTask.getCategory() + "][" + newTask.marked() + "] " + newTask.getName());
            System.out.println("    Now you have " + index + " tasks in the list.");
        }
    }

    public static void createDeadline(String line) {
        String trimmedLine = line.substring(9).trim();

        if (trimmedLine.isEmpty()) {
            System.out.println("    Sorry! The description of a 'deadline' cannot be empty.");
        } else {
            String[] parts = trimmedLine.split(" /by ", 2);

            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                System.out.println("    Sorry, I don't understand what you're trying to say, please specify more... \n" +
                        "    (deadline 'activity' '/by' 'deadline time')");
            } else {
                String taskDescription = parts[0].trim();
                String deadline = parts[1].trim();

                Deadlines newTask = new Deadlines(taskDescription, deadline);
                list[index] = newTask;
                index++;

                System.out.println("    Nice mate! I've added this task: ");
                System.out.println("      [" + newTask.getCategory() + "][" + newTask.marked() + "] " + newTask.getName() + " (by: " + newTask.getBy() + ")");
                System.out.println("    Now you have " + index + " tasks in the list.");
            }
        }
    }

    public static void createEvent(String line) {
        String trimmedLine = line.substring(6).trim();

        if (trimmedLine.isEmpty()) {
            System.out.println("    Sorry! The description of an 'event' cannot be empty.");
        } else {
            String[] parts = trimmedLine.split(" /from | /to ", 3);

            if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                System.out.println("    Sorry, I don't understand what you're trying to say, please specify more... \n" +
                        "    (event 'activity' '/from' 'start time' '/to' 'end time')");
            } else {
                String taskDescription = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();

                Events newTask = new Events(taskDescription, from, to);
                list[index] = newTask;
                index++;

                System.out.println("    Nice mate! I've added this task: ");
                System.out.println("      [" + newTask.getCategory() + "][" + newTask.marked() + "] " + newTask.getName() + " (from: " + newTask.getFrom() + " to: " + newTask.getTo() + ")");
                System.out.println("    Now you have " + index + " tasks in the list.");
            }
        }
    }

    public static void markTask(String line, boolean markStatus) {
        String[] words = line.split(" ");

        if (words.length < 2 || !words[1].matches("\\d+")) {
            printErrorMessage("Command must be followed by a valid task number.");
            return;
        }

        int taskNum = Integer.parseInt(words[1]);
        if (taskNum > index) {
            printErrorMessage("Task number too large, please try again...");
            return;
        }

        list[taskNum - 1].setMark(markStatus);
        System.out.println(LINE_SEPARATOR);
        System.out.println("    " + (markStatus ? "Awesome! this task is marked as done" : "Alright, this task has been unmarked") + ":");
        printItem(taskNum);
        System.out.println(LINE_SEPARATOR);

    }

    public static void printItem(int counter) {
        System.out.print("    " + counter + ".[" + list[counter - 1].category + "][" + list[counter - 1].marked() + "] " + list[counter - 1].getName());
        if (list[counter - 1].getCategory().equals("D")) {
            System.out.println(" (by: " + ((Deadlines) list[counter - 1]).getBy() + ")");
        } else if (list[counter - 1].getCategory().equals("E")) {
            System.out.println(" (from: " + ((Events) list[counter - 1]).getFrom() + " to: " + ((Events) list[counter - 1]).getTo() + ")");
        } else {
            System.out.println();
        }
    }

    public static void printList() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Here are all the tasks in your list:");
        for (int i = 1; i <= index; i++) {
            printItem(i);
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void printErrorMessage(String message) {
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
            if (line.startsWith("todo ")) {
                createTodo(line);
            } else if (line.startsWith("deadline ")) {
                createDeadline(line);
            } else if (line.startsWith("event ")) {
                createEvent(line);
            }
            System.out.println(LINE_SEPARATOR);
        } else {
            printErrorMessage("Sorry, I don't understand what you're trying to say...");
        }
    }

    public static void main(String[] args) {
        String logo = """
                    ____     _                        /\\   \s
                   / __ \\   | |__   ____   __  __  \\ /  \\ / \s
                  / /__\\ \\  |  __| / __ \\ |  \\/  | <      >\s
                 / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\ \s
                /_/      \\_\\ \\____|\\____/ |_|  |_|    \\/   \s
                """;
        System.out.println("Hello from\n" + logo);

        System.out.println(LINE_SEPARATOR);
        System.out.println("    Hey hey there! Its your favourite chatbot assistant, Atom :D");
        System.out.println("    Is there anything I can help you with?");
        System.out.println(LINE_SEPARATOR);

        while (true) {
            System.out.println();
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }
            processCommand(line);
        }

        System.out.println(LINE_SEPARATOR);
        System.out.println("    Alright, I'll see ya next time!");
        System.out.println(LINE_SEPARATOR);

    }
}
