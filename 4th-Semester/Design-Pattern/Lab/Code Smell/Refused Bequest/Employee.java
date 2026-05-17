// Scenario:
// The `Employee` class is subclassed by `Manager` and `Developer`, but the `Manager` class doesn't need all 
// the functionalities provided by the `Employee` class. The `Manager` is forced to inherit functionality 
// it doesn’t use, such as handling salary adjustments. This increases the complexity of the system, 
// and potentially introduces bugs, as changes to `Employee` might affect `Manager` unexpectedly.
//
// Task for Students:
// Refactor the code to ensure that `Manager` and `Developer` do not inherit unused methods. 
// Move any unnecessary functionality into separate classes that can be used by the specific subclasses 
// using composition, instead of inheritance. 
// Apply the **Replace Inheritance with Delegation** or **Extract Class** refactoring techniques.

public class Employee {
    private String name;
    private int salary;

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void giveRaise(int raiseAmount) {
        this.salary += raiseAmount;
    }
}

public class Manager extends Employee {
    private int bonus;

    // Manager doesn't need the functionality related to salary adjustment
}

public class Developer extends Employee {
    private String programmingLanguage;

    // Developer doesn't need the functionality related to salary adjustment
}
