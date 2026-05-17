// Scenario:
// The `Employee` class holds several pieces of related data (name, address, phone number, department, salary), 
// and this data is passed around in various parts of the codebase. Whenever an employee is updated, 
// all of these pieces of data are passed together. If, in the future, the company needs to store a new set of 
// related information (e.g., `ContactInfo`), the existing structure will quickly become cumbersome and hard to maintain.
//
// Task for Students:
// Refactor the `Employee` class by extracting the data clumps into appropriate classes 
// (e.g., create a `ContactInfo` class for the address and phone number).
// Apply the Extract Class refactoring technique.

public class Employee {
    private String name;
    private String address;
    private String phoneNumber;
    private String department;
    private int salary;

    public void updateEmployee(String name, String address, String phoneNumber, 
                               String department, int salary) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.salary = salary;
    }
}
