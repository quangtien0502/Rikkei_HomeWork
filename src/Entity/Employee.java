package Entity;

import Business.IEntity;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee implements IEntity {

    public static List<Employee> listEmployee= new ArrayList<>();

    private int empId;
    private String empName;
    private int birthYear;
    private String phoneNumber;
    private String email;
    private int empStatus;

    public Employee() {
    }

    public int getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }


    public int autoGenerateId(Scanner scanner){
        int max = 1;

        for (Employee employee:listEmployee
             ) {
            if(employee.getEmpId()>max){
                max=employee.getEmpId();
            }
        }
        return max+1;
    }

    public String inputEmpName(Scanner scanner){
        String name="";

        do {
            System.out.println("Please enter your employee name");
            name=scanner.nextLine();
            if(name.length()>10&&name.length()<50){
                return name;
            }
        }while (true);

    }

    public int inputBirthYear(Scanner scanner){
        do {
            System.out.println("Please enter your BirthYear");
            try{
                int year=Integer.parseInt(scanner.nextLine());
                int currentYear= Year.now().getValue();
                if(year<currentYear){
                    return year;
                }
            }catch (NumberFormatException nfx){
                System.err.println("The input should be a number");
            }
        }while (true);

    }
    public String inputPhoneNumber(Scanner scanner){
        String regex = "^(0[2-9]|1[0-9])\\d{8}$";
        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.println("Please enter phone Number");
            String phone= scanner.nextLine();
            Matcher matcher = pattern.matcher(phone);
            if(matcher.matches()){
                return phone;
            }
        }while (true);

    }
    public String inputEmail(Scanner scanner){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.println("Please enter your email");
            String email= scanner.nextLine();
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){
                return email;
            }
        }while (true);
    }
    public int inputEmpStatus(Scanner scanner){
        do {
            System.out.println("Please enter your EmpStatus");
            try{
                int status=Integer.parseInt(scanner.nextLine());
                if(status==1||status==2||status==0){
                    return status;
                }else {
                    System.err.println("Only receive value 0,1,2");
                }
            }catch (NumberFormatException nfx){
                System.err.println("Only receive value, String is invalid");
            }


        }while (true);
    }

    @Override
    public void inputData(Scanner scanner) {
        this.empId=autoGenerateId(scanner);
        this.empName=inputEmpName(scanner);
        this.birthYear=inputBirthYear(scanner);
        this.phoneNumber=inputPhoneNumber(scanner);
        this.email=inputEmail(scanner);
        this.empStatus=inputEmpStatus(scanner);
    }

    @Override
    public void displayData() {
        System.out.printf("{EmployeeId: %d Name: %s birthYear: %d phoneNumber: %s email: %s empStatus: %s \n}",this.empId,this.empName,this.birthYear,this.phoneNumber,this.email,this.empStatus==0?"Working":this.empStatus==1?"Stop worked":"On a vacation");

    }


}


