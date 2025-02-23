import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // call NoteManager
        NoteManager manager = new NoteManager();
        Scanner scanner = new Scanner(System.in);
        
        try {
            while(true) {
                System.out.println("""
                    ___________________________
                   |                           |
                   | What Do You Want To Do ?  |
                   |                           |
                   | 1. Create New Note        |    
                   | 2. Search Existing Note   |
                   | 3. Delete a Note          |
                   | 4. Exit                   | 
                   |___________________________|
                    """);
                
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (option) {
                    case 1:
                        manager.createNote();
                        break;
                    case 2:
                        manager.searchNote();
                        break;
                    case 3:
                        manager.deleteNote();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        } finally {
            scanner.close();
            manager.close();
        }
    }
}