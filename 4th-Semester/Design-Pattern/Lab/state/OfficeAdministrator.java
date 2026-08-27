public class OfficeAdministrator {

  private String name;

  public OfficeAdministrator(String name) { this.name = name; }

  public void issueDocument(Document document) {

    System.out.println("\nAdministrator " + name +
                       " attempts to issue document.");

    document.issue();
  }

  public void archiveDocument(Document document) {

    System.out.println("\nAdministrator " + name +
                       " attempts to archive document.");

    document.archive();
  }
}