
public class Main {

    public static void main(String[] args) {

        String original = "Hello World";

        section("1) Basic processor - no decoration");
        FileProcessor basic = new BasicFileProcessor(original);
        System.out.println("Result: " + basic.process());

        section("2) Encrypt, then compress");
        FileProcessor encryptThenCompress =
                new CompressionDecorator(
                        new EncryptionDecorator(
                                new BasicFileProcessor(original)));
        System.out.println("Result: " + encryptThenCompress.process());

        section("3) Reverse it: extract, then decrypt");
        FileProcessor reversed =
                new DecryptionDecorator(
                        new ExtractionDecorator(
                                new BasicFileProcessor(encryptThenCompress.process())));
        System.out.println("Result: " + reversed.process());

        section("4) Password-protect, then encrypt (different order)");
        FileProcessor protectThenEncrypt =
                new EncryptionDecorator(
                        new PasswordProtectionDecorator(
                                new BasicFileProcessor(original), "secret123"));
        System.out.println("Result: " + protectThenEncrypt.process());

        section("5) Reverse #4: decrypt, then unlock with correct password");
        FileProcessor unlockCorrect =
                new UnlockDecorator(
                        new DecryptionDecorator(
                                new BasicFileProcessor(protectThenEncrypt.process())),
                        "secret123");
        System.out.println("Result: " + unlockCorrect.process());

        section("6) Same as #5 but with the WRONG password");
        FileProcessor unlockWrong =
                new UnlockDecorator(
                        new DecryptionDecorator(
                                new BasicFileProcessor(protectThenEncrypt.process())),
                        "guess456");
        System.out.println("Result: " + unlockWrong.process());

        section("7) Stack all six: protect -> encrypt -> compress, then fully reverse");
        FileProcessor allApplied =
                new CompressionDecorator(
                        new EncryptionDecorator(
                                new PasswordProtectionDecorator(
                                        new BasicFileProcessor(original), "pw1")));
        String fullyProcessed = allApplied.process();
        System.out.println("Fully processed: " + fullyProcessed);

        System.out.println("-- now reversing --");
        FileProcessor allReversed =
                new UnlockDecorator(
                        new DecryptionDecorator(
                                new ExtractionDecorator(
                                        new BasicFileProcessor(fullyProcessed))),
                        "pw1");
        System.out.println("Back to original: " + allReversed.process());
    }

    private static void section(String title) {
        System.out.println("\n=== " + title + " ===");
    }
}
