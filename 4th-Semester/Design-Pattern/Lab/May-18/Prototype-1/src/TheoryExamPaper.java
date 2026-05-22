public class TheoryExamPaper extends ExamPaper {

    private int essayQuestionCount;

    public TheoryExamPaper(String title, String departmentName, String examDate,
                           String instructions, String marksDistribution) {
        super(title, departmentName, examDate, instructions, marksDistribution);
        this.essayQuestionCount = 0;
    }

    public void addEssayQuestion(String question) {
        essayQuestionCount++;
        addQuestion("[Essay Q" + essayQuestionCount + "] " + question);
    }

    @Override
    public TheoryExamPaper clone() {
        TheoryExamPaper cloned = (TheoryExamPaper) super.clone();
        cloned.essayQuestionCount = this.essayQuestionCount;
        return cloned;
    }

    @Override
    public void displayPaper() {
        System.out.println("\n  [THEORY EXAM PAPER]  Essay Qs so far: " + essayQuestionCount);
        super.displayPaper();
    }
}