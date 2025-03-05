package misc;

import exceptions.AtomException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import task.*;

public class Parser {
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

    public static LocalDateTime parseTime(String dateTime) {
        try {
            String datePart = dateTime.split(" ")[0];
            String timePart = dateTime.substring(dateTime.indexOf("(") + 1, dateTime.indexOf(")"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            return LocalDateTime.parse(datePart + " " + timePart, formatter);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            AtomException.dateParseError(dateTime);
            return null;
        }
    }

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
