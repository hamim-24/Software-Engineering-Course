public class User {
    private String name;
    private boolean authenticated;
    private String role;
    public User(String name, String role) {
        this.name = name;
        this.role = role;
        this.authenticated = false;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
