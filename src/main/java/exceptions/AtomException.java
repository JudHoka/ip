package exceptions;

import misc.Others;

public class AtomException extends Exception {

    public static void notImplemented() {
        Others.printMessageWithLineSeperator("Sorry, I don't understand what you're trying to say...");
    }

    public static void taskEmpty() {
        Others.printMessageWithLineSeperator("Task list is empty! Please create one first.");
    }

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

    public static void taskIncomplete(String task) {
        if (task.equals("d")) {
            System.out.println("    Sorry, I don't understand what you're trying to say, please specify more... \n" +
                    "    (deadline 'activity' '/by' 'deadline time')");
        } else if (task.equals("e")) {
            System.out.println("    Sorry, I don't understand what you're trying to say, please specify more... \n" +
                    "    (event 'activity' '/from' 'start time' '/to' 'end time')");
        }
    }

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

    public static void storageError(String message) {
        Others.printMessageWithLineSeperator("Storage Error: " + message);
    }

    public static void dateTimeError(String message) {
        Others.printMessageWithLineSeperator("DateTime Error: " + message);
    }

    public static void dateParseError(String dateTime) {
        System.out.println("    Sorry, the date/time format is invalid or incomplete : " + dateTime + "\n" +
        "    Please ensure the format is: dd-MM-yyyy (HH:mm)");
    }
}
