
public class BasicFileProcessor implements FileProcessor {

    private final String content;

    public BasicFileProcessor(String content) {
        this.content = content;
    }

    @Override
    public String process() {
        return content;
    }
}
