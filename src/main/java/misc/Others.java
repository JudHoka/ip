package misc;

import java.util.Scanner;

/**
 * The {@code Others} class provides utility methods for displaying messages, handling input,
 * and managing introductory and exit sequences for the chatbot.
 */
public class Others {

    /** A constant for the line separator used in output formatting. */
    public static final String LINE_SEPARATOR = "  ____________________________________________________________";

    /** The logo for the chatbot displayed at startup. */
    public static final String LOGO = """
                ____     _                        /\\   \s
               / __ \\   | |__   ____   __  __  \\ /  \\ / \s
              / /__\\ \\  |  __| / __ \\ |  \\/  | <      >\s
             / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\ \s
            /_/      \\_\\ \\____|\\____/ |_|  |_|    \\/   \s
            """;

    /**
     * Prints a message enclosed within line separators for better readability.
     *
     * @param message The message to be displayed.
     */
    public static void printMessageWithLineSeperator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("    " + message);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays Atom's introduction, including the logo and a welcome message.
     */
    public static void intro() {
        System.out.println("Hello from\n" + LOGO);
        printMessageWithLineSeperator("Hey hey there! Its your favourite chatbot assistant, Atom! :D\n" +
                "    Is there anything I can help you with? Just let me know.");
    }

    /**
     * Reads and returns user input from the console.
     *
     * @return The input string entered by the user.
     */
    public static String receiveInput() {
        System.out.println();
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Displays the chatbot exit message before termination.
     */
    public static void end() {
        printMessageWithLineSeperator("Alright, I'll catch ya next time, Have a nice day!");
    }
}
