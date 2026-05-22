public class Doctor {

    private final HospitalDatabaseManager db;
    private final HospitalLogger          logger;
    private final AuthenticationManager   auth;
    private final String                  doctorName;

    private final PrescriptionTemplate    prescriptionPrototype;
    private final DischargeSummaryTemplate dischargePrototype;

    public Doctor(String doctorId, String doctorName) {
        this.doctorName = doctorName;
        this.db         = HospitalDatabaseManager.getInstance();
        this.logger     = HospitalLogger.getInstance();
        this.auth       = AuthenticationManager.getInstance();
        auth.authenticate(doctorId, "Doctor");

        prescriptionPrototype = new PrescriptionTemplate();
        prescriptionPrototype.setDoctorName(doctorName);

        dischargePrototype = new DischargeSummaryTemplate();
        dischargePrototype.setDoctorName(doctorName);
    }

    public void updateDiagnosis(String patientId, String diagnosis) {
        db.connect();
        db.updatePatientRecord(patientId, "diagnosis", diagnosis);
        logger.log("Doctor", "Diagnosis updated for patient " + patientId + ": " + diagnosis);
        System.out.println("[Doctor:" + doctorName + "] Diagnosis updated for " + patientId);
    }

    public PrescriptionTemplate createPrescription(String patientName, String diagnosis,
                                                   String date, String[] medicines,
                                                   String dosage) {
        PrescriptionTemplate rx = prescriptionPrototype.clone();
        rx.setPatientName(patientName);
        rx.setDiagnosis(diagnosis);
        rx.setReportDate(date);
        rx.setDosageInstructions(dosage);
        for (String m : medicines) rx.addMedicine(m);
        rx.generateReport();

        db.connect();
        db.executeQuery("INSERT INTO prescriptions (patient, doctor, date) VALUES ('"
                + patientName + "', '" + doctorName + "', '" + date + "')");
        logger.log("Doctor", "Prescription created for: " + patientName);
        return rx;
    }

    public DischargeSummaryTemplate createDischargeSummary(String patientName,
                                                           String diagnosis,
                                                           String admitted,
                                                           String discharged,
                                                           String treatment,
                                                           String followUp,
                                                           String date) {
        DischargeSummaryTemplate ds = dischargePrototype.clone();
        ds.setPatientName(patientName);
        ds.setDiagnosis(diagnosis);
        ds.setReportDate(date);
        ds.setAdmissionDate(admitted);
        ds.setDischargeDate(discharged);
        ds.setTreatmentSummary(treatment);
        ds.setFollowUpInstructions(followUp);
        ds.generateReport();
        logger.log("Doctor", "Discharge summary created for: " + patientName);
        return ds;
    }
}