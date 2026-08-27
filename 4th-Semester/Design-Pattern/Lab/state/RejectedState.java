public class RejectedState implements DocumentState {

  @Override
  public void edit(Document document) {
    System.out.println("Invalid operation: Use returnForCorrection() first.");
  }

  @Override
  public void submit(Document document) {
    System.out.println(
        "Invalid operation: Correct the document before resubmission.");
  }

  @Override
  public void verify(Document document) {
    System.out.println(
        "Invalid operation: Rejected document must be corrected first.");
  }

  @Override
  public void approve(Document document) {
    System.out.println(
        "Invalid operation: Rejected document cannot be approved.");
  }

  @Override
  public void reject(Document document, String reason) {
    System.out.println("Invalid operation: Document is already rejected.");
  }

  @Override
  public void returnForCorrection(Document document) {

    System.out.println("Rejected document returned for correction.");

    document.setRemarks("Document returned to applicant for correction.");

    document.setState(new DraftState());
  }

  @Override
  public void issue(Document document) {
    System.out.println(
        "Invalid operation: Rejected document cannot be issued.");
  }

  @Override
  public void archive(Document document) {
    System.out.println(
        "Invalid operation: Rejected document cannot be archived.");
  }

  @Override
  public String getStateName() {
    return "REJECTED";
  }
}