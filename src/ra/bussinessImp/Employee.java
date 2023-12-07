package ra.bussinessImp;

import ra.bussiness.IEmployee;

import java.util.Scanner;

public class Employee implements IEmployee,Comparable<Employee> {

    public static Employee[] arrEmployee=new Employee[100];
    public static int currentIndex=0;

    private String Id;
    private String Name;
    private int Year;
    private float Rate;
    private float Commission;
    private float Salary;
    private boolean Status;

    public Employee() {
    }

    public Employee(String id, String name, int year, float rate, float commission, float salary, boolean status) {
        Id = id;
        Name = name;
        Year = year;
        Rate = rate;
        Commission = commission;
        Salary = salary;
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public float getCommission() {
        return Commission;
    }

    public void setCommission(float commission) {
        Commission = commission;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }


    public String inputId(Scanner scanner){
        System.out.println("Please enter your Id");
        do {
            boolean isExist=true;
            String id=scanner.nextLine();
            if(currentIndex<1){
                return id;
            }else {
                for (int i = 0; i < currentIndex; i++) {
                    if(Employee.arrEmployee[i].getId().equals(id)){

                        break;
                    }
                    isExist=false;
                }
                if(isExist){
                    System.err.println("The Id already exist in the system");
                }else {
                    return id;
                }
            }

        }while (true);

    }

    public String inputName(Scanner scanner){
        System.out.println("Please enter Employee Name");
        return scanner.nextLine();


    }

    public int inputYearOfBirth(Scanner scanner){
        System.out.println("Please enter Year of Birth of Employee");
        return Integer.parseInt(scanner.nextLine());

    }

    public float inputRate(Scanner scanner){
        System.out.println("Please enter Employee's Rate");
        return Float.parseFloat(scanner.nextLine());

    }

    public float inputCommission(Scanner scanner){
        System.out.println("Please enter employee Commission");
        return Float.parseFloat(scanner.nextLine());
    }


    public boolean inputStatus(Scanner scanner){
        System.out.println("Please enter Employee Status");
        do {
            boolean isCorrect=false;
            String status=scanner.nextLine();
            if(status.equals("true")||status.equals("false")){
                isCorrect=true;
            }else {
                System.err.println("Please enter again Employee Status because it only receive true or false value");
            }
            if(isCorrect){
                return Boolean.parseBoolean(status);
            }
        }while (true);
    }


    @Override
    public void inputData(Scanner scanner) {
        this.Id=inputId(scanner);
        this.Name=inputName(scanner);
        this.Year=inputYearOfBirth(scanner);
        this.Rate=inputRate(scanner);
        this.Commission=inputCommission(scanner);
        this.Status=inputStatus(scanner);

    }

    @Override
    public void displayData() {
        String information = toString();
        System.out.println(information);
    }

    public float calSalary(){
        return BASIC_SALARY*this.Rate+this.Commission;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Year=" + Year +
                ", Rate=" + Rate +
                ", Commission=" + Commission +
                ", Salary=" + Salary +
                ", Status=" + Status +
                '}';
    }


    @Override
    public int compareTo(Employee employee) {
        if((this.Salary-employee.getSalary()>0)){
            return 1;
        } else if ((this.Salary-employee.getSalary()==0)) {
            return 0;
        }
        else {
            return -1;
        }

    }



}
