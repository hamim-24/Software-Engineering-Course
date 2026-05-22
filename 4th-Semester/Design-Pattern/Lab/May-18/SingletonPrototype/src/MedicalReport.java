public abstract class MedicalReport implements Cloneable {

    protected String patientName;
    protected String doctorName;
    protected String diagnosis;
    protected String reportDate;
    protected String hospitalHeader;

    public MedicalReport() {
        this.hospitalHeader =
                "═══════════════════════════════════════════════\n" +
                        "        CITY GENERAL HOSPITAL                  \n" +
                        "    123 Health Ave | Tel: 555-0100             \n" +
                        "═══════════════════════════════════════════════";
    }

    @Override
    public MedicalReport clone() {
        try {
            return (MedicalReport) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed: " + e.getMessage());
        }
    }

    public abstract void generateReport();

    public void printReport() {
        System.out.println(hospitalHeader);
        System.out.println("  Patient   : " + patientName);
        System.out.println("  Doctor    : " + doctorName);
        System.out.println("  Diagnosis : " + diagnosis);
        System.out.println("  Date      : " + reportDate);
    }

    public void setPatientName(String n)  { this.patientName  = n; }
    public void setDoctorName(String n)   { this.doctorName   = n; }
    public void setDiagnosis(String d)    { this.diagnosis    = d; }
    public void setReportDate(String d)   { this.reportDate   = d; }

    public String getPatientName()  { return patientName;  }
    public String getDoctorName()   { return doctorName;   }
    public String getDiagnosis()    { return diagnosis;    }
    public String getReportDate()   { return reportDate;   }
}