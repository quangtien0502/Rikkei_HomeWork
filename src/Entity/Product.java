package Entity;

import Business.IEntity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product implements IEntity {
    private String productId;
    private String productName;
    private String manufacture;
    private boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, String manufacture, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.manufacture = manufacture;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }


    public String inputId(Scanner scanner){


        String regex = "^[A-Z][a-z]{3}$";


        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.println("Please enter productId");
            String Id=scanner.nextLine();
            Matcher matcher = pattern.matcher(Id);
            if(matcher.matches()){
                return Id;
            }
        }while (true);


    }
    public String inputProductName(Scanner scanner){
        do {
            System.out.println("Please enter your product Name");
            String name= scanner.nextLine();
            if(name.length()>0){
                return name;
            }
        }while (true);


    }

    public String inputManufacture(Scanner scanner){
        System.out.println("Please enter manufacture");
        return scanner.nextLine();
    }

    public boolean inputStatus(Scanner scanner){
        do {
            System.out.println("Please enter Product Status");
            String status=scanner.nextLine();
            if(status.equals("true")||status.equals("false")){
                return Boolean.parseBoolean(status);
            }
        }while (true);
    }

    @Override
    public void inputData(Scanner scanner) {
        this.productId=inputId(scanner);
        this.productName=inputProductName(scanner);
        this.manufacture=inputManufacture(scanner);
        this.productStatus=inputStatus(scanner);
    }

    @Override
    public void displayData() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", productStatus=" + productStatus +
                '}';
    }
}
