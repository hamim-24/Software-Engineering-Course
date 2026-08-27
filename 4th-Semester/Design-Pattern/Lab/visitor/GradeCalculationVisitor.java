public class GradeCalculationVisitor implements AcademicVisitor {
    private String calculateGrade(double marks) {
        if (marks >= 80)
            return "A+";
        if (marks >= 75)
            return "A";
        if (marks >= 70)
            return "A-";
        if (marks >= 65)
            return "B+";
        if (marks >= 60)
            return "B";
        if (marks >= 55)
            return "B-";
        if (marks >= 50)
            return "C+";
        if (marks >= 45)
            return "C";
        if (marks >= 40)
            return "D";

        return "F";
    }

    private double calculateGradePoint(double marks) {
        if (marks >= 80)
            return 4.00;
        if (marks >= 75)
            return 3.75;
        if (marks >= 70)
            return 3.50;
        if (marks >= 65)
            return 3.25;
        if (marks >= 60)
            return 3.00;
        if (marks >= 55)
            return 2.75;
        if (marks >= 50)
            return 2.50;
        if (marks >= 45)
            return 2.25;
        if (marks >= 40)
            return 2.00;

        return 0.00;
    }

    @Override
    public void visit(Course course) {
        String grade = calculateGrade(course.getMarks());
        double point = calculateGradePoint(course.getMarks());

        course.setGrade(grade);
        course.setGradePoint(point);
    }

    @Override
    public void visit(LaboratoryCourse lab) {
        String grade = calculateGrade(lab.getMarks());
        double point = calculateGradePoint(lab.getMarks());

        lab.setGrade(grade);
        lab.setGradePoint(point);
    }

    @Override
    public void visit(Project project) {
        String grade = calculateGrade(project.getMarks());
        double point = calculateGradePoint(project.getMarks());

        project.setGrade(grade);
        project.setGradePoint(point);
    }

    @Override
    public void visit(Thesis thesis) {
        String grade = calculateGrade(thesis.getMarks());
        double point = calculateGradePoint(thesis.getMarks());

        thesis.setGrade(grade);
        thesis.setGradePoint(point);
    }
}