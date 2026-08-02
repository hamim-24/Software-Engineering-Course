
public class CompressionDecorator extends FileProcessorDecorator {

    public CompressionDecorator(FileProcessor wrappee) {
        super(wrappee);
    }

    @Override
    public String process() {
        String content = wrappee.process();
        String result = "ZIP[" + content + "]";
        System.out.println("  [Compress]  " + content + "  ->  " + result);
        return result;
    }
}
