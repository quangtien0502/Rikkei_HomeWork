package Presentation;

import BusinessImp.CommonFunction;
import BusinessImp.ProductMenuLogic;
import Entity.Category;
import Entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {

    public static void displayMenu(Scanner scanner){
        boolean isExit=false;
        do {
            Product.listProduct=readDataFromFileForProduct();
            System.out.println("""
                    ===== PRODUCTS MANAGEMENT =====
                    1. Add new Products.
                    2. Update Products
                    3. Delete Product.
                    4. Display Product By Name from A-Z.
                    5. Display products by descending profit.
                    6. Search for product.
                    7. Back.\s
                    """
            );
            int choice= CommonFunction.CheckInteger("choice",scanner);
            switch (choice){
                case 1:

                    if(Category.listCategory.size()!=0){
                        int number=CommonFunction.CheckInteger("The amount of products that you want to enter",scanner);
                        for (int i = 0; i < number; i++) {
                            Product product = new Product();
                            product.inputData(scanner);
                            Product.listProduct.add(product);
                        }
                    }else {
                        System.err.println("You must enter a category first");
                    }
                    writeDataToFile(Product.listProduct);
                    break;
                case 2:
                    ProductMenuLogic.updateProduct(scanner);
                    writeDataToFile(Product.listProduct);
                    break;
                case 3:
                    ProductMenuLogic.deleteProduct(scanner);
                    writeDataToFile(Product.listProduct);
                    break;
                case 4:
                    ProductMenuLogic.displayProductByName();
                    writeDataToFile(Product.listProduct);
                    break;
                case 5:
                    ProductMenuLogic.displayProductByDescendingProfit();
                    writeDataToFile(Product.listProduct);
                    break;
                case 6:
                    ProductMenuLogic.findProductByKeyWord(scanner);
                    writeDataToFile(Product.listProduct);
                    break;
                case 7:
                    isExit=true;
                    break;
                default:
                    System.err.println("Please enter value from 1 to 7");
            }
        }while (!isExit);
    }

    public static List<Product> readDataFromFileForProduct(){
        List<Product> listProduct = null;
        File file = new File("products.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listProduct = (List<Product>) ois.readObject();
            return listProduct;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return new ArrayList<>();
    }

    public static void writeDataToFile(List<Product> listProduct){
        File file = new File("products.txt");

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listProduct);
            oos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("File is null");
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
