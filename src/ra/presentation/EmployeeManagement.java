package ra.presentation;

import ra.bussinessImp.Employee;
import ra.bussinessImp.EmployeeMenu;

import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("********************MENU*********************");
            System.out.println("""
                1. Insert information for n employee
                2. Display employee information
                3. Calculate Salary for employee
                4. Find employee by employee Name
                5. Update Employee
                6. Delete Employee by Id
                7. Sort Employee by Ascending Salary (Comparable)
                8. Sort Employee by Descending Name (Comparator)
                9. Sort Employee by Ascending Year of Birth (Comparator)
                10. Exit""");

            System.out.println("Please enter your choice");
            int choice=Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Please enter the amount of employee that you want to insert information");
                    int number=Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < number; i++) {

                        System.out.printf("Please insert information of employee %d \n",i);
                        Employee.arrEmployee[Employee.currentIndex]=new Employee();
                        Employee.arrEmployee[Employee.currentIndex].inputData(scanner);
                        Employee.currentIndex++;
                    }
                    break;
                case 2:
                    for (int i = 0; i < Employee.currentIndex; i++) {
                        Employee.arrEmployee[i].displayData();
                    }
                    break;
                case 3:
                    for (int i = 0; i < Employee.currentIndex; i++) {
                        Employee.arrEmployee[i].setSalary(Employee.arrEmployee[i].calSalary());
                    }
                    break;
                case 4:
                    EmployeeMenu.findEmployeeByName(scanner);
                    break;
                case 5:
                    EmployeeMenu.upDateEmployeeById(scanner);
                    break;
                case 6:
                    EmployeeMenu.deleteEmployeeById(scanner);
                    break;
                case 7:
                    EmployeeMenu.sortAscendingSalary();
                    break;
                case 8:
                    EmployeeMenu.sortDescendingName();
                    break;
                case 9:
                    EmployeeMenu.sortAscendingYearOfBirth();
                    break;
                case 10:
                    System.exit(0);
                    break;
            }
        }
    }
}
