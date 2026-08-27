public class Main {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        HistoryManager history = new HistoryManager(editor);

        System.out.println("===== MEMENTO TEXT EDITOR =====");
        history.saveState();
        editor.insertText("Hello");
        history.saveVersion("Initial");

        history.saveState();
        editor.insertText(" World");

        history.saveVersion("First Draft");
        history.saveState();
        editor.insertText("!");

        history.saveVersion("Final");
        System.out.println("\n--- Current State ---");
        editor.display();

        history.listVersions();
        System.out.println("\n--- Undo ---");

        history.undo();
        editor.display();
        System.out.println("\n--- Redo ---");

        history.redo();
        editor.display();

        System.out.println("\n--- Multiple Undo ---");

        history.undo();
        history.undo();

        editor.display();
        System.out.println("\n--- Redo ---");

        history.redo();

        editor.display();

        System.out.println("\n--- Rollback to Initial ---");

        history.rollbackToVersion("Initial");

        editor.display();

        System.out.println("\n--- Undo Rollback ---");

        history.undo();

        editor.display();

        System.out.println("\n--- Redo Rollback ---");

        history.redo();

        editor.display();

        System.out.println("\n--- Saved Versions ---");

        history.listVersions();
    }
}