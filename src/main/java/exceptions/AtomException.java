package exceptions;

import misc.Others;

/**
 * The {@code AtomException} class handles custom exceptions for Atom.
 * It extends {@code Exception} to provide meaningful error messages and ensures
 * structured handling of task-related, storage-related, and other errors.
 */
public class AtomException extends Exception {

    /**
     * Displays a message when an unrecognized command is entered.
     */
    public static void notImplemented() {
        Others.printMessageWithLineSeperator("Sorry, I don't understand what you're trying to say...");
    }

    /**
     * Displays an error when trying to access an empty task list.
     */
    public static void taskEmpty() {
        Others.printMessageWithLineSeperator("Task list is empty! Please create one first.");
    }

    /**
     * Displays an error when a task description is missing.
     *
     * @param task The type of task that is missing a description (todo, deadline, or event).
     */
    public static void taskMissingDesc(String task) {
        switch (task) {
        case "t":
            System.out.println("    Sorry! The description of a 'todo' cannot be empty.");
            break;
        case "d":
            System.out.println("    Sorry! The description of a 'deadline' cannot be empty.");
            break;
        case "e":
            System.out.println("    Sorry! The description of an 'event' cannot be empty.");
            break;
        default:
            System.out.println("    Sorry! I don't understand what you meant.....");
        }
    }

    /**
     * Displays an error message when a task is incomplete (e.g., missing date or time).
     *
     * @param task The type of task that is incomplete (deadline or event).
     */
    public static void taskIncomplete(String task) {
        if (task.equals("d")) {
            System.out.println("    Sorry, I don't understand what you're trying to say, please specify more... \n" +
                    "    (deadline 'activity' '/by' 'deadline time')");
        } else if (task.equals("e")) {
            System.out.println("    Sorry, I don't understand what you're trying to say, please specify more... \n" +
                    "    (event 'activity' '/from' 'start time' '/to' 'end time')");
        }
    }

    /**
     * Displays an error message for invalid task numbers.
     *
     * @param error The type of number error (e.g., no number, out of bounds).
     */
    public static void numError(String error) {
        switch (error) {
        case "no number" -> Others.printMessageWithLineSeperator("Command must be followed by a valid task number.");
        case "empty task list" ->
                Others.printMessageWithLineSeperator("The list is empty, please create a task first...");
        case "out of bounds" -> Others.printMessageWithLineSeperator("Task number too large, please try again...");
        case "invalid number format" ->
                Others.printMessageWithLineSeperator("Invalid task number, please try again...");
        }
    }

    /**
     * Displays a storage-related error message.
     *
     * @param message The specific storage error encountered.
     */
    public static void storageError(String message) {
        Others.printMessageWithLineSeperator("Storage Error: " + message);
    }

    /**
     * Displays an error message when the date or time format is invalid or incomplete.
     *
     * @param dateTime The invalid date/time string provided by the user.
     */
    public static void dateParseError(String dateTime) {
        System.out.println("    Sorry, the date/time format is invalid or incomplete : " + dateTime + "\n" +
                "    Please ensure the format is: dd-MM-yyyy (HH:mm)");
    }
}
