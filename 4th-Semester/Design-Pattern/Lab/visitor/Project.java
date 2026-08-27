public class Project implements AcademicComponent {
    private String projectCode;
    private String title;
    private double creditHours;
    private double marks;
    private String grade;
    private double gradePoint;
    private String supervisor;

    public Project(
        String projectCode, String title, double creditHours, double marks, String supervisor) {
        this.projectCode = projectCode;
        this.title = title;
        this.creditHours = creditHours;
        this.marks = marks;
        this.supervisor = supervisor;
    }

    @Override
    public void accept(AcademicVisitor visitor) {
        visitor.visit(this);
    }

    public String getProjectCode() {
        return projectCode;
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

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }
}