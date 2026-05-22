import java.util.ArrayList;
import java.util.List;

public class PrescriptionTemplate extends MedicalReport {

    private List<String> medicineList;
    private String       dosageInstructions;

    public PrescriptionTemplate() {
        super();
        this.medicineList       = new ArrayList<>();
        this.dosageInstructions = "";
    }

    @Override
    public PrescriptionTemplate clone() {
        PrescriptionTemplate copy = (PrescriptionTemplate) super.clone();
        copy.medicineList = new ArrayList<>(this.medicineList);
        return copy;
    }

    public void addMedicine(String medicine) {
        medicineList.add(medicine);
    }

    public void setDosageInstructions(String instructions) {
        this.dosageInstructions = instructions;
    }

    @Override
    public void generateReport() {
        System.out.println("\n[PrescriptionTemplate] Report generated for: " + patientName);
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.println("  ── PRESCRIPTION ─────────────────────────");
        for (int i = 0; i < medicineList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + medicineList.get(i));
        }
        System.out.println("  Dosage : " + dosageInstructions);
        System.out.println("═══════════════════════════════════════════════\n");
    }

    public List<String> getMedicineList()    { return medicineList;       }
    public String getDosageInstructions()    { return dosageInstructions; }
}