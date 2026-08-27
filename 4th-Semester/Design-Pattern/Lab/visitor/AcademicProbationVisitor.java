public class AcademicProbationVisitor implements AcademicVisitor {
    private boolean hasFailed = false;
    private int failedComponents = 0;

    private void check(String grade) {
        if (grade.equals("F")) {
            hasFailed = true;
            failedComponents++;
        }
    }

    @Override
    public void visit(Course course) {
        check(course.getGrade());
    }

    @Override
    public void visit(LaboratoryCourse lab) {
        check(lab.getGrade());
    }

    @Override
    public void visit(Project project) {
        check(project.getGrade());
    }

    @Override
    public void visit(Thesis thesis) {
        check(thesis.getGrade());
    }

    public void printResult() {
        System.out.println("\n===== ACADEMIC PROBATION =====");

        if (hasFailed) {
            System.out.println("Status : ON ACADEMIC PROBATION");
            System.out.println("Failed Components : " + failedComponents);
        } else {
            System.out.println("Status : GOOD STANDING");
        }
    }
}