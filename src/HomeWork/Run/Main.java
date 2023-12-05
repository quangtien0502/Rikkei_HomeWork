package HomeWork.Run;

import HomeWork.Entity.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Product[] arrProducts=new Product[100];
        int currentIndex=0;
        Scanner scanner = new Scanner(System.in);
        Main objMain= new Main();
        while (true){
            System.out.println("***********************MENU**************************");
            System.out.println("""
                    1. Insert information of n products(n is received from keyboard)
                    2. Display all products information
                    3. Calculate products profit
                    4. Sort all products with descending profit
                    5. List all products with export price
                    6. Find all products with product Name
                    7. Enter quantity for products
                    8. Sell products
                    9. Update product status
                    10. Exit""");
            System.out.println("Please enter your choice");
            int choice= Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Please enter the amount of products that you want to insert");
                    int numbers= Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numbers; i++) {
                        currentIndex++;
                        Product product= new Product();
                        product.inputData(currentIndex,arrProducts);
                        arrProducts[currentIndex-1]=product;
                    }
                    break;
                case 2:
                    for (int i = 0; i < currentIndex; i++) {
                        arrProducts[i].displayData();
                    }
                    break;
                case 3:
                    for (int i = 0; i < currentIndex; i++) {
                        arrProducts[i].calculateInterest();
                    }
                    break;
                case 4:
                    objMain.sortDescendingInterest(currentIndex,arrProducts);
                    break;
                case 5:
                    System.out.println("Please enter the price that you want to find the book");
                    float buyPrice= Float.parseFloat(scanner.nextLine());
                    for (int i = 0; i < currentIndex; i++) {
                        if(arrProducts[i].getExportPrice()==buyPrice){
                            arrProducts[i].displayData();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Please enter the product Name that you want to find");
                    String productNameEnter= scanner.nextLine();
                    for (int i = 0; i < currentIndex; i++) {
                        if(arrProducts[i].getProductName().equals(productNameEnter)){
                            arrProducts[i].displayData();
                        }
                    }
                    break;
                case 7:

                    for (int i = 0; i < currentIndex; i++) {
                        System.out.printf("Product %s has Id %s",arrProducts[i].getProductName(),arrProducts[i].getProductId());
                    }
                    System.out.println("Please enter the id that you want to find");
                    String idToFind=scanner.nextLine();
                    int indexToEnterQuantity= objMain.findIndexById(currentIndex,arrProducts,idToFind);
                    while(indexToEnterQuantity==-1){
                        System.out.println("The id doesn't exist in the system");
                        idToFind=scanner.nextLine();
                        indexToEnterQuantity= objMain.findIndexById(currentIndex,arrProducts,idToFind);
                    }

                    break;
                case 8:
                    for (int i = 0; i < currentIndex; i++) {
                        System.out.printf("Product %s has Id %s",arrProducts[i].getProductName(),arrProducts[i].getProductId());
                    }
                    System.out.println("Please enter the id that you want to sell");
                    String idToSell= scanner.nextLine();
                    int indexToSell=objMain.findIndexById(currentIndex,arrProducts,idToSell);
                    while (indexToSell==-1){
                        System.out.println("The id doesn't exist in the system");
                        idToSell=scanner.nextLine();
                        indexToSell= objMain.findIndexById(currentIndex,arrProducts,idToSell);
                    }
                    System.out.println("Please enter the amount that you want to sell");
                    int quantityToSell=Integer.parseInt(scanner.nextLine());
                    while (quantityToSell<arrProducts[indexToSell].getQuantity()){
                        System.out.println("The amount of quantity excess the number we have, please re enter");
                        quantityToSell=Integer.parseInt(scanner.nextLine());
                    }
                    arrProducts[indexToSell].setQuantity(arrProducts[indexToSell].getQuantity()-quantityToSell);

                    break;
                case 9:
                    for (int i = 0; i < currentIndex; i++) {
                        System.out.printf("Product %s has Id %s",arrProducts[i].getProductName(),arrProducts[i].getProductId());
                    }
                    System.out.println("Please enter the id that you want to sell");
                    String idToUpdateStatus= scanner.nextLine();
                    int indexToUpdateStatus=objMain.findIndexById(currentIndex,arrProducts,idToUpdateStatus);
                    while (indexToUpdateStatus==-1){
                        System.out.println("The id doesn't exist in the system");
                        idToUpdateStatus=scanner.nextLine();
                        indexToUpdateStatus= objMain.findIndexById(currentIndex,arrProducts,idToUpdateStatus);
                    }
                    System.out.println("Please enter your status");
                    String preUpdateStatus= scanner.nextLine();
                    while (!preUpdateStatus.equals("true")&&!preUpdateStatus.equals("false")){
                        System.out.println("Status only receive true or false, please enter again your status");
                        preUpdateStatus=scanner.nextLine();

                    }

                    arrProducts[indexToUpdateStatus].setStatus(Boolean.parseBoolean(preUpdateStatus));
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Please enter value from 1-10");
                    
            }

        }

    }

    public void sortDescendingInterest(int currentIndex, Product[] arrProduct) {


        for (int i = 0; i < currentIndex - 1; i++) {
            int maxIndex = i;

            // Find the index of the maximum element in the unsorted part of the array
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrProduct[j].getProfit() > arrProduct
                        [maxIndex].getProfit()) {
                    maxIndex = j;
                }
            }

            // Swap the found maximum element with the first element in the unsorted part
            Product temp = arrProduct[maxIndex];
            arrProduct[maxIndex] = arrProduct[i];
            arrProduct[i] = temp;
        }
    }

    public int findIndexById(int currentIndex,Product[] arrProduct,String id){
        int index=-1;
        for (int i = 0; i < currentIndex; i++) {
            if(arrProduct[i].getProductId().equals(id)){
                index= i;
                break;
            }

        }
        return index;
    }
}
