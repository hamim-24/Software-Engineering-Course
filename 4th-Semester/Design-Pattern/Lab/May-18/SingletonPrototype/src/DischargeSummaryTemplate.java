public class DischargeSummaryTemplate extends MedicalReport {

    private String admissionDate;
    private String dischargeDate;
    private String treatmentSummary;
    private String followUpInstructions;

    public DischargeSummaryTemplate() {
        super();
        this.admissionDate       = "";
        this.dischargeDate       = "";
        this.treatmentSummary    = "";
        this.followUpInstructions = "";
    }

    @Override
    public DischargeSummaryTemplate clone() {
        return (DischargeSummaryTemplate) super.clone();
    }

    public void setAdmissionDate(String date)        { this.admissionDate        = date; }
    public void setDischargeDate(String date)        { this.dischargeDate        = date; }
    public void setTreatmentSummary(String summary)  { this.treatmentSummary     = summary; }
    public void setFollowUpInstructions(String instr){ this.followUpInstructions = instr; }

    @Override
    public void generateReport() {
        System.out.println("\n[DischargeSummary] Summary generated for: " + patientName);
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.println("  Admitted  : " + admissionDate);
        System.out.println("  Discharged: " + dischargeDate);
        System.out.println("  ── TREATMENT SUMMARY ────────────────────");
        System.out.println("  " + treatmentSummary);
        System.out.println("  ── FOLLOW-UP ────────────────────────────");
        System.out.println("  " + followUpInstructions);
        System.out.println("═══════════════════════════════════════════════\n");
    }
}