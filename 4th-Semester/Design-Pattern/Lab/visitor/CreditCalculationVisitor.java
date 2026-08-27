public class CreditCalculationVisitor implements AcademicVisitor {
    private double completedCredits = 0;

    @Override
    public void visit(Course course) {
        if (!course.getGrade().equals("F")) {
            completedCredits += course.getCreditHours();
        }
    }

    @Override
    public void visit(LaboratoryCourse lab) {
        if (!lab.getGrade().equals("F")) {
            completedCredits += lab.getCreditHours();
        }
    }

    @Override
    public void visit(Project project) {
        if (!project.getGrade().equals("F")) {
            completedCredits += project.getCreditHours();
        }
    }

    @Override
    public void visit(Thesis thesis) {
        if (!thesis.getGrade().equals("F")) {
            completedCredits += thesis.getCreditHours();
        }
    }

    public double getCompletedCredits() {
        return completedCredits;
    }
}