
public class PasswordProtectionDecorator extends FileProcessorDecorator {

    private final String password;

    public PasswordProtectionDecorator(FileProcessor wrappee, String password) {
        super(wrappee);
        this.password = password;
    }

    @Override
    public String process() {
        String content = wrappee.process();
        String result = "LOCKED{" + password + ":" + content + "}";
        System.out.println("  [Protect]   " + content + "  ->  " + result);
        return result;
    }
}
