public class ApprovedState implements DocumentState {

  @Override
  public void edit(Document document) {
    System.out.println("Invalid operation: Approved document cannot be edited.");
  }

  @Override
  public void submit(Document document) {
    System.out.println("Invalid operation: Approved document cannot be resubmitted.");
  }

  @Override
  public void verify(Document document) {
    System.out.println(
        "Invalid operation: Approved document has already been verified.");
  }

  @Override
  public void approve(Document document) {
    System.out.println("Invalid operation: Document is already approved.");
  }

  @Override
  public void reject(Document document, String reason) {
    System.out.println(
        "Invalid operation: Approved document cannot be rejected.");
  }

  @Override
  public void returnForCorrection(Document document) {
    System.out.println("Invalid operation: Approved document cannot be "
                       + "returned for correction.");
  }

  @Override
  public void issue(Document document) {

    System.out.println("Document issued successfully.");

    document.setState(new IssuedState());
  }

  @Override
  public void archive(Document document) {

    System.out.println("Approved document archived.");

    document.setState(new ArchivedState());
  }

  @Override
  public String getStateName() {
    return "APPROVED";
  }
}