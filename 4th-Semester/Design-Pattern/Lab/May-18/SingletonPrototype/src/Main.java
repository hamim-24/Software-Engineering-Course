public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║   CITY GENERAL HOSPITAL — Management System      ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        HospitalDatabaseManager db1 = HospitalDatabaseManager.getInstance();
        HospitalDatabaseManager db2 = HospitalDatabaseManager.getInstance();
        HospitalLogger          lg1 = HospitalLogger.getInstance();
        HospitalLogger          lg2 = HospitalLogger.getInstance();
        System.out.println("\n[Singleton Check] DB  instance same? " + (db1 == db2));
        System.out.println("[Singleton Check] Log instance same? " + (lg1 == lg2));

        System.out.println("\n── Initializing Staff ───────────────────────────");
        Receptionist receptionist = new Receptionist("R-001");
        Doctor       doctor       = new Doctor("D-001", "Dr. Sarah Khan");
        LabTechnician labTech     = new LabTechnician("L-001", "Mr. Ahmed Raza");
        PharmacyModule pharmacy   = new PharmacyModule();

        System.out.println("\n── Patient Registration ─────────────────────────");
        receptionist.registerPatient("P-1001", "John Smith", "42");
        receptionist.assignRoom("P-1001", "Ward-3B");

        System.out.println("\n── Doctor Consultation ──────────────────────────");
        doctor.updateDiagnosis("P-1001", "Acute Bronchitis");

        PrescriptionTemplate rx = doctor.createPrescription(
                "John Smith",
                "Acute Bronchitis",
                "2026-05-23",
                new String[]{"Amoxicillin 500mg", "Bromhexine 8mg", "Paracetamol 500mg"},
                "Take after meals. Amoxicillin twice daily, others thrice daily."
        );

        System.out.println("\n── Prescription Report ───────────────────────────");
        rx.printReport();

        System.out.println("── Pharmacy Dispensing ──────────────────────────");
        pharmacy.dispenseMedicine("P-1001", rx);
        pharmacy.checkInventory("Amoxicillin 500mg");

        System.out.println("\n── Laboratory Tests ─────────────────────────────");
        LabReportTemplate labReport = labTech.createLabReport(
                "John Smith",
                "Dr. Sarah Khan",
                "Acute Bronchitis",
                "2026-05-23",
                new String[][]{
                        {"WBC Count",       "11,500 cells/μL (High)"},
                        {"CRP",             "28 mg/L (Elevated)"},
                        {"Haemoglobin",     "13.8 g/dL (Normal)"},
                        {"Chest X-Ray",     "Mild opacity in right lower lobe"}
                },
                "Elevated WBC and CRP consistent with bacterial infection."
        );

        System.out.println("\n── Lab Report ───────────────────────────────────");
        labReport.printReport();

        System.out.println("── Discharge Summary ────────────────────────────");
        DischargeSummaryTemplate discharge = doctor.createDischargeSummary(
                "John Smith",
                "Acute Bronchitis — Resolved",
                "2026-05-20",
                "2026-05-23",
                "Patient received IV antibiotics for 3 days. Fever resolved on Day 2. " +
                        "Oxygen saturation stable at 98%.",
                "Rest for 1 week. Complete antibiotic course. Follow up in 7 days.",
                "2026-05-23"
        );
        discharge.printReport();

        System.out.println("── Prototype Independence Check ─────────────────");
        PrescriptionTemplate rx2 = doctor.createPrescription(
                "Mary Jones", "Hypertension", "2026-05-23",
                new String[]{"Amlodipine 5mg", "Losartan 50mg"},
                "Once daily in the morning."
        );
        System.out.println("Rx1 patient: " + rx.getPatientName()
                + " | Rx2 patient: " + rx2.getPatientName()
                + " — Independent? " + !rx.getPatientName().equals(rx2.getPatientName()));

        System.out.println("\n── System Status ────────────────────────────────");
        HospitalDatabaseManager.getInstance().printStatus();
        System.out.println("  Auth sessions : "
                + AuthenticationManager.getInstance().getActiveSessionCount());
        System.out.println("  Total log entries: "
                + HospitalLogger.getInstance().getLogCount());

        HospitalDatabaseManager.getInstance().closeConnection();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║  Singleton + Prototype patterns applied cleanly  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}