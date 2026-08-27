public class TextEditor {
    private String content;
    private int cursorPosition;

    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
    }

    public void insertText(String text) {
        content = content.substring(0, cursorPosition) + text + content.substring(cursorPosition);
        cursorPosition += text.length();
    }

    public void deleteText(int length) {
        if (length <= 0 || cursorPosition == 0) {
            return;
        }

        int start = Math.max(0, cursorPosition - length);

        content = content.substring(0, start) + content.substring(cursorPosition);
        cursorPosition = start;
    }

    public void display() {
        System.out.println("Current Document:");
        System.out.println(content);
    }

    public EditorMemento createMemento() {
        return new EditorMemento(content, cursorPosition);
    }

    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
    }

    public String getContent() {
        return content;
    }
}