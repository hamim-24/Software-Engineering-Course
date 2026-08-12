import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LibraryFacade {
    private AuthenticationService authenticationService;
    private AuthorizationService authorizationService;
    private searchService searchService;
    private CatalogService catalogService;
    private BorrowService borrowService;
    private PreviewService previewService;
    private DownloadService downloadService;
    private NotificationService notificationService;
    private ActivityLogger activityLogger;

    private List<Resource> resources;

    public LibraryFacade() {
        authenticationService = new AuthenticationService();
        authorizationService = new AuthorizationService();
        searchService = new searchService();
        catalogService = new CatalogService();
        borrowService = new BorrowService();
        previewService = new PreviewService();
        downloadService = new DownloadService();
        notificationService = new NotificationService();
        activityLogger = new ActivityLogger();
        resources = new ArrayList<>();

        resources.add(new Resource(
                "Java Design Patterns",
                "Gamma",
                "Book",
                "PDF",
                true));
        resources.add(new Resource(
                "Operating System",
                "Silberschatz",
                "Book",
                "PDF",
                true));
        resources.add(new Resource(
                "Machine Learning",
                "Andrew Ng",
                "Video",
                "MP4",
                true));
    }
    public void login(User user) {
        authenticationService.login(user);
        activityLogger.log(user.getName() + " logged in.");
    }
    public void searchResource(String title) {
        Resource resource = searchService.search(resources, title);
        catalogService.display(resource);
    }
    public void previewResource(String title) {

        Resource resource = searchService.search(resources, title);

        if(resource != null)

            previewService.preview(resource);

    }
    public void borrowResource(User user, String title) {
        if (!authorizationService.hasAccess(user)) return;
        Resource resource = searchService.search(resources, title);
        if (resource != null) {
            borrowService.borrow(resource);
            notificationService.notify(user, "Borrow complete.");
            activityLogger.log(user.getName() + " borrowed " + title);
        }
    }
    public void downloadResource(User user, String title) {
        if (!authorizationService.hasAccess(user)) return;

        Resource resource = searchService.search(resources, title);
        if (resource != null) {
            downloadService.download(resource);
            activityLogger.log(user.getName() + " downloaded " + title);
        }
    }
    public void returnResource(User user, String title) {
        Resource resource = searchService.search(resources, title);
        if (resource != null) {
            borrowService.returnBook(resource);
            notificationService.notify(user, "Return book.");
            activityLogger.log(user.getName() + " returned " + title);
        }
    }
}