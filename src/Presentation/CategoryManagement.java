package Presentation;

import BusinessImp.CategoryMenuLogic;
import BusinessImp.CommonFunction;
import Entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManagement {
    public static void displayMenu(Scanner scanner) {
        boolean isExit=false;
        do {

            Category.listCategory = readDataFromFileForCategory();
            System.out.println("""
                            ===== CATEGORY MANAGEMENT =====
                            1. Add new categories.
                            2. Display Categories from A-Z
                            3. Show the numbers of each book in each category.
                            4. Update Category.
                            5. Delete Category.
                            6. Back.
                            8.Display Category.\s
                            """
            );

            int choice= CommonFunction.CheckInteger("choice",scanner);
            switch (choice){
                case 1:
                    int number=CommonFunction.CheckInteger("The amount of categories that you want to insert",scanner);
                    for (int i = 0; i < number; i++) {
                        Category category=new Category();
                        category.inputData(scanner);
                        Category.listCategory.add(category);
                    }
                    writeDataToFile(Category.listCategory);
                    break;
                case 2:
                    CategoryMenuLogic.displayCategoryByName();
                    break;
                case 3:
                    CategoryMenuLogic.showNumberOfBooksEachCategory();
                    break;
                case 4:
                    CategoryMenuLogic.updateCategory(scanner);
                    writeDataToFile(Category.listCategory);
                    break;
                case 5:
                    CategoryMenuLogic.deleteCategory(scanner);
                    writeDataToFile(Category.listCategory);
                    break;
                case 6:
                    isExit=true;
                    break;
                case 8:
                    CommonFunction.displayCategory(Category.listCategory);
                    break;
                default:
                    System.err.println("Please enter value from 1-6");
            }
        }while (!isExit);
    }

    public static List<Category> readDataFromFileForCategory() {
        List<Category> listCategory = null;
        File file = new File("categories.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listCategory = (List<Category>) ois.readObject();
            return listCategory;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return new ArrayList<>();
    }

    public static void writeDataToFile(List<Category> listCategory) {
        File file = new File("categories.txt");

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listCategory);
            oos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("File is null");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
