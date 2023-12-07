package ra.bussinessImp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeMenu {
    public static void findEmployeeByName(Scanner scanner){
        System.out.println("Please enter the Name of Employee that you want to find");
        String name=scanner.nextLine();
        int count = 0;
        for (int i = 0; i < Employee.currentIndex; i++) {
            if(Employee.arrEmployee[i].getName().equals(name)){
                Employee.arrEmployee[i].displayData();
                count++;
            }
        }
        if (count==0){
            System.err.println("There are no employee like this");
        }
    }

    public static void upDateEmployeeById(Scanner scanner){
        System.out.println("Please enter the Id of Employee that you want to Update");
        String Id=scanner.nextLine();
        int index=-1;
        for (int i = 0; i < Employee.currentIndex; i++) {
            if(Employee.arrEmployee[i].getId().equals(Id)){
                index=i;
                break;
            }
        }

        if(index==-1){
            System.out.println("There are no employee with id like this");
        }else {
            boolean isInUpdateMenu=true;
            while (isInUpdateMenu){
                System.out.println("********************UPDATE MENU*********************");
                System.out.println("""
                1. Update Employee Name
                2. Update Employee Year of Birth
                3. Update Employee Rate
                4. Update Employee Commission
                5. Update Employee Status
                6.Exit
                """);

                System.out.println("Please enter your choice");
                int choice=Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        Employee.arrEmployee[index].setName(Employee.arrEmployee[index].inputName(scanner));
                        break;
                    case 2:
                        Employee.arrEmployee[index].setYear(Employee.arrEmployee[index].inputYearOfBirth(scanner));
                        break;
                    case 3:
                        Employee.arrEmployee[index].setRate(Employee.arrEmployee[index].inputRate(scanner));
                        Employee.arrEmployee[index].setSalary(Employee.arrEmployee[index].calSalary());
                        break;
                    case 4:
                        Employee.arrEmployee[index].setCommission(Employee.arrEmployee[index].inputCommission(scanner));
                        Employee.arrEmployee[index].setSalary(Employee.arrEmployee[index].calSalary());
                        break;
                    case 5:
                        Employee.arrEmployee[index].setStatus(!Employee.arrEmployee[index].isStatus());
                        break;
                    case 6:
                        isInUpdateMenu=false;
                        break;
                    default:
                        System.err.println("Please enter value from 1-6");
                }
            }
        }
    }

    public static void deleteEmployeeById(Scanner scanner){
        System.out.println("Please enter the Id of Employee that you want to delete");
        String Id= scanner.nextLine();
        int index = -1;
        for (int i = 0; i < Employee.currentIndex; i++) {
            if(Employee.arrEmployee[i].getId().equals(Id)){
                index=i;
                break;
            }
        }
        if(index==-1){
            System.out.println("There are no employee like this");
        }else {
            for (int i = index; i < Employee.currentIndex; i++) {
                Employee.arrEmployee[i]=Employee.arrEmployee[i+1];
            }
            Employee.currentIndex--;
        }
    }

    public static void sortAscendingSalary(){
        Employee[] arrEmployeeSort=new Employee[Employee.currentIndex];
        for (int i = 0; i < Employee.currentIndex; i++) {
            arrEmployeeSort[i]=Employee.arrEmployee[i];
        }
        Collections.sort(Arrays.asList(arrEmployeeSort));
        for (int i = 0; i < Employee.currentIndex; i++) {
            Employee.arrEmployee[i]=arrEmployeeSort[i];
        }
    }

    public static void sortDescendingName(){
        Employee[] arrEmployeeSort=new Employee[Employee.currentIndex];
        for (int i = 0; i < Employee.currentIndex; i++) {
            arrEmployeeSort[i]=Employee.arrEmployee[i];
        }
        Collections.sort(Arrays.asList(arrEmployeeSort), new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {

                return -o1.getName().compareTo(o2.getName());
            }
        });
        for (int i = 0; i < Employee.currentIndex; i++) {
            Employee.arrEmployee[i]=arrEmployeeSort[i];
        }
    }

    public static void sortAscendingYearOfBirth(){
        Employee[] arrEmployeeSort=new Employee[Employee.currentIndex];
        for (int i = 0; i < Employee.currentIndex; i++) {
            arrEmployeeSort[i]=Employee.arrEmployee[i];
        }
        Collections.sort(Arrays.asList(arrEmployeeSort), new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getYear()-o2.getYear();
            }
        });
        for (int i = 0; i < Employee.currentIndex; i++) {
            Employee.arrEmployee[i]=arrEmployeeSort[i];
        }
    }
}
