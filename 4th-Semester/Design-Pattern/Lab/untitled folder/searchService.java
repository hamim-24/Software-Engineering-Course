import java.util.List;

public class searchService {
    public Resource search(List<Resource> resources, String title) {
        for (Resource r : resources) {
            if (r.getTitle().equals(title)) {
                System.out.println("Resource found.");
                return r;
            }
        }
        System.out.println("Resource not found.");
        return null;
    }
}
