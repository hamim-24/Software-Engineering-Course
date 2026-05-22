public class ExamGenerator {

    private TheoryExamPaper theoryTemplate;
    private LabExamPaper    labTemplate;

    public ExamGenerator(TheoryExamPaper theoryTemplate, LabExamPaper labTemplate) {
        this.theoryTemplate = theoryTemplate;
        this.labTemplate    = labTemplate;
    }

    public TheoryExamPaper generateSectionA(String date) {
        TheoryExamPaper sectionA = theoryTemplate.clone();
        sectionA.setTitle("Final Theory Exam – Section A");
        sectionA.setExamDate(date);
        sectionA.addEssayQuestion("Explain the SOLID principles with examples.");
        sectionA.addEssayQuestion("Compare procedural and object-oriented programming.");
        System.out.println("[ExamGenerator] Section A paper generated.");
        return sectionA;
    }

    public TheoryExamPaper generateSectionB(String date) {
        TheoryExamPaper sectionB = theoryTemplate.clone();
        sectionB.setTitle("Final Theory Exam – Section B");
        sectionB.setExamDate(date);
        sectionB.addEssayQuestion("Describe the Prototype design pattern and its benefits.");
        sectionB.addEssayQuestion("What is polymorphism? Give a real-world analogy.");
        System.out.println("[ExamGenerator] Section B paper generated.");
        return sectionB;
    }

    public LabExamPaper generateRetakeExam(String date, String labRoom) {
        LabExamPaper retake = labTemplate.clone();
        retake.setTitle("Retake Lab Exam");
        retake.setExamDate(date);
        retake.setLabRoom(labRoom);
        retake.setInstructions("All retake students must sit in designated lab seats. No discussion allowed.");
        retake.addPracticalTask("Write a Java program implementing the Singleton pattern.");
        retake.addPracticalTask("Demonstrate deep vs shallow copy in code.");
        System.out.println("[ExamGenerator] Retake lab exam generated.");
        return retake;
    }
}