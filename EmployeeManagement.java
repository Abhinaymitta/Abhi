import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Employee> employees = new ArrayList<>();

    private static class Employee {
        private final int id;
        private final String name;
        private final String department;
        private final double salary;

        private Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        private void display() {
            System.out.printf("ID: %d | Name: %s | Department: %s | Salary: $%.2f%n",
                    id, name, department, salary);
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Exit");

            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please choose an option from 1 to 4.");
            }
        }
        scanner.close();
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        if (findEmployee(id) != null) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String department = readNonEmpty("Department: ");
        double salary = readSalary("Salary: ");
        employees.add(new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }

        System.out.println("\nEmployee details:");
        for (Employee employee : employees) {
            employee.display();
        }
    }

    private static void searchEmployee() {
        int id = readInt("Enter employee ID to search: ");
        Employee employee = findEmployee(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            employee.display();
        }
    }

    private static Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.id == id) {
                return employee;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readSalary(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double salary = Double.parseDouble(input);
                if (Double.isFinite(salary) && salary >= 0) {
                    return salary;
                }
            } catch (NumberFormatException ignored) {
                // Show the common validation message below.
            }
            System.out.println("Please enter a valid non-negative salary.");
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }
}
