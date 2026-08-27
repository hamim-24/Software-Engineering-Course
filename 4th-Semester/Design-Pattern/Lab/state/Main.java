public class Main {

  public static void main(String[] args) {

    Employee employee = new Employee("Inzamamul", "EMP-101");

    VerifyingOfficer verifier = new VerifyingOfficer("Mr. Rahman");

    ApprovingOfficer approver = new ApprovingOfficer("Dr. Ahmed");

    OfficeAdministrator administrator = new OfficeAdministrator("Ms. Fatima");

    Document document =
        employee.createDocument("DOC-2026-001", "Government Project Proposal",
                                "Software Engineering Department");

    document.displayStatus();

    employee.editDocument(document);

    employee.submitDocument(document);

    document.displayStatus();

    verifier.verifyDocument(document);

    document.displayStatus();

    verifier.returnForCorrection(document);

    document.displayStatus();

    employee.editDocument(document);

    employee.submitDocument(document);

    verifier.verifyDocument(document);

    document.displayStatus();

    approver.approveDocument(document);

    document.displayStatus();

    employee.editDocument(document);

    administrator.issueDocument(document);

    document.displayStatus();

    administrator.archiveDocument(document);

    document.displayStatus();

    employee.editDocument(document);

    approver.approveDocument(document);
  }
}