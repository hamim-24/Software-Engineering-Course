public class LaboratoryCourse implements AcademicComponent {
    private String courseCode;
    private String title;
    private double creditHours;
    private double marks;
    private String grade;
    private double gradePoint;

    public LaboratoryCourse(String courseCode, String title, double creditHours, double marks) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.marks = marks;
    }

    @Override
    public void accept(AcademicVisitor visitor) {
        visitor.visit(this);
    }

    public String getCourseCode() {
        return courseCode;
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

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }
}