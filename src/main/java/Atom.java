import storage.Storage;
import ui.Commands;
import task.TaskList;
import misc.*;

/**
 * The {@code Atom} class represents the main entry point for Atom.
 * It initializes storage and loads tasks from a save file.
 * The chatbot reads user input, processes commands, and executes tasks accordingly.
 */
public class Atom {

    private Storage storage;

    /**
     * Constructs an {@code Atom} instance, initializing the storage system,
     *
     * @param filePath The file path where task data is stored and loaded.
     */
    public Atom(String filePath) {
        storage = new Storage(filePath);
        new TaskList(storage.loadTasks());
    }

    /**
     * Runs the chatbot application, displaying an introduction message,
     * processing user input, and passing the commands to be processed until termination.
     */
    public void run() {
        Others.intro(); // Display welcome message
        Storage.loadTasks(); // Load saved tasks from storage

        boolean isContinue = true;
        while (isContinue) {

            String line = Others.receiveInput(); // Read user input

            if (line.equals("bye")) {
                isContinue = false; // Exit the chatbot when "bye" is entered
            }
            else {
                Commands.processCommand(line); // Process user command
            }
        }

        Others.end(); // Display exit message
    }

    /**
     * The main entry point for Atom.
     * It initializes an {@code Atom} instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Atom("src/main/java/data").run();
    }
}
