
public class DecryptionDecorator extends FileProcessorDecorator {

    public DecryptionDecorator(FileProcessor wrappee) {
        super(wrappee);
    }

    @Override
    public String process() {
        String content = wrappee.process();
        if (content.startsWith("ENC(") && content.endsWith(")")) {
            String result = content.substring(4, content.length() - 1);
            System.out.println("  [Decrypt]   " + content + "  ->  " + result);
            return result;
        }
        System.out.println("  [Decrypt]   " + content + "  ->  (not encrypted, left unchanged)");
        return content;
    }
}
