import java.util.Scanner;

public class Atom {
    public static void main(String[] args) {
        String logo = "    ____     _                        /\\    \n"
                + "   / __ \\   | |__   ____   __  __  \\ /  \\ /  \n"
                + "  / /__\\ \\  |  __| / __ \\ |  \\/  | <      > \n"
                + " / /    \\ \\ | |___| |__| || |\\/| | / \\  / \\  \n"
                + "/_/      \\_\\ \\____|\\____/ |_|  |_|    \\/    \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hey hey there! Its your favourite chatbot assistant, Atom :D");
        System.out.println("Is there anything I can help you with?");
        System.out.println("____________________________________________________________");

        while (true) {
            System.out.println();
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if(line.equals("bye")){
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Alright, I'll see ya next time!");
        System.out.println("____________________________________________________________");

    }
}
