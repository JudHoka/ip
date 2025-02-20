package storage;

import atom.ui.Atom;
import exceptions.AtomException;
import task.Deadlines;
import task.Events;
import task.Tasks;
import task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_PATH = "src/main/java/data";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + "atom.txt";

    public static void saveTasks(ArrayList<Tasks> taskList) {
        try {
            Path directory = Paths.get(DIRECTORY_PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (Tasks task : taskList) {
                if (task != null) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            AtomException.storageError("Error saving tasks: " + e.getMessage());
        }
    }

    public static void loadTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Tasks task = parseTask(line);
                if (task != null) {
                    Atom.taskList.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            AtomException.storageError("Error loading tasks: " + e.getMessage());
        }
    }

    private static Tasks parseTask(String line) {
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
