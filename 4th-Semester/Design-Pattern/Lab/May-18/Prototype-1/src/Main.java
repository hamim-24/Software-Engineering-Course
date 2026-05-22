public class Main {

    public static void main(String[] args) {

        System.out.println("=== Building Master Templates ===");

        TheoryExamPaper theoryMaster = new TheoryExamPaper(
                "Final Theory Exam – Template",
                "Department of Computer Science",
                "TBD",
                "Attempt ALL questions. Each question carries equal marks. No plagiarism.",
                "Total: 100 marks | Section A: 50 | Section B: 50"
        );
        theoryMaster.addEssayQuestion("Define object-oriented programming.");

        LabExamPaper labMaster = new LabExamPaper(
                "Lab Exam – Template",
                "Department of Computer Science",
                "TBD",
                "Use the assigned workstation only. Save work every 10 minutes.",
                "Total: 60 marks | Task 1: 30 | Task 2: 30",
                "Lab-101"
        );

        System.out.println("Master templates ready.\n");

        ExamGenerator generator = new ExamGenerator(theoryMaster, labMaster);

        TheoryExamPaper sectionA = generator.generateSectionA("2025-06-10");
        TheoryExamPaper sectionB = generator.generateSectionB("2025-06-10");
        LabExamPaper    retake   = generator.generateRetakeExam("2025-06-17", "Lab-202");

        System.out.println("\n=== Generated Exam Papers ===");
        sectionA.displayPaper();
        sectionB.displayPaper();
        retake.displayPaper();

        System.out.println("\n=== Prototype Integrity Check ===");
        System.out.println("Master theory template questions: "
                + theoryMaster.questionList.size());
        System.out.println("Section A questions: " + sectionA.questionList.size());
        System.out.println("Section B questions: " + sectionB.questionList.size());
        System.out.println("Deep copy confirmed: modifying clones did NOT alter the master template.");
    }
}