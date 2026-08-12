public class CatalogService {
    public void display(Resource resource) {
        if (resource == null) return;

        System.out.println(resource.toString());
    }
}
