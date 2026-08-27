public class ArchivedState implements DocumentState {

  @Override
  public void edit(Document document) {
    System.out.println(
        "Invalid operation: Archived document cannot be modified.");
  }

  @Override
  public void submit(Document document) {
    System.out.println(
        "Invalid operation: Archived document cannot be submitted.");
  }

  @Override
  public void verify(Document document) {
    System.out.println(
        "Invalid operation: Archived document cannot be verified.");
  }

  @Override
  public void approve(Document document) {
    System.out.println(
        "Invalid operation: Archived document cannot be approved.");
  }

  @Override
  public void reject(Document document, String reason) {
    System.out.println(
        "Invalid operation: Archived document cannot be rejected.");
  }

  @Override
  public void returnForCorrection(Document document) {
    System.out.println(
        "Invalid operation: Archived document cannot be returned.");
  }

  @Override
  public void issue(Document document) {
    System.out.println("Invalid operation: Archived document has already "
                       + "completed its lifecycle.");
  }

  @Override
  public void archive(Document document) {
    System.out.println("Invalid operation: Document is already archived.");
  }

  @Override
  public String getStateName() {
    return "ARCHIVED";
  }
}