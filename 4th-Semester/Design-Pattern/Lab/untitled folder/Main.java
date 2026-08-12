public class Main {

    public static void main(String[] args) {

        LibraryFacade library = new LibraryFacade();

        User user = new User("Inzamamul", "Student");

        library.login(user);

        System.out.println();

        library.searchResource("Operating System");

        System.out.println();

        library.previewResource("Operating System");

        System.out.println();

        library.borrowResource(user, "Operating System");

        System.out.println();

        library.downloadResource(user, "Operating System");

        System.out.println();

        library.returnResource(user, "Operating System");
    }
}