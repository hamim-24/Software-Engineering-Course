public class ApprovingOfficer {

  private String name;

  public ApprovingOfficer(String name) { this.name = name; }

  public void approveDocument(Document document) {

    System.out.println("\nApproving Officer " + name + " attempts approval.");

    document.setAssignedOfficer(name);
    document.approve();
  }

  public void rejectDocument(Document document, String reason) {

    System.out.println("\nApproving Officer " + name + " rejects document.");

    document.setAssignedOfficer(name);
    document.reject(reason);
  }
}