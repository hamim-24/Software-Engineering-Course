public class Main {
    public static void main(String[] args) {
        ImageLibrary library = new ImageLibrary();

        User guest = new User("Guest", false, null);
        User friend = new User("Friend", false, "1234");
        User admin = new User("Admin", true, null);

        System.out.println("Guest views public image:");
        library.getImage("sunset.png").display(guest);

        System.out.println("\nGuest views it again (should use cache):");
        library.getImage("sunset.png").display(guest);

        System.out.println("\nGuest tries a PRIVATE image:");
        library.getImage("family.png").display(guest);

        System.out.println("\nAdmin views the same PRIVATE image:");
        library.getImage("family.png").display(admin);

        System.out.println("\nGuest tries a LOCKED image (no code):");
        library.getImage("diary.png").display(guest);

        System.out.println("\nFriend views the LOCKED image (knows the code):");
        library.getImage("diary.png").display(friend);

        System.out.println("\nGuest tries a RESTRICTED image:");
        library.getImage("salary.png").display(guest);
    }
}
