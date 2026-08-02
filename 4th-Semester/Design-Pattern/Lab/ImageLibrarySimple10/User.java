public class User {
    private final String name;
    private final boolean isAdmin;
    private final String knownCode;

    public User(String name, boolean isAdmin, String knownCode) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.knownCode = knownCode;
    }

    public String getName() { return name; }
    public boolean isAdmin() { return isAdmin; }
    public boolean knowsCode(String code) { return code != null && code.equals(knownCode); }

    @Override
    public String toString() {
        return name;
    }
}
