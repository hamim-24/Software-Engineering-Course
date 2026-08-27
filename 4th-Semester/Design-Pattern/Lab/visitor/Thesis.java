public class Thesis implements AcademicComponent {
    private String title;
    private double creditHours;
    private double marks;
    private String grade;
    private double gradePoint;
    private String supervisor;
    private String evaluationResult;

    public Thesis(String title, double creditHours, double marks, String supervisor,
        String evaluationResult) {
        this.title = title;
        this.creditHours = creditHours;
        this.marks = marks;
        this.supervisor = supervisor;
        this.evaluationResult = evaluationResult;
    }

    @Override
    public void accept(AcademicVisitor visitor) {
        visitor.visit(this);
    }

    public String getTitle() {
        return title;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getEvaluationResult() {
        return evaluationResult;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }
}