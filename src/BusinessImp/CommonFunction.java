package BusinessImp;

import Entity.Category;
import Entity.Product;

import java.util.List;
import java.util.Scanner;

public class CommonFunction {
    public static int CheckInteger(String option, Scanner scanner){
        do {
            try{
                System.out.printf("Please enter your %s \n",option);
                int number=Integer.parseInt(scanner.nextLine());
                return number;
            }catch (NumberFormatException nfx){
                System.err.println("Please enter an Integer value");
            }
        }while (true);
    }

    public static int findIndexByIdCategory(int id){
        int index=-1;
        for (Category category:Category.listCategory
             ) {
            if(category.getId()==id){
                index= Category.listCategory.indexOf(category);
            }
        }
        return index;
    }

    public static int findIndexByIdProduct(String id){
        int index=-1;
        for (Product product:Product.listProduct
        ) {
            if(product.getId().equals(id)){
                index= Product.listProduct.indexOf(product);
            }
        }
        return index;
    }

    public static void displayCategory(List<Category> categories){
        Category category=new Category();
        category.displayData(categories);
    }

    public static void displayProduct(List<Product> products){
        Product product=new Product();
        product.displayData(products);
    }
}
