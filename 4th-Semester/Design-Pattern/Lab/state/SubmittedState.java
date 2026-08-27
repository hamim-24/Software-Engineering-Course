public class SubmittedState implements DocumentState {

  @Override
  public void edit(Document document) {
    System.out.println(
        "Invalid operation: Submitted document cannot be edited.");
  }

  @Override
  public void submit(Document document) {
    System.out.println("Invalid operation: Document is already submitted.");
  }

  @Override
  public void verify(Document document) {

    System.out.println("Document sent to verification.");

    document.setState(new UnderVerificationState());
  }

  @Override
  public void approve(Document document) {
    System.out.println("Invalid operation: Document must be verified first.");
  }

  @Override
  public void reject(Document document, String reason) {
    System.out.println(
        "Invalid operation: Document cannot be rejected before verification.");
  }

  @Override
  public void returnForCorrection(Document document) {
    System.out.println("Invalid operation: Document is awaiting verification.");
  }

  @Override
  public void issue(Document document) {
    System.out.println("Invalid operation: Document cannot be issued.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Invalid operation: Document cannot be archived.");
  }

  @Override
  public String getStateName() {
    return "SUBMITTED";
  }
}