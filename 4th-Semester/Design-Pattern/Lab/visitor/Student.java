import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private String department;
    private String batch;

    private List<AcademicComponent> academicComponents;

    public Student(String studentId, String name, String department, String batch) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.batch = batch;

        academicComponents = new ArrayList<>();
    }

    public void addAcademicComponent(AcademicComponent component) {
        academicComponents.add(component);
    }

    public void removeAcademicComponent(AcademicComponent component) {
        academicComponents.remove(component);
    }

    public void processRecord(AcademicVisitor visitor) {
        for (AcademicComponent component : academicComponents) {
            component.accept(visitor);
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getBatch() {
        return batch;
    }
}