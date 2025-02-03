import java.util.Scanner;

public class Atom {
    static Tasks[] list = new Tasks[100];
    static int index = 0;

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
        System.out.println("  ____________________________________________________________");
        System.out.println("    Here are all the tasks in your list:");
        for (int i = 1; i <= index; i++) {
            printItem(i);
        }
        System.out.println("  ____________________________________________________________");
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

        System.out.println("____________________________________________________________");
        System.out.println("  Hey hey there! Its your favourite chatbot assistant, Atom :D");
        System.out.println("  Is there anything I can help you with?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.println();
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("list")) {
                printList();
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");

                if (words.length > 1 && words[1].matches("\\d+")) {
                    int counter = Integer.parseInt(words[1]);
                    if (counter > index) {
                        System.out.println("  ____________________________________________________________");
                        System.out.println("    Number request too large, please try again...");
                        System.out.println("  ____________________________________________________________");
                    } else {
                        list[counter - 1].setMark(true);
                        System.out.println("  ____________________________________________________________");
                        System.out.println("    Awesome! I've marked this task as done:");
                        printItem(counter);
                        System.out.println("  ____________________________________________________________");
                    }
                } else {
                    System.out.println("  ____________________________________________________________");
                    System.out.println("    Command 'mark' is supposed to be followed by a number, please try again....");
                    System.out.println("  ____________________________________________________________");
                }

            } else if (line.startsWith("unmark")) {
                String[] check = line.split(" ");

                if (check.length > 1 && check[1].matches("\\d+")) {
                    int counter = Integer.parseInt(check[1]);
                    if (counter > index) {
                        System.out.println("  ____________________________________________________________");
                        System.out.println("    Number request too large, please try again...");
                        System.out.println("  ____________________________________________________________");
                    } else {
                        list[counter - 1].setMark(false);
                        System.out.println("  ____________________________________________________________");
                        System.out.println("    Alright, So then this task is marked as not done yet:");
                        printItem(counter);
                        System.out.println("  ____________________________________________________________");
                    }
                } else {
                    System.out.println("  ____________________________________________________________");
                    System.out.println("    Command 'mark' is supposed to be followed by a number, please try again....");
                    System.out.println("  ____________________________________________________________");
                }

            } else if (line.equals("bye")) {
                break;
            } else if (line.startsWith("todo ") || line.startsWith("deadline ") || line.startsWith("event ")) {
                System.out.println("  ____________________________________________________________");

                if (line.startsWith("todo ")) {
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
                } else if (line.startsWith("deadline ")) {
                    String trimmedLine = line.substring(9).trim(); // Trim spaces after "deadline "

                    if (trimmedLine.isEmpty()) {  // Check if description is missing
                        System.out.println("    Sorry! The description of a 'deadline' cannot be empty.");
                    } else {
                        String[] parts = trimmedLine.split(" /by ", 2); // Ensure correct splitting

                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            System.out.println("    Sorry, I don't understand what you're trying to say, please specify the deadline...");
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

                else if (line.startsWith("event ")) {
                    String trimmedLine = line.substring(6).trim(); // Trim spaces after "event "

                    if (trimmedLine.isEmpty()) {  // Check if description is missing
                        System.out.println("    Sorry! The description of an 'event' cannot be empty.");
                    } else {
                        String[] parts = trimmedLine.split(" /from | /to ", 3); // Split at /from and /to

                        if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                            System.out.println("    Sorry, I don't understand what you're trying to say, please specify the event time...");
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

                System.out.println("  ____________________________________________________________");
            } else {
                System.out.println("  ____________________________________________________________");
                System.out.println("    Sorry, I don't understand what you're trying to say...");
                System.out.println("  ____________________________________________________________");
            }
        }

        System.out.println("  ____________________________________________________________");
        System.out.println("    Alright, I'll see ya next time!");
        System.out.println("  ____________________________________________________________");

    }
}
