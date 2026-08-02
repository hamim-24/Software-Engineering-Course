
public class UnlockDecorator extends FileProcessorDecorator {

    private final String passwordAttempt;

    public UnlockDecorator(FileProcessor wrappee, String passwordAttempt) {
        super(wrappee);
        this.passwordAttempt = passwordAttempt;
    }

    @Override
    public String process() {
        String content = wrappee.process();

        if (content.startsWith("LOCKED{") && content.endsWith("}")) {
            String inner = content.substring(7, content.length() - 1);
            int sep = inner.indexOf(':');
            if (sep != -1) {
                String storedPassword = inner.substring(0, sep);
                String originalContent = inner.substring(sep + 1);

                if (storedPassword.equals(passwordAttempt)) {
                    System.out.println("  [Unlock]    " + content + "  ->  " + originalContent);
                    return originalContent;
                } else {
                    System.out.println("  [Unlock]    WRONG PASSWORD - content remains locked.");
                    return content;
                }
            }
        }

        System.out.println("  [Unlock]    " + content + "  ->  (not password-protected, left unchanged)");
        return content;
    }
}
