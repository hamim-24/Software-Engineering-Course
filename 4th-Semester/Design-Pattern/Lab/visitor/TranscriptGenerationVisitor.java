public class TranscriptGenerationVisitor implements AcademicVisitor {
    @Override
    public void visit(Course course) {
        System.out.printf("%-10s %-25s %-8.1f %-8.2f %-5s%n", course.getCourseCode(),
            course.getTitle(), course.getCreditHours(), course.getGradePoint(), course.getGrade());
    }

    @Override
    public void visit(LaboratoryCourse lab) {
        System.out.printf("%-10s %-25s %-8.1f %-8.2f %-5s%n", lab.getCourseCode(), lab.getTitle(),
            lab.getCreditHours(), lab.getGradePoint(), lab.getGrade());
    }

    @Override
    public void visit(Project project) {
        System.out.printf("%-10s %-25s %-8.1f %-8.2f %-5s%n", "PROJECT", project.getTitle(),
            project.getCreditHours(), project.getGradePoint(), project.getGrade());
    }

    @Override
    public void visit(Thesis thesis) {
        System.out.printf("%-10s %-25s %-8.1f %-8.2f %-5s%n", "THESIS", thesis.getTitle(),
            thesis.getCreditHours(), thesis.getGradePoint(), thesis.getGrade());
    }
}