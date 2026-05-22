import java.util.ArrayList;
import java.util.List;

public abstract class ExamPaper implements Cloneable {

    protected String title;
    protected String departmentName;
    protected String examDate;
    protected String instructions;
    protected String marksDistribution;
    protected List<String> questionList;

    public ExamPaper(String title, String departmentName, String examDate, String instructions, String marksDistribution) {
        this.title             = title;
        this.departmentName    = departmentName;
        this.examDate          = examDate;
        this.instructions      = instructions;
        this.marksDistribution = marksDistribution;
        this.questionList      = new ArrayList<>();
    }

    @Override
    public ExamPaper clone() {
        try {
            ExamPaper cloned = (ExamPaper) super.clone();
            cloned.questionList = new ArrayList<>(this.questionList);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed: " + e.getMessage());
        }
    }

    public void addQuestion(String question) {
        questionList.add(question);
    }

    public void removeQuestion(String question) {
        questionList.remove(question);
    }

    public void displayPaper() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("          [UNIVERSITY LOGO]");
        System.out.println("  Department : " + departmentName);
        System.out.println("  Exam Title : " + title);
        System.out.println("  Date       : " + examDate);
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("  Instructions: " + instructions);
        System.out.println("  Marks       : " + marksDistribution);
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("  Questions:");
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println("  Q" + (i + 1) + ". " + questionList.get(i));
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    public void setTitle(String title)                       { this.title = title; }
    public void setExamDate(String examDate)                 { this.examDate = examDate; }
    public void setInstructions(String instructions)         { this.instructions = instructions; }
    public void setMarksDistribution(String marksDistribution) { this.marksDistribution = marksDistribution; }

    public String getTitle()          { return title; }
    public String getDepartmentName() { return departmentName; }
    public String getExamDate()       { return examDate; }
}