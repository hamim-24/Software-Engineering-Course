public class LabTechnician {

    private final HospitalDatabaseManager db;
    private final HospitalLogger          logger;
    private final AuthenticationManager   auth;
    private final String                  techName;

    // Prototype template
    private final LabReportTemplate labReportPrototype;

    public LabTechnician(String techId, String techName) {
        this.techName = techName;
        this.db       = HospitalDatabaseManager.getInstance();
        this.logger   = HospitalLogger.getInstance();
        this.auth     = AuthenticationManager.getInstance();
        auth.authenticate(techId, "LabTechnician");

        labReportPrototype = new LabReportTemplate();
        labReportPrototype.setLabTechnicianName(techName);
    }

    public LabReportTemplate createLabReport(String patientName, String doctorName,
                                             String diagnosis, String date,
                                             String[][] tests, String observations) {
        LabReportTemplate report = labReportPrototype.clone();
        report.setPatientName(patientName);
        report.setDoctorName(doctorName);
        report.setDiagnosis(diagnosis);
        report.setReportDate(date);
        for (String[] t : tests) report.addTestResult(t[0], t[1]);
        report.addObservation(observations);
        report.generateReport();

        db.connect();
        db.executeQuery("INSERT INTO lab_reports (patient, tech, date) VALUES ('"
                + patientName + "', '" + techName + "', '" + date + "')");
        logger.log("LabTechnician", "Lab report created for: " + patientName);
        return report;
    }
}