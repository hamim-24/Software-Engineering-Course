public class DraftState implements DocumentState {

  @Override
  public void edit(Document document) {

    System.out.println("Document edited successfully.");
  }

  @Override
  public void submit(Document document) {

    System.out.println("Draft submitted for verification.");

    document.setState(new SubmittedState());
  }

  @Override
  public void verify(Document document) {
    System.out.println("Invalid operation: Draft cannot be verified.");
  }

  @Override
  public void approve(Document document) {
    System.out.println("Invalid operation: Draft cannot be approved.");
  }

  @Override
  public void reject(Document document, String reason) {
    System.out.println("Invalid operation: Draft cannot be rejected.");
  }

  @Override
  public void returnForCorrection(Document document) {
    System.out.println("Invalid operation: Draft is already editable.");
  }

  @Override
  public void issue(Document document) {
    System.out.println("Invalid operation: Draft cannot be issued.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Invalid operation: Draft cannot be archived.");
  }

  @Override
  public String getStateName() {
    return "DRAFT";
  }
}