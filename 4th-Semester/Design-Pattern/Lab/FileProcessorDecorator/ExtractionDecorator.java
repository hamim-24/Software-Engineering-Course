
public class ExtractionDecorator extends FileProcessorDecorator {

    public ExtractionDecorator(FileProcessor wrappee) {
        super(wrappee);
    }

    @Override
    public String process() {
        String content = wrappee.process();
        if (content.startsWith("ZIP[") && content.endsWith("]")) {
            String result = content.substring(4, content.length() - 1);
            System.out.println("  [Extract]   " + content + "  ->  " + result);
            return result;
        }
        System.out.println("  [Extract]   " + content + "  ->  (not compressed, left unchanged)");
        return content;
    }
}
