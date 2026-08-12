public class BorrowService {
    public void borrow(Resource resource) {
        if (resource.isAvailable()) {
            resource.setAvailable(false);
            System.out.println("Borrow successful.");
            return;
        }
        System.out.println("Already borrowed.");
    }
    public void returnBook(Resource resource) {
        resource.setAvailable(true);
        System.out.println("Returned successful.");
    }
}
