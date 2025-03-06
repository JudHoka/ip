package misc;

import exceptions.AtomException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import task.*;

/**
 * The {@code Parser} class handles parsing user input and stored task data.
 * It converts raw input into task-related commands and parses task entries from storage.
 */
public class Parser {

    /**
     * Parses an integer from a given user command.
     * The method checks if the input contains a valid task number and returns the parsed integer.
     * If the number is invalid or out of bounds, it returns -1.
     *
     * @param line The user command containing a task number.
     * @return The parsed task number if valid, otherwise returns -1.
     */
    public static int parseInt(String line) {
        try {
            String[] words = line.split(" ");

            if (words.length < 2) {
                AtomException.numError("no number");
                throw new NumberFormatException();
            }

            if (!words[1].matches("\\d+")) {
                AtomException.numError("invalid number format");
                throw new NumberFormatException();
            }

            int taskNum = Integer.parseInt(words[1]);

            if (TaskList.taskList.isEmpty()) {
                AtomException.numError("empty task list");
                throw new IllegalStateException();
            }

            if (taskNum > TaskList.taskList.size() || taskNum <= 0) {
                AtomException.numError("out of bounds");
                throw new IndexOutOfBoundsException();
            }

            return taskNum;
        } catch (NumberFormatException | IndexOutOfBoundsException | IllegalStateException e) {
            return -1;
        }
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     * The expected format is {@code dd-MM-yyyy (HH:mm)}, where the date and time are
     * separated, and the time is enclosed in parentheses.
     *
     * @param dateTime The input date-time string to be parsed.
     * @return A {@code LocalDateTime} object if parsing is successful, or {@code null} if the format is invalid.
     */
    public static LocalDateTime parseTime(String dateTime) {
        try {
            // Extract date and time parts
            String datePart = dateTime.split(" ")[0];
            String timePart = dateTime.substring(dateTime.indexOf("(") + 1, dateTime.indexOf(")"));

            // Define the expected format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            // Parse and return the LocalDateTime object
            return LocalDateTime.parse(datePart + " " + timePart, formatter);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            // Handle invalid or incomplete date-time input
            AtomException.dateParseError(dateTime);
            return null;
        }
    }

    /**
     * Parses a stored task entry from a file and reconstructs the corresponding task object.
     * It supports different types of tasks: {@code Todo}, {@code Deadlines}, and {@code Events}.
     * If the format is invalid or missing required data, an error is printed, and the task is skipped.
     *
     * @param line A line from the storage file representing a task in the format:
     *             {@code Type | Status | Description | Date & Time (except Todo)}.
     * @return The parsed {@code Tasks} object, or {@code null} if the line is corrupted.
     */
    public static Tasks parseTask(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            AtomException.storageError("Skipping corrupted line: " + line);
            return null;
        }

        try {
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            switch (type) {
            case "T":
                return new Todo(description, isDone);

            case "D":
                if (parts.length < 4) {
                    AtomException.storageError("Skipping corrupted deadline: " + line);
                    return null;
                }
                LocalDateTime by = parseTime(parts[3].trim());
                if (by == null) return null;
                return new Deadlines(description, by, isDone);

            case "E":
                if (parts.length < 5) {
                    AtomException.storageError("Skipping corrupted event: " + line);
                    return null;
                }
                LocalDateTime from = parseTime(parts[3].trim());
                LocalDateTime to = parseTime(parts[4].trim());
                if (from == null || to == null) return null;
                return new Events(description, from, to, isDone);

            default:
                AtomException.storageError("Unknown task type: " + type);
                return null;
            }
        } catch (Exception e) {
            AtomException.storageError("Skipping corrupted line: " + line);
            return null;
        }
    }
}
