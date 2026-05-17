public class Employee {
    private String name;
    private ContactInfo contactInfo;
    private EmployeeDetails employeeDetails;
    public Employee(String name, ContactInfo contactInfo, EmployeeDetails employeeDetails) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.employeeDetails = employeeDetails;
    }
    public void updateEmployee(String name, ContactInfo contactInfo, EmployeeDetails employeeDetails) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.employeeDetails = employeeDetails;
    }
    public String getName() {
        return this.name;
    }
    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }
    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee: " + name +  contactInfo + employeeDetails;
    }
}
