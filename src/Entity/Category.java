package Entity;

import Business.IEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity<Category>, Serializable {

    public static List<Category> listCategory=new ArrayList<>();
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int autoGenerateId(Scanner scanner) {
        int max = 0;
        if (listCategory.size() != 0) {
            for (Category category :
                    listCategory) {
                if (category.getId() > max) {
                    max = category.getId();
                }

            }
        }
        return max + 1;


    }

    public static String inputName(Scanner scanner) {
        do {
            boolean isDuplicateName = false;
            System.out.println("Please enter your Category Name");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                if (name.length() > 6 && name.length() < 30) {
                    for (Category category :
                            listCategory) {
                        if (category.getName().trim().equals(name.trim())) {
                            isDuplicateName = true;
                            break;
                        }
                        isDuplicateName = false;
                    }
                    if (isDuplicateName) {
                        System.err.println("The name already exist in the system");

                    } else {
                        return name;
                    }

                } else {
                    System.err.println("The length of Category name must smaller than 30 and bigger than 6");
                }
            } else {
                System.err.println("You must enter the name");
            }
        } while (true);
    }

    public static boolean inputStatus(Scanner scanner) {
        do {
            System.out.println("Please enter Category Status");
            String status = scanner.nextLine();
            if (!status.trim().isEmpty()) {
                if (status.trim().toLowerCase().equals("true") || status.trim().toLowerCase().equals("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.err.println("Status only receive value true or false");
                }
            } else {
                System.err.println("You must enter the status");
            }
        } while (true);
    }
    @Override
    public void inputData(Scanner scanner) {
        this.id=autoGenerateId(scanner);
        this.name=inputName(scanner);
        this.status=inputStatus(scanner);
    }

    private static void printTableRowWithBoundaryAndId(int id, String name,String status) {
        System.out.printf("| %-5s | %-20s | %-15s |%n", id, name, status);
    }

    private static void printTableHeaderWithBoundaryAndId() {
        printHorizontalLineWithBoundary();
        System.out.printf("| %-5s | %-20s | %-15s |%n", "Id", "Name","Status");
        printHorizontalLineWithBoundary();
    }



    @Override
    public void displayData(List<Category> ListItem) {
        printTableHeaderWithBoundaryAndId();
        for (Category item : ListItem) {
            printTableRowWithBoundaryAndId(item.getId(), item.getName(),item.isStatus() ? "Available" : "Unavailable");
        }
        printTableFooterWithBoundary();
    }

    private static void printTableFooterWithBoundary() {
        printHorizontalLineWithBoundary();
    }

    private static void printHorizontalLineWithBoundary() {
        System.out.println("+-------+----------------------+-----------------+");
    }
}
