package BusinessImp;

import Entity.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductMenuLogic {
    public static void updateProduct(Scanner scanner) {
        boolean isExit = false;
        System.out.println("Please enter the Id of the product");
        String id = scanner.nextLine();
        int index = CommonFunction.findIndexByIdProduct(id);
        if (index != -1) {
            do {
                System.out.println("""
                        =====UPDATE PRODUCT MENU =====
                        1. Update Product Name.
                        2. Update Product Description
                        3. Update Product Status\s
                        4. Update Product Import Price\s
                        5. Update Product Export Price\s
                        6. Update Product Category Id\s
                        7. Back.
                        """
                );
                int choice = CommonFunction.CheckInteger("choice", scanner);
                switch (choice) {
                    case 1:
                        Product.listProduct.get(index).setName(Product.inputName(scanner));
                        break;
                    case 2:
                        Product.listProduct.get(index).setDescription(Product.inputDescription(scanner));
                        break;
                    case 3:
                        Product.listProduct.get(index).setStatus(!Product.listProduct.get(index).isStatus());
                        break;
                    case 4:
                        Product.listProduct.get(index).setImportPrice(Product.inputImportPrice(scanner,index));
                        break;
                    case 5:
                        Product.listProduct.get(index).setExportPrice(Product.listProduct.get(index).inputExportPrice(scanner));
                        break;
                    case 6:
                        Product.listProduct.get(index).setCategoryId(Product.inputCategoryId(scanner));
                        break;
                    case 7:
                        isExit = true;
                        break;
                }
            } while (!isExit);
        } else {
            System.err.println("The product doesn't exist in the system");
        }
    }

    public static void deleteProduct(Scanner scanner) {
        int index;
        System.out.println("Please enter product Id");
        String id = scanner.nextLine();
        index = CommonFunction.findIndexByIdProduct(id);
        if (index != -1) {
            Product.listProduct.remove(index);
        } else {
            System.err.println("Id like that doesn't exist");
        }

    }

    public static void displayProductByName() {
        List<Product> listProductResult;
        listProductResult = Product.listProduct.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
        CommonFunction.displayProduct(listProductResult);
    }

    public static void displayProductByDescendingProfit() {
        List<Product> listProductResult;
        listProductResult = Product.listProduct.stream().sorted(Comparator.comparing(Product::getProfit).reversed()).collect(Collectors.toList());
        CommonFunction.displayProduct(listProductResult);
    }

    public static void findProductByKeyWord(Scanner scanner) {
        List<Product> listProductResult = new ArrayList<>();
        System.out.println("Please enter the keyword that you want to search for product");
        String keyword = scanner.nextLine();
        for (Product product :
                Product.listProduct) {
            if (product.getName().contains(keyword) || Double.toString(product.getImportPrice()).contains(keyword) || Double.toString(product.getExportPrice()).contains(keyword)) {
                listProductResult.add(product);
            }
        }
        CommonFunction.displayProduct(listProductResult);

    }

}

