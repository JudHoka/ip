package exceptions;

import atom.ui.Atom;

public class AtomException {
    public static void notImplemented() {
        Atom.printMessageWithLineSeperator("Sorry, I don't understand what you're trying to say...");
    }

    public static void taskEmpty() {
        Atom.printMessageWithLineSeperator("Task list is empty! Please create one first.");
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
        case "no number" -> Atom.printMessageWithLineSeperator("Command must be followed by a valid task number.");
        case "empty task list" -> Atom.printMessageWithLineSeperator("The list is empty, please create a task first...");
        case "out of bounds" -> Atom.printMessageWithLineSeperator("Task number too large, please try again...");
        case "invalid number format" -> Atom.printMessageWithLineSeperator("Invalid task number, please try again...");
        }

    }

    public static void storageError(String message) {
        System.out.println("Storage Error : " + message);
    }
}