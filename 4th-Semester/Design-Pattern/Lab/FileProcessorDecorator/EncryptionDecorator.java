
public class EncryptionDecorator extends FileProcessorDecorator {

    public EncryptionDecorator(FileProcessor wrappee) {
        super(wrappee);
    }

    @Override
    public String process() {
        String content = wrappee.process();
        String result = "ENC(" + content + ")";
        System.out.println("  [Encrypt]   " + content + "  ->  " + result);
        return result;
    }
}
