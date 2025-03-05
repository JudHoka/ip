import storage.Storage;
import ui.Commands;
import task.TaskList;
import misc.*;


public class Atom {

    private Storage storage;
    private TaskList tasks;
    private Commands ui;

    public Atom(String filePath) {
        ui = new Commands();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        Others.intro();
        Storage.loadTasks();

        boolean isContinue = true;
        while (isContinue) {

            String line = Others.receiveInput();

            if (line.equals("bye")) {
                isContinue = false;
            }
            else {
                Commands.processCommand(line);
            }
        }

        Others.end();
    }


    public static void main(String[] args) {
        new Atom("src/main/java/data").run();
    }
}
