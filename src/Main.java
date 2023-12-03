import Entity.Categories;
import Entity.Product;

import java.util.Scanner;

public class Main {

    Categories[] arrCategories=new Categories[100];
    Product[]   arrProducts= new Product[100];
    int currentIndex=0;
    int currentIndexProduct=0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Main objMain = new Main();
        System.out.println("******************SHOP MENU*******************");
        System.out.println("""
                1. Category Management
                2. Product Management
                3. Exit
                """);

        System.out.println("Please enter your choice");
        int choice = Integer.parseInt(scanner.nextLine());

        while (true){

            switch (choice){
                case 1:
                    objMain.displayCategoryManagement();
                    break;
                case 2:
                    objMain.displayProductManagement();
                    break;
                case 3:
                    System.out.println("Exiting");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Please enter ");
            }
        }

    }

    public void displayCategoryManagement(){
        Scanner scanner=new Scanner(System.in);
        boolean isNotExitCategoryMenu=true;
        Main objMain= new Main();
        while (isNotExitCategoryMenu) {
            System.out.println("********************CATEGORIES MENU********************");
            System.out.println("1. Insert categories information\n" +
                    "2. Show all categories information\n" +
                    "3. Update categories information\n" +
                    "4. Delete categories\n" +
                    "5. Update category status\n" +
                    "6. Exit");
            System.out.println("Please enter your choice");
            int choice = Integer.parseInt(scanner.nextLine());

            isNotExitCategoryMenu=true;
            switch (choice){
                case 1:
                    System.out.println("Please enter the amount of categories that you want to enter");
                    int insertNumber=Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < insertNumber; i++) {
                        System.out.printf("Please enter Product %d information \n",i);
                        Categories categories=new Categories();
                        categories.inputData(scanner,arrCategories,currentIndex);
                        arrCategories[currentIndex]=categories;
                        currentIndex++;
                    }
                    break;
                case 2:
                    for (int i = 0; i < currentIndex; i++) {
                        arrCategories[i].displayData();
                    }
                    break;
                case 3:
                    System.out.println("Please enter the categories that you want to update");
                    int updateCategoryId=Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < currentIndex; i++) {
                        if(updateCategoryId==arrCategories[i].getCatalogId()){
                            arrCategories[i].updateData(i,scanner,arrCategories,currentIndex);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Please enter the category Id that you want to delete");
                    boolean isNotCorrectCategoryId = true;
                    int deleteCategory=-1;
                    while (isNotCorrectCategoryId){
                         deleteCategory=Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < currentIndex; i++) {
                            if(arrCategories[i].getCatalogId()==deleteCategory){
                                isNotCorrectCategoryId=false;
                                for (int j = 0; j < currentIndexProduct; i++) {
                                    if(deleteCategory==arrProducts[i].getCatalogId()){
                                        System.out.println("The category already include a product, can't delete");
                                        isNotCorrectCategoryId=true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        System.out.println("Please re enter your category Id");
                    }
                    int indexCategory= objMain.findCategoryIndexById(deleteCategory,arrCategories,currentIndex);
                    if(indexCategory==currentIndex-1){
                        arrCategories[indexCategory]=arrCategories[indexCategory+1];
                    }else {
                        for (int i = 0; i < currentIndex; i++) {
                            if(i>=indexCategory){
                                arrCategories[i]=arrCategories[i+1];
                            }
                        }
                    }
                    currentIndex--;


                    break;
                case 5:
                    System.out.println("Please enter Id of category that you want to update status");
                    int updateCategoryStatusId=Integer.parseInt(scanner.nextLine());
                    indexCategory=objMain.findCategoryIndexById(updateCategoryStatusId,arrCategories,currentIndex);
                    if(indexCategory==-1){
                        System.out.println("There are no category id like this");
                    }else {
                        arrCategories[indexCategory].updateStatus(indexCategory,scanner,arrCategories);
                    }
                    break;
                case 6:
                    isNotExitCategoryMenu=false;
                    break;
            }
        }
    }

    public int findCategoryIndexById(int id,Categories[] arrCategories,int currentIndex){
        int index=-1;
        for (int i = 0; i < currentIndex; i++) {
            if(arrCategories[i].getCatalogId()==id){
                index=i;
                break;
            }

        }
        return index;
    }

    public int findProductIndexById(String id,Product[] arrProducts,int currentIndexProduct){
        int index=-1;
        for (int i = 0; i < currentIndexProduct; i++) {
            if(arrProducts[i].getProductId().equals(id)){
                index=i;
                break;
            }
        }
        return index;
    }

    public void sortAscendingPrice(int currentIndexProduct,Product[] arr){
        int n=currentIndexProduct;
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getPrice() < arr[min_idx].getPrice())
                    min_idx = j;
            }

            // Swap the found minimum element with the first
            // element
            Product temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public void deleteProductById(String id,Main objMain,Scanner scanner){
        int index=objMain.findProductIndexById(id,arrProducts,currentIndexProduct);
        while (index==-1){

            System.out.println("Please re enter your productId");
            id=scanner.nextLine();
            index=objMain.findProductIndexById(id,arrProducts,currentIndexProduct);

        }
        for (int i = 0; i < currentIndexProduct-1; i++) {
            if(i>=index){
                arrProducts[i]=arrProducts[i+1];

            }
        }
        currentIndexProduct--;
    }
    public void displayProductManagement(){
        Scanner scanner=new Scanner(System.in);
        boolean isNotExitProductMenu=true;
        Main objMain= new Main();
        while (isNotExitProductMenu){
            System.out.println("********************PRODUCTS MENU********************");
            System.out.println("1. Insert products information\n" +
                    "2. Show all products information\n" +
                    "3. Sort all products according to price\n" +
                    "4. Update products\n" +
                    "5. Delete Product using by Id\n" +
                    "6. Find Product by Id\n" +
                    "7. Find product in price range\n"+
                    "8.Exit");
            System.out.println("Please enter your choice");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Please enter the amounts of products that you want to insert");
                    int number= Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < number; i++) {
                        Product product = new Product();
                        product.inputData(scanner,arrProducts,currentIndexProduct,currentIndex,arrCategories,product);
                    }
                    break;
                case 2:
                    for (int i = 0; i < currentIndexProduct; i++) {
                        System.out.println("Information about Product i");
                        arrProducts[i].displayData();
                    }
                    break;
                case 3:
                    objMain.sortAscendingPrice(currentIndexProduct,arrProducts);
                    break;
                case 4:
                    System.out.println("Please enter Product Id");
                    String id= scanner.nextLine();
                    int index= objMain.findProductIndexById(id,arrProducts,currentIndexProduct);
                    while (index==-1){
                        System.out.println("Please re enter your Product Id");
                        id= scanner.nextLine();
                        index= objMain.findProductIndexById(id,arrProducts,currentIndexProduct);

                    }

                    arrProducts[index].updateProduct(index,currentIndexProduct,arrProducts,currentIndex,arrCategories);
                    break;
                case 5:
                    System.out.println("Please enter your id");
                    String idToDelete= scanner.nextLine();
                    deleteProductById(idToDelete,objMain,scanner);
                    break;
                case 6:
                    System.out.println("Please enter Id and we'll show you the product information");
                    String idProductToShow= scanner.nextLine();
                    int indexProductToShow= findProductIndexById(idProductToShow,arrProducts,currentIndexProduct);
                    if(indexProductToShow==-1){
                        System.out.println("Your products don't have in category");
                    }else {
                        arrProducts[indexProductToShow].displayData();
                    }
                    break;
                case 7:
                    System.out.println("Please enter start price");
                    float startPrice= Float.parseFloat(scanner.nextLine());
                    System.out.println("Please enter end price");
                    float endPrice=Float.parseFloat(scanner.nextLine());
                    int isProductExist=0;
                    for (int i = 0; i < currentIndexProduct; i++) {
                        if(arrProducts[i].getPrice()>startPrice && arrProducts[i].getPrice()<endPrice){
                            arrProducts[i].displayData();
                            isProductExist++;
                        }
                    }
                    if(isProductExist==0){
                        System.out.println("There are no products in this range");
                    }else {
                        isProductExist=0;
                    }
                    break;
                case 8:
                    isNotExitProductMenu=false;
                    break;
                default:
                    System.err.println("Please enter value from 1-8");
            }
        }
    }
}