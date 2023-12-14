package Entity;

import Business.IProduct;
import BusinessImp.CommonFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product implements IProduct<Product>, Serializable {

    public static List<Product> listProduct=new ArrayList<>();

    private String id;
    private String name;
    private double importPrice;
    private double exportPrice;

    private double profit;
    private String description;
    private boolean Status;
    private int categoryId;

    public Product() {
    }

    public Product(String id, String name, double importPrice, double exportPrice,double profit, String description, boolean status, int categoryId) {
        this.id = id;
        this.name = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.description = description;
        this.Status = status;
        this.categoryId = categoryId;
        this.profit=profit;
    }

    public static List<Product> getListProduct() {
        return listProduct;
    }

    public static void setListProduct(List<Product> listProduct) {
        Product.listProduct = listProduct;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String inputId(Scanner scanner){
        String regex = "^P.{3}$";
        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.println("Please enter your Product ID");
            String id=scanner.nextLine();
            boolean isDuplicate=false;
            Matcher matcher = pattern.matcher(id);
            if(id.length()==4){
                if(matcher.matches()){
                    for (Product product:listProduct
                    ) {
                        if(product.getId().equals(id)){
                            isDuplicate=true;
                            break;
                        }
                        isDuplicate=false;
                    }
                    if(isDuplicate){
                        System.err.println("The id already exist in the system");
                    }else {
                        return id;
                    }
                }else {
                    System.err.println("The Id must have form Pxxx (Where x is character)");
                }
            }else {
                System.err.println("The id must contains 4 letter");
            }
        }while (true);
    }

    public static String inputName(Scanner scanner){
        do {
            System.out.println("Please enter product Name");
            String name=scanner.nextLine();
            boolean isDuplicate=false;
            if(!name.trim().isEmpty()){
                if(name.length()>6 && name.length()<30){
                    for (Product product :
                            listProduct) {
                        if (product.getName().trim().equals(name.trim())){
                            isDuplicate=true;
                            break;
                        }
                        isDuplicate=false;
                    }
                    if(isDuplicate){
                        System.err.println("The name already exist in the system");
                    }else {
                        return name;
                    }
                }else {
                    System.err.println("Please enter name have length from 6 to 30");
                }
            }else {
                System.err.println("You must enter the name");
            }
        }while (true);
    }

    public static double inputImportPrice(Scanner scanner,int index){
        do {
            System.out.println("Please enter import Price");
            try{
                double importPrice=Float.parseFloat(scanner.nextLine());
                if(importPrice>0){
                    if(index==-1){
                        return importPrice;
                    }else {
                        if(Product.listProduct.get(index).getExportPrice()-(MIN_INTEREST_RATE+1)*importPrice>=0){
                            Product.listProduct.get(index).calProfit();
                            return importPrice;
                        }else {
                            System.err.println("The import price must smaller than export price divided for 1.2");
                        }
                    }
                }else {
                    System.err.println("Please enter a value that greater than 0");
                }
            }catch (NumberFormatException nfx){
                System.err.println("Please enter a number for import Price");
            }
        }while (true);
    }

    public double inputExportPrice(Scanner scanner){
        do {
            System.out.println("Please enter export Price");
            try{
                double exportPrice=Float.parseFloat(scanner.nextLine());
                if(exportPrice-this.importPrice*(MIN_INTEREST_RATE+1)>0){
                    this.calProfit();
                    return exportPrice;
                }else {
                    System.err.println("Please enter a value that greater than import Price at least "+MIN_INTEREST_RATE);
                }
            }catch (NumberFormatException nfx){
                System.err.println("Please enter a number for export Price");
            }
        }while (true);
    }



    public static String inputDescription(Scanner scanner) {
        do {
            System.out.println("Please enter Product description");
            String description = scanner.nextLine();
            if (!description.trim().isEmpty()) {
                return description;
            } else {
                System.err.println("You must enter the description");
            }
        } while (true);
    }

    public static boolean inputStatus(Scanner scanner) {
        do {
            System.out.println("Please enter Product Status");
            String status = scanner.nextLine();
            if (!status.trim().isEmpty()) {
                if (status.equals("true") || status.equals("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.err.println("Status only receive value true or false");
                }
            } else {
                System.err.println("You must enter the status");
            }
        } while (true);
    }

    public static int inputCategoryId(Scanner scanner){
        Category categoryTrash=new Category();
        categoryTrash.displayData(Category.listCategory);
        do {
            int CategoryId= CommonFunction.CheckInteger("CategoryId",scanner);
            int index=CommonFunction.findIndexByIdCategory(CategoryId);
            if(index!=-1){
                return CategoryId;
            }else {
                System.err.println("The Category doesn't exist,please enter again");
            }
        }while (true);
    }

    @Override
    public void inputData(Scanner scanner) {
        this.id=inputId(scanner);
        this.name=inputName(scanner);
        this.importPrice=inputImportPrice(scanner,-1);
        this.exportPrice=inputExportPrice(scanner);
        this.description=inputDescription(scanner);
        this.Status=inputStatus(scanner);
        this.categoryId=inputCategoryId(scanner);
        calProfit();
    }



    @Override
    public void displayData(List<Product> listItem) {
        printTableHeaderWithBoundaryAndAdditionalFields();
        for (Product product : listItem) {
            System.out.printf("| %-5s | %-20s | %-12.2f | %-12.2f | %-10.2f | %-30s | %-10s | %-10s |%n",
                    product.getId(), product.getName(), product.getImportPrice(), product.getExportPrice(),
                    product.getProfit(), product.getDescription(), product.isStatus() ? "Active" : "Inactive", product.getCategoryId());
        }
        printTableFooterWithBoundary();
    }

    private static void printTableFooterWithBoundary() {
        printHorizontalLineWithBoundary();
    }


    private static void printTableHeaderWithBoundaryAndAdditionalFields() {
        printHorizontalLineWithBoundary();
        System.out.printf("| %-5s | %-20s | %-12s | %-12s | %-10s | %-30s | %-10s | %-10s |%n",
                "ID", "Name", "Import Price", "Export Price", "Profit", "Description", "Status", "Category ID");
        printHorizontalLineWithBoundary();
    }

    private static void printHorizontalLineWithBoundary() {
        System.out.println("+-------+----------------------+--------------+--------------+------------+--------------------------------+------------+------------+");
    }



    @Override
    public void calProfit() {
        this.profit=this.exportPrice-this.importPrice;
    }
}
