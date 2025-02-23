import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class NoteManager {
    
    private static final String noteFolder = "notes/";
    private String currentNote = "";
    private String title = "";
    private final Scanner scanner;

    public NoteManager() {
        this.scanner = new Scanner(System.in);
    }

    // 1. Create New Note
    public void createNote() {
        try {
            System.out.println("\n Give Title: ");
            this.title = scanner.next() + ".txt";
            scanner.nextLine(); // Consume the newline

            this.currentNote = inputFromUser();
            saveNote();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 2. Search Existing Note
    public void searchNote() {
        File[] allNotes = getAllNotes();
        
        if(allNotes.length == 0) {
            System.out.println("\n No notes found, Create a new Note First!\n");
            return;
        }

        System.out.println("\n Give title of Note you are looking for: \n");
        String title = scanner.nextLine();

        try {
            for(int i=0; i<allNotes.length; i++) {
                if(allNotes[i].getName().equalsIgnoreCase(title + ".txt")) {
                    String content = new String(Files.readAllBytes(Paths.get(allNotes[i].getPath())));
                    System.out.println("\n--- " + allNotes[i].getName() + " ---");
                    System.out.println(content);
                    System.out.println("-----------------------------");
                    showNoteOptions(allNotes[i]);
                    return;
                }
            }
            
            // If we reach here, the note wasn't found
            System.out.println(String.format("""                   
            Cannot Find file: %s                          
                                                                  
            Would you like to see list of all Notes? Y/N     
            """, title));

            char choice = scanner.next().charAt(0);
            if(Character.toLowerCase(choice) == 'y') {
                displayNote();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 3. Delete a Note
    public void deleteNote() {
        File[] allNotes = getAllNotes();
        
        if(allNotes.length == 0) {
            System.out.println("\n No notes found to Delete\n");
            return;
        }
        
        try {
            System.out.println("\n All Available Notes to Delete are:\n");
            for(int i=0; i<allNotes.length; i++) {
                System.out.println(i+1 + " " + allNotes[i].getName());
            }
            
            // choose note to Delete
            System.out.print("\n Enter the Number of note to Delete, For Multiple Note enter numbers separated by commas\n");            
            String newCh = scanner.nextLine().trim();

            // get all choices like [1,2,3,4]
            String[] strNumbers = newCh.split(",");

            // Using LinkedHashSet to store unique numbers while maintaining order
            Set<Integer> uniqueNumbers = new LinkedHashSet<>();

            for(String str:strNumbers) {
                str = str.trim();  // remove extra space around each number

                // Only convert to integer if the string is not empty
                if(!str.isEmpty()) {
                    uniqueNumbers.add(Integer.parseInt(str));
                }
            }
             // Convert Set to int array
            int[] numbers = uniqueNumbers.stream().mapToInt(Integer::intValue).toArray();

            for(int i=0; i<numbers.length; i++) {

                if(numbers[i] > 0 && numbers[i] <= allNotes.length) {
                    File noteToDelete = allNotes[numbers[i]-1];
    
                    if(noteToDelete.delete()) {
                        System.out.println("\n Note Deleted Sucessfully!");
                    }else {
                        System.out.println("\n Failed to Delete Note: " + noteToDelete.getName());
                    }
                }else {
                    System.out.println("\n Invalid Note Number: " + numbers[i] + " Try Again: ");
                    deleteNote();
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper methods
    private String inputFromUser() {
        System.out.println("Enter your Note (Press Enter to finish):");
        return scanner.nextLine();
    }

    private void saveNote() {
        if(currentNote.isEmpty() || title.isEmpty()) {
            System.out.println("\n No Note to Save!!\n");
            return;
        }

        try {
            //create notes dir if it dosen't exists
            new File(noteFolder).mkdirs();

            FileWriter writer = new FileWriter( noteFolder + title);
            writer.write(currentNote);
            writer.close();
            System.out.println("\n Note Saved!\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showNoteOptions(File noteFile) {
        while(true) {
            System.out.println("""
                ____________________________
               |                            |
               | What would you like to do? |
               |                            |
               | 1. Edit Note               |
               | 2. Delete this Note        |
               | 3. Return to Main Menu     |
               |____________________________|
                """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    editNote(noteFile);
                    return;
                case 2:
                    if(noteFile.delete()) {
                        System.out.println("\n Note Deleted Successfully: " + noteFile.getName());
                    }else {
                        System.out.println("\n Failed to Delete Note: " + noteFile.getName());
                    }
                    return;
                default:
                    System.out.println("Invalid Choice!!");
            }
        }
    }

    private void editNote(File noteFile) {
        try {  
            // read current content
            String content = new String(Files.readAllBytes(Paths.get(noteFile.getPath())));
            System.out.println("\nCurrent Content: \n");
            System.out.println(content);

            // get new content
            String newContent = inputFromUser();
            
            // write new content
            try(FileWriter writer = new FileWriter(noteFile)) {
                writer.write(newContent);
                System.out.println("\n Note Updated Successfully!!");
            }
        } catch (Exception e) {
            System.out.println("Error editing note: " + e.getMessage());
        }
    }

    private void displayNote() {
        File[] allNotes = getAllNotes();
        
        if(allNotes.length == 0) {
            System.out.println("\n No Notes have been created yet!!\n");
            return;
        }
        
        try {
            // display notes
            System.out.println("\n All Available Notes are:\n");
            for(int i=0; i<allNotes.length; i++) {
                System.out.println(i+1 + " " + allNotes[i].getName());
                if(i == allNotes.length-1) {
                    System.out.println(allNotes.length+1 + " " + "Return");
                }
            }

            // choose note to read / open
            System.out.print("\n Choose: \n");
            int choice = scanner.nextInt();

            if(choice == allNotes.length+1) {
                return;
            }

            try {
                // check valid choice or not
                if(choice > 0 && choice <= allNotes.length) {
                    String content = new String(Files.readAllBytes(Paths.get(allNotes[choice-1].getPath())));
                    System.out.println("\n--- " + allNotes[choice-1].getName() + " ---");
                    System.out.println(content);
                    System.out.println("-----------------------------");
                    showNoteOptions(allNotes[choice-1]);
                }else {
                    System.out.println("\n Invalid Choice!! Try Again\n");
                    displayNote();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private File[] getAllNotes() {
        File folder = new File(noteFolder);
        
        // Create notes directory if it doesn't exist
        if(!folder.exists()) {
            folder.mkdir();
        }
        
        File[] files = folder.listFiles();
        
        // Return empty array if no files found
        if(files == null) {
            return new File[0];
        }
        
        return files;
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
