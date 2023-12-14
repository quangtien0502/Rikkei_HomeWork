package BusinessImp;

import Entity.Book;
import Entity.Category;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CategoryMenuLogic {

    public static void displayCategoryByName() {
        List<Category> listCategoryResult;
        listCategoryResult = Category.listCategory.stream().sorted(Comparator.comparing(Category::getName)).collect(Collectors.toList());
        CommonFunction.displayCategory(listCategoryResult);
    }

    public static void updateCategory(Scanner scanner) {
        int index;
        CommonFunction.displayCategory(Category.listCategory);
        int id = CommonFunction.CheckInteger("Category Id", scanner);
        index = CommonFunction.findIndexByIdCategory(id);
        boolean isExit = false;
        if (index != -1) {
            do {
                System.out.println("""
                        =====UPDATE CATEGORY MENU =====
                        1. Update Category Name.
                        2. Update Category Status\s
                        3. Back.
                        """
                );
                int choice = CommonFunction.CheckInteger("choice", scanner);
                switch (choice) {
                    case 1:
                        Category.listCategory.get(index).setName(Category.inputName(scanner));
                        break;
                    case 2:
                        Category.listCategory.get(index).setStatus(!Category.listCategory.get(index).isStatus());
                        break;
                    case 3:
                        isExit = true;
                        break;
                    default:
                        System.err.println("Please enter value from 1-3");
                }
            } while (!isExit);
        } else {
            System.err.println("Id like that doesn't exist");
        }

    }

    public static void deleteCategory(Scanner scanner) {
        int index;
        CommonFunction.displayCategory(Category.listCategory);
        int id = CommonFunction.CheckInteger("Category Id", scanner);
        index = CommonFunction.findIndexByIdCategory(id);
        boolean isExist = true;
        if (index != -1) {
            for (Book book :
                    Book.listBook) {
                if (book.getCategoryId() == id) {
                    isExist = true;
                    break;
                }
                isExist = false;
            }
            if (!isExist) {
                Category.listCategory.remove(index);
                System.out.println("Delete Success");
            } else {
                System.err.println("The Category already include Book, can't delete");
            }
        } else {
            System.err.println("Id like that doesn't exist");
        }

    }

    public static String findCategoryNameById(int id) {
        String value = "Unknown";
        int index = CommonFunction.findIndexByIdCategory(id);
        if (index != -1) {
            value = Category.listCategory.get(index).getName();
        }
        return value;
    }

    public static void showNumberOfBooksEachCategory() {
        for (Category category :
                Category.listCategory) {
            int bookCount = 0;
            for (Book book :
                    Book.listBook) {
                if (book.getCategoryId() == category.getId()) {
                    bookCount++;
                }
            }
            System.out.printf("| %-15s | %-5d |%n",category.getName(),bookCount);
            System.out.println("୨୧┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈୨୧");

        }
    }
}
