public class StatisticsVisitor implements AcademicVisitor {
    private int totalComponents = 0;
    private int passedComponents = 0;
    private int failedComponents = 0;
    private double totalMarks = 0;
    private String highestGrade = "F";

    private void process(double marks, String grade) {
        totalComponents++;
        totalMarks += marks;

        if (grade.equals("F")) {
            failedComponents++;
        } else {
            passedComponents++;
        }

        if (gradePoint(grade) > gradePoint(highestGrade)) {
            highestGrade = grade;
        }
    }

    private double gradePoint(String grade) {
        return switch (grade) {
            case "A+" -> 4.00;
            case "A" -> 3.75;
            case "A-" -> 3.50;
            case "B+" -> 3.25;
            case "B" -> 3.00;
            case "B-" -> 2.75;
            case "C+" -> 2.50;
            case "C" -> 2.25;
            case "D" -> 2.00;
            default -> 0.00;
        };
    }

    @Override
    public void visit(Course course) {
        process(course.getMarks(), course.getGrade());
    }

    @Override
    public void visit(LaboratoryCourse lab) {
        process(lab.getMarks(), lab.getGrade());
    }

    @Override
    public void visit(Project project) {
        process(project.getMarks(), project.getGrade());
    }

    @Override
    public void visit(Thesis thesis) {
        process(thesis.getMarks(), thesis.getGrade());
    }

    public void printStatistics() {
        double average = totalComponents == 0 ? 0 : totalMarks / totalComponents;

        System.out.println("\n===== STATISTICS =====");

        System.out.println("Total Components : " + totalComponents);
        System.out.println("Passed           : " + passedComponents);
        System.out.println("Failed           : " + failedComponents);
        System.out.println("Total Marks      : " + totalMarks);
        System.out.printf("Average Marks    : %.2f%n", average);
        System.out.println("Highest Grade    : " + highestGrade);
    }
}