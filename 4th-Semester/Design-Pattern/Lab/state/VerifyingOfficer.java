public class VerifyingOfficer {

  private String name;

  public VerifyingOfficer(String name) { this.name = name; }

  public void verifyDocument(Document document) {

    System.out.println("\nVerifying Officer " + name +
                       " attempts verification.");

    document.setAssignedOfficer(name);
    document.verify();
  }

  public void returnForCorrection(Document document) {

    System.out.println("\nVerifying Officer " + name + " returns document.");

    document.returnForCorrection();
  }
}