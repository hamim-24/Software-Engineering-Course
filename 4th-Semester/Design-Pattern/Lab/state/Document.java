import java.time.LocalDate;

public class Document {

  private String documentId;
  private String title;
  private String applicant;
  private String department;
  private LocalDate creationDate;
  private String assignedOfficer;
  private String remarks;

  private DocumentState currentState;

  public Document(String documentId, String title, String applicant,
                  String department) {

    this.documentId = documentId;
    this.title = title;
    this.applicant = applicant;
    this.department = department;
    this.creationDate = LocalDate.now();

    this.currentState = new DraftState();
  }

  public void setState(DocumentState state) {
    this.currentState = state;

    System.out.println("Document state changed to: " + state.getStateName());
  }

  public DocumentState getState() { return currentState; }

  public void edit() { currentState.edit(this); }

  public void submit() { currentState.submit(this); }

  public void verify() { currentState.verify(this); }

  public void approve() { currentState.approve(this); }

  public void reject(String reason) { currentState.reject(this, reason); }

  public void returnForCorrection() { currentState.returnForCorrection(this); }

  public void issue() { currentState.issue(this); }

  public void archive() { currentState.archive(this); }

  public void displayStatus() {

    System.out.println("\n========== DOCUMENT STATUS ==========");
    System.out.println("Document ID      : " + documentId);
    System.out.println("Title            : " + title);
    System.out.println("Applicant        : " + applicant);
    System.out.println("Department       : " + department);
    System.out.println("Creation Date    : " + creationDate);
    System.out.println("Assigned Officer : " + assignedOfficer);
    System.out.println("Remarks          : " + remarks);
    System.out.println("Current State    : " + currentState.getStateName());
    System.out.println("=====================================");
  }

  public String getDocumentId() { return documentId; }

  public String getTitle() { return title; }

  public String getApplicant() { return applicant; }

  public String getDepartment() { return department; }

  public LocalDate getCreationDate() { return creationDate; }

  public String getAssignedOfficer() { return assignedOfficer; }

  public String getRemarks() { return remarks; }

  public void setTitle(String title) { this.title = title; }

  public void setAssignedOfficer(String assignedOfficer) {
    this.assignedOfficer = assignedOfficer;
  }

  public void setRemarks(String remarks) { this.remarks = remarks; }
}