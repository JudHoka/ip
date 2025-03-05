package misc;

import exceptions.AtomException;
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

    public static Tasks parseTask(String line) {
        String[] parts = line.split(" \\| ");
        try {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            return switch (type) {
                case "T" -> new Todo(description, isDone);
                case "D" -> new Deadlines(description, parts[3], isDone);
                case "E" -> new Events(description, parts[3], parts[4], isDone);
                default -> {
                    AtomException.storageError("Unknown task type: " + type);
                    yield null;
                }
            };
        } catch (Exception e) {
            AtomException.storageError("Skipping corrupted line: " + line);
            return null;
        }
    }
}
