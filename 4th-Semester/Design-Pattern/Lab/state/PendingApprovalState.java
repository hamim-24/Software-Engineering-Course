public class PendingApprovalState implements DocumentState {

  @Override
  public void edit(Document document) {
    System.out.println("Invalid operation: Document is awaiting approval.");
  }

  @Override
  public void submit(Document document) {
    System.out.println("Invalid operation: Document is already submitted.");
  }

  @Override
  public void verify(Document document) {
    System.out.println(
        "Invalid operation: Document has already been verified.");
  }

  @Override
  public void approve(Document document) {

    System.out.println("Document approved successfully.");

    document.setRemarks("Approved by authorized authority.");

    document.setState(new ApprovedState());
  }

  @Override
  public void reject(Document document, String reason) {

    System.out.println("Document rejected by approving authority.");

    document.setRemarks("Rejection Reason: " + reason);

    document.setState(new RejectedState());
  }

  @Override
  public void returnForCorrection(Document document) {
    System.out.println("Invalid operation: Document is awaiting approval.");
  }

  @Override
  public void issue(Document document) {
    System.out.println("Invalid operation: Document must be approved first.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Invalid operation: Document must be approved first.");
  }

  @Override
  public String getStateName() {
    return "PENDING APPROVAL";
  }
}