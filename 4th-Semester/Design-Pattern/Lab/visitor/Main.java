public class Main {
    public static void main(String[] args) {
        Student student =new Student("2024-001", "Inzamamul Lohani", "Software Engineering", "2024");

        student.addAcademicComponent(new Course("SWE-301", "Software Architecture", 3, 82));

        student.addAcademicComponent(new Course("SWE-302", "Database Systems", 3, 76));

        student.addAcademicComponent(new LaboratoryCourse("SWE-303", "Software Engineering Lab", 1.5, 68));

        student.addAcademicComponent(new Project("SWE-310", "IoT Smart Monitoring System", 3, 85, "Dr. Rahman"));

        student.addAcademicComponent(new Thesis("AI-Based Smart Healthcare System", 4, 78, "Dr. Ahmed", "Excellent"));

        GradeCalculationVisitor gradeVisitor = new GradeCalculationVisitor();

        student.processRecord(gradeVisitor);

        CreditCalculationVisitor creditVisitor = new CreditCalculationVisitor();

        student.processRecord(creditVisitor);

        System.out.println("\nCompleted Credits : " + creditVisitor.getCompletedCredits());

        System.out.println("\n===== ACADEMIC TRANSCRIPT =====");

        System.out.println("Student ID : " + student.getStudentId());

        System.out.println("Name       : " + student.getName());

        System.out.println("Department : " + student.getDepartment());

        System.out.println("Batch      : " + student.getBatch());

        System.out.println();

        System.out.printf(
            "%-10s %-25s %-8s %-8s %-5s%n", "Code", "Title", "Credit", "Point", "Grade");

        System.out.println("------------------------------------------------------------");

        TranscriptGenerationVisitor transcriptVisitor = new TranscriptGenerationVisitor();

        student.processRecord(transcriptVisitor);

        StatisticsVisitor statisticsVisitor = new StatisticsVisitor();

        student.processRecord(statisticsVisitor);

        statisticsVisitor.printStatistics();

        AcademicProbationVisitor probationVisitor = new AcademicProbationVisitor();

        student.processRecord(probationVisitor);

        probationVisitor.printResult();
    }
}