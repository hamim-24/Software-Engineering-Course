public class LabExamPaper extends ExamPaper {

    private String labRoom;
    private int practicalTaskCount;

    public LabExamPaper(String title, String departmentName, String examDate,
                        String instructions, String marksDistribution, String labRoom) {
        super(title, departmentName, examDate, instructions, marksDistribution);
        this.labRoom           = labRoom;
        this.practicalTaskCount = 0;
    }

    public void addPracticalTask(String task) {
        practicalTaskCount++;
        addQuestion("[Practical Task " + practicalTaskCount + "] " + task);
    }

    @Override
    public LabExamPaper clone() {
        LabExamPaper cloned = (LabExamPaper) super.clone();
        cloned.labRoom            = this.labRoom;
        cloned.practicalTaskCount = this.practicalTaskCount;
        return cloned;
    }

    public void setLabRoom(String labRoom) { this.labRoom = labRoom; }

    @Override
    public void displayPaper() {
        System.out.println("\n  [LAB EXAM PAPER]  Lab Room: " + labRoom + "  |  Tasks: " + practicalTaskCount);
        super.displayPaper();
    }
}