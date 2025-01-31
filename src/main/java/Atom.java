import java.util.Scanner;

public class Atom {
    static String[] list = new String[100];
    static int index = 0;

    public static void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + ". " + list[i-1]);
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
            } else if (line.equals("bye")) {
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Added: " + line);
                list[index] = line;
                index++;
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Alright, I'll see ya next time!");
        System.out.println("____________________________________________________________");

    }
}
