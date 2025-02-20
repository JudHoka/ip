package atom.ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_PATH = "src/main/java/data";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + "atom.txt";

    public static void saveTasks(Tasks[] taskList) {
        try {
            Path directory = Paths.get(DIRECTORY_PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (int i = 0; i < Atom.taskCount; i++) {
                if (taskList[i] != null) {
                    String formattedTask = taskList[i].toFileFormat();
                    writer.write(formattedTask);
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
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
                    Atom.taskList[Atom.taskCount] = task;
                    Atom.taskCount++;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private static Tasks parseTask(String line) {
        String[] parts = line.split(" \\| ");
        try {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
            case "T" -> {
                return new Todo(description, isDone);
            }
            case "D" -> {
                return new Deadlines(description, parts[3], isDone);
            }
            case "E" -> {
                return new Events(description, parts[3], parts[4], isDone);
            }
            default -> {
                return null;
            }
            }
        } catch (Exception e) {
            System.out.println("Skipping corrupted line: " + line);
            return null;
        }
    }
}

