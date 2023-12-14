package BusinessImp;

import Entity.Category;
import Entity.Product;

import java.util.*;
import java.util.stream.Collectors;

public class CategoryMenuLogic {
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
                        2. Update Category Description
                        3. Update Category Status\s
                        4. Back.
                        """
                );
                int choice = CommonFunction.CheckInteger("choice", scanner);
                switch (choice) {
                    case 1:
                        Category.listCategory.get(index).setName(Category.inputName(scanner));
                        break;
                    case 2:
                        Category.listCategory.get(index).setDescription(Category.inputDescription(scanner));
                        break;
                    case 3:
                        Category.listCategory.get(index).setStatus(!Category.listCategory.get(index).isStatus());
                        break;
                    case 4:
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
            for (Product product :
                    Product.listProduct) {
                if (product.getCategoryId() == id) {
                    isExist = true;
                    break;
                }
                isExist = false;
            }
            if (!isExist) {
                Category.listCategory.remove(index);
                System.out.println("Delete Success");
            } else {
                System.err.println("The Category already include product, can't delete");
            }
        } else {
            System.err.println("Id like that doesn't exist");
        }

    }

    public static void findCategoryByName(Scanner scanner) {
        List<Category> listCategoryResult = new ArrayList<>();
        System.out.println("Please enter The Category Name that you want to find");
        String name = scanner.nextLine();
        for (Category category :
                Category.listCategory) {
            if (category.getName().equals(name)) {
                listCategoryResult.add(category);
            }
        }
        CommonFunction.displayCategory(listCategoryResult);
    }

    public static String findCategoryNameById(int id) {
        String value = "Unknown";
        int index = CommonFunction.findIndexByIdCategory(id);
        if (index != -1) {
            value = Category.listCategory.get(index).getName();
        }
        return value;
    }

    public static void listProductNumberOfAllCategories() {
        Map<String, List<Product>> productsByCategory = Product.listProduct.stream().collect(Collectors.groupingBy(productGroup -> CategoryMenuLogic.findCategoryNameById(productGroup.getCategoryId())));
        productsByCategory.forEach((category, products) -> {
            System.out.println("Category: " + category);
            products.forEach(product -> {
                System.out.println("Product: " + product.getName());
            });
            System.out.println("---------------------------------------------");
        });
    }
}
