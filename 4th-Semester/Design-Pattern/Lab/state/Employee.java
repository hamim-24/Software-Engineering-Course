public class Employee {

  private String name;
  private String employeeId;

  public Employee(String name, String employeeId) {
    this.name = name;
    this.employeeId = employeeId;
  }

  public Document createDocument(String documentId, String title,
                                 String department) {

    Document document = new Document(documentId, title, name, department);

    System.out.println("\nEmployee " + name + " created document " +
                       documentId);

    return document;
  }

  public void editDocument(Document document) {

    System.out.println("\nEmployee " + name + " attempts to edit.");

    document.edit();
  }

  public void submitDocument(Document document) {

    System.out.println("\nEmployee " + name + " submits document.");

    document.submit();
  }
}