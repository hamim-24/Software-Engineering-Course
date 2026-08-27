public class UnderVerificationState implements DocumentState {

  @Override
  public void edit(Document document) {
    System.out.println("Invalid operation: Document is under verification.");
  }

  @Override
  public void submit(Document document) {
    System.out.println(
        "Invalid operation: Document is already under verification.");
  }

  @Override
  public void verify(Document document) {

    System.out.println("Document verified successfully.");

    document.setRemarks("Verified by authorized officer.");

    document.setState(new PendingApprovalState());
  }

  @Override
  public void approve(Document document) {
    System.out.println(
        "Invalid operation: Document must complete verification first.");
  }

  @Override
  public void reject(Document document, String reason) {

    System.out.println("Document rejected during verification.");

    document.setRemarks(reason);
    document.setState(new RejectedState());
  }

  @Override
  public void returnForCorrection(Document document) {

    System.out.println("Document returned for correction.");

    document.setState(new DraftState());
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
    return "UNDER VERIFICATION";
  }
}