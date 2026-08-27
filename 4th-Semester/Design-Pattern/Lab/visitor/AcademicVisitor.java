public interface AcademicVisitor {
    void visit(Course course);

    void visit(LaboratoryCourse lab);

    void visit(Project project);

    void visit(Thesis thesis);
}