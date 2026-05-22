import java.util.LinkedHashMap;
import java.util.Map;

public class LabReportTemplate extends MedicalReport {

    private Map<String, String> testResults;
    private String              labTechnicianName;
    private String              observations;

    public LabReportTemplate() {
        super();
        this.testResults       = new LinkedHashMap<>();
        this.labTechnicianName = "";
        this.observations      = "";
    }

    @Override
    public LabReportTemplate clone() {
        LabReportTemplate copy = (LabReportTemplate) super.clone();
        copy.testResults = new LinkedHashMap<>(this.testResults);
        return copy;
    }

    public void addTestResult(String testName, String result) {
        testResults.put(testName, result);
    }

    public void addObservation(String observation) {
        this.observations = observation;
    }

    public void setLabTechnicianName(String name) {
        this.labTechnicianName = name;
    }

    @Override
    public void generateReport() {
        System.out.println("\n[LabReportTemplate] Lab report generated for: " + patientName);
    }

    @Override
    public void printReport() {
        super.printReport();
        System.out.println("  Lab Tech  : " + labTechnicianName);
        System.out.println("  ── TEST RESULTS ─────────────────────────");
        for (Map.Entry<String, String> entry : testResults.entrySet()) {
            System.out.printf("  %-20s : %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println("  Observations: " + observations);
        System.out.println("═══════════════════════════════════════════════\n");
    }

    public Map<String, String> getTestResults()   { return testResults;       }
    public String getLabTechnicianName()           { return labTechnicianName; }
    public String getObservations()                { return observations;      }
}