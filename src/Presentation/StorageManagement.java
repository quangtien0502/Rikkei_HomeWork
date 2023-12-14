package Presentation;

import BusinessImp.CommonFunction;
import Entity.Category;
import Entity.Product;

import java.util.Scanner;

public class StorageManagement {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        do {
            Category.listCategory=CategoryManagement.readDataFromFileForCategory();
            Product.listProduct=ProductManagement.readDataFromFileForProduct();
            System.out.println("===== Store Management =====");
            System.out.println("1. Categories Management\n2. Product Management\n3. Exit");
            int choice= CommonFunction.CheckInteger("choice",scanner);
            switch (choice){
                case 1:
                    CategoryManagement.displayMenu(scanner);
                    break;
                case 2:
                    ProductManagement.displayMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Please enter value from 1-3");
            }
        }while (true);
    }
}
