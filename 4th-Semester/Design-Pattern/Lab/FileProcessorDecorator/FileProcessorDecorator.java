
public abstract class FileProcessorDecorator implements FileProcessor {

    protected final FileProcessor wrappee;

    protected FileProcessorDecorator(FileProcessor wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String process() {
        return wrappee.process(); // delegate by default
    }
}
