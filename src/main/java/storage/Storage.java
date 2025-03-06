package storage;

import exceptions.AtomException;
import task.*;
import misc.Parser;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The {@code Storage} class handles reading and writing task data to a file.
 * It ensures that tasks persist between program executions by saving and loading data.
 */
public class Storage {
    private static String directoryPath; // The directory where task data is stored
    private static String filePath;      // The file path for saving and loading tasks

    /**
     * Constructs a {@code Storage} instance with the specified directory.
     * The task data is stored in a file named "atom.txt" inside the given directory.
     *
     * @param directory The directory where the task file is stored.
     */
    public Storage(String directory) {
        directoryPath = directory;
        filePath = directory + File.separator + "atom.txt";
    }

    /**
     * Saves the list of tasks to a file. If the directory does not exist, it creates one.
     *
     * @param taskList The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Tasks> taskList) {
        try {
            Path directory = Paths.get(directoryPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

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

    /**
     * Loads tasks from a file and returns them as an {@code ArrayList<Tasks>}.
     * If the file does not exist, it returns an empty task list.
     *
     * @return A list of tasks loaded from the file.
     */
    public static ArrayList<Tasks> loadTasks() {
        ArrayList<Tasks> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return list;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Tasks task = Parser.parseTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            AtomException.storageError("Error loading tasks: " + e.getMessage());
        }
        return list;
    }
}
