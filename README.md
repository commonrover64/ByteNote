# ByteNote

A simple Java-based command-line application for managing text notes. Create, search, edit, and delete notes with ease. All notes are stored as `.txt` files in a dedicated `notes/` directory.

---

## **Coming Soon**:
- Multi-line note writing support.
- A clean and intuitive GUI for better user experience.


## ✨ Features

- **Create New Notes**: Create and save new text notes with a title.
- **Search Notes**: Search for existing notes by title.
- **Edit Notes**: Update the content of existing notes.
- **Delete Notes**: Remove single or multiple notes.
- **List All Notes**: View all available notes.
- **Persistent Storage**: Notes are saved as text files for later access.

---

## 🚀 How to Use

### 1. **Create a New Note**
   - Select option `1` from the main menu.
   - Enter a title for your note.
   - Type your note content and press `Enter` to save.

### 2. **Search for a Note**
   - Select option `2` from the main menu.
   - Enter the title of the note you want to find.
   - View the note content and choose to edit or delete it.

### 3. **Delete a Note**
   - Select option `3` from the main menu.
   - View the list of available notes.
   - Enter the number(s) of the note(s) you want to delete.

### 4. **Exit the Program**
   - Select option `4` from the main menu to exit.

---

## 🛠️ Code Structure

The application consists of two main classes:

### 1. **`Main.java`**
   - Handles the main menu and user input.
   - Manages the application lifecycle.

### 2. **`NoteManager.java`**
   - Contains all note-related operations.
   - Manages file I/O and note storage.

---

## 📂 Notes Storage

All notes are stored in the `notes/` directory within the project root. Each note is saved as a `.txt` file with the title as the filename.

---

## ⚙️ Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/commonrover64/ByteNote.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ByteNote
   ```
3. Compile the Java files:
   ```bash
   javac src/*.java
   ```
4. Run the application:
   ```bash
   java -cp src Main
   ```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature.
3. Commit your changes.
4. Push to the branch.
5. Create a new pull request.

---

## 📜 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## ❓ Support

For any issues or questions, please open an issue on the [GitHub repository](https://github.com/yourusername/note-manager/issues).

---

## 🖥️ Screenshots

### Main Menu
