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

public class Storage {
    private static String directoryPath;
    private static String filePath;

    public Storage(String directory) {
        directoryPath = directory;
        filePath = directory + File.separator + "atom.txt";
    }

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
