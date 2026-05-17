public class Main {
    public static void main(String[] args) {
        EmployeeDetails employeeDetails = new EmployeeDetails("IIT", 100);
        ContactInfo contactInfo = new ContactInfo("Doyelchattor", "0123456789");
        Employee employee = new Employee("Alex", contactInfo, employeeDetails);
        System.out.println(employee);
    }
}
