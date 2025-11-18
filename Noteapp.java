import java.io.*;
import java.util.Scanner;

public class Noteapp {

    static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n====== SIMPLE NOTES APP ======");
            System.out.println("1. Write Notes");
            System.out.println("2. Read Notes");
            System.out.println("3. Clear Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    writeNotes(sc);
                    break;

                case 2:
                    readNotes();
                    break;

                case 3:
                    clearNotes();
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Write notes to file
    static void writeNotes(Scanner sc) {
        try {
            System.out.println("Enter your notes (type 'END' to finish):");
            FileWriter fw = new FileWriter(FILE_NAME, true); // append mode

            while (true) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                fw.write(line + System.lineSeparator());
            }

            fw.close();
            System.out.println("Notes saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing notes: " + e.getMessage());
        }
    }

    // Read notes from file
    static void readNotes() {
        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);

            System.out.println("\n--- STORED NOTES ---");
            String line;
            boolean empty = true;

            while ((line = br.readLine()) != null) {
                empty = false;
                System.out.println(line);
            }

            if (empty) System.out.println("[No notes found]");

            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("No notes found or error reading file.");
        }
    }

    // Clear all notes
    static void clearNotes() {
        try {
            FileWriter fw = new FileWriter(FILE_NAME); // overwrite file
            fw.write("");
            fw.close();
            System.out.println("All notes cleared!");
        } catch (IOException e) {
            System.out.println("Error clearing notes.");
        }
    }
}
