import storage.Storage;
import ui.Commands;
import task.TaskList;
import misc.*;


public class Atom {

    private Storage storage;

    public Atom(String filePath) {
        storage = new Storage(filePath);
        new TaskList(storage.loadTasks());
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
