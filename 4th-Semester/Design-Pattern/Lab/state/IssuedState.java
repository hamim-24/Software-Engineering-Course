public class IssuedState implements DocumentState {

    @Override
    public void edit(Document document) {
        System.out.println("Invalid operation: Issued document cannot be edited.");
    }

    @Override
    public void submit(Document document) {
        System.out.println(
                "Invalid operation: Issued document cannot be submitted.");
    }

    @Override
    public void verify(Document document) {
        System.out.println(
                "Invalid operation: Issued document does not require verification.");
    }

    @Override
    public void approve(Document document) {
        System.out.println(
                "Invalid operation: Document is already approved and issued.");
    }

    @Override
    public void reject(Document document, String reason) {
        System.out.println(
                "Invalid operation: Issued document cannot be rejected.");
    }

    @Override
    public void returnForCorrection(Document document) {
        System.out.println(
                "Invalid operation: Issued document cannot be returned.");
    }

    @Override
    public void issue(Document document) {
        System.out.println("Invalid operation: Document is already issued.");
    }

    @Override
    public void archive(Document document) {

        System.out.println("Issued document archived successfully.");

        document.setState(new ArchivedState());
    }

    @Override
    public String getStateName() {
        return "ISSUED";
    }
}