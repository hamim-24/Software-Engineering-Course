public class Version {
    private final String versionName;
    private final EditorMemento memento;

    public Version(String versionName, EditorMemento memento) {
        this.versionName = versionName;
        this.memento = memento;
    }

    public String getVersionName() {
        return versionName;
    }

    public EditorMemento getMemento() {
        return memento;
    }
}