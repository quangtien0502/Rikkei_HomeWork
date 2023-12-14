package Presentation;

import BusinessImp.CommonFunction;
import Entity.Book;
import Entity.Category;

import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        do {
            Category.listCategory=CategoryManagement.readDataFromFileForCategory();
            Book.listBook=BookManagement.readDataFromFileForBook();
            System.out.println("===== Library Management =====");
            System.out.println("1. Categories Management\n2. Book Management\n3. Exit");
            int choice= CommonFunction.CheckInteger("choice",scanner);
            switch (choice){
                case 1:
                    CategoryManagement.displayMenu(scanner);
                    break;
                case 2:
                    BookManagement.displayMenu(scanner);
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
