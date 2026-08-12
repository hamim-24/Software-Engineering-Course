public class Resource {
    private String title;
    private String author;
    private String formate;
    private String category;
    private boolean available;
    public Resource(String title, String author, String formate, String category, boolean available) {
        this.title = title;
        this.author = author;
        this.formate = formate;
        this.category = category;
        this.available = available;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public String getFormate() {
        return formate;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String toString() {
        return "\nTitle: " + title + "\nAuthor: " + author
        + "\nCategory: " + category + "\nFormate: " + formate
        + "\nAvailable: " + available;
    }
}
