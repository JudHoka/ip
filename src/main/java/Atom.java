import java.util.Scanner;

public class Atom {
    static Tasks[] list = new Tasks[100];
    static int index = 0;

    public static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are all the tasks in your list:");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + ".[" + list[i - 1].marked() + "] " + list[i - 1].getName());
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = "    ____     _                        /\\    \n"
                + "   / __ \\   | |__   ____   __  __  \\ /  \\ /  \n"
                + "  / /__\\ \\  |  __| / __ \\ |  \\/  | <      > \n"
                + " / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\  \n"
                + "/_/      \\_\\ \\____|\\____/ |_|  |_|    \\/    \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hey hey there! Its your favourite chatbot assistant, Atom :D");
        System.out.println("Is there anything I can help you with?");
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
                        System.out.println("____________________________________________________________");
                        System.out.println("Number request too large, please try again...");
                        System.out.println("____________________________________________________________");
                    } else {
                        list[counter - 1].setMark(true);
                        System.out.println("____________________________________________________________");
                        System.out.println("Awesome! I've marked this task as done:");
                        System.out.println(counter + ".[" + list[counter - 1].marked() + "] " + list[counter - 1].getName());
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Command 'mark' is supposed to be followed by a number, please try again....");
                    System.out.println("____________________________________________________________");
                }

            } else if (line.startsWith("unmark")) {
                String[] check = line.split(" ");

                if (check.length > 1 && check[1].matches("\\d+")) {
                    int counter = Integer.parseInt(check[1]);
                    if (counter > index) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Number request too large, please try again...");
                        System.out.println("____________________________________________________________");
                    } else {
                        list[counter - 1].setMark(false);
                        System.out.println("____________________________________________________________");
                        System.out.println("Alright, So then this task is marked as not done yet:");
                        System.out.println(counter + ".[" + list[counter - 1].marked() + "] " + list[counter - 1].getName());
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Command 'mark' is supposed to be followed by a number, please try again....");
                    System.out.println("____________________________________________________________");
                }

            } else if (line.equals("bye")) {
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Added: " + line);
                Tasks newTask = new Tasks(line);
                list[index] = newTask;
                index++;
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Alright, I'll see ya next time!");
        System.out.println("____________________________________________________________");

    }
}
