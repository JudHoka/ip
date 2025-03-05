package misc;

import java.util.Scanner;

public class Others {
    public static final String LINE_SEPARATOR = "  ____________________________________________________________";

    public static final String LOGO = """
                ____     _                        /\\   \s
               / __ \\   | |__   ____   __  __  \\ /  \\ / \s
              / /__\\ \\  |  __| / __ \\ |  \\/  | <      >\s
             / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\ \s
            /_/      \\_\\ \\____|\\____/ |_|  |_|    \\/   \s
            """;

    public static void printMessageWithLineSeperator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("    " + message);
        System.out.println(LINE_SEPARATOR);
    }

    public static void intro() {
        System.out.println("Hello from\n" + LOGO);
        printMessageWithLineSeperator("Hey hey there! Its your favourite chatbot assistant, Atom! :D\n" +
                "    Is there anything I can help you with? Just let me know.");
    }

    public static String receiveInput() {
        System.out.println();
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void end() {
        printMessageWithLineSeperator("Alright, I'll catch ya next time, Have a nice day!");
    }
}
