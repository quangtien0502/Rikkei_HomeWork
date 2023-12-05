package Run;

import Entity.Book;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Book[] arrBook = new Book[100];
        int currentIndex = 0;
        Scanner scanner = new Scanner(System.in);
        Main objMain = new Main();
        while (true) {
            System.out.println("*********************MENU******************");
            System.out.println("""
                    1. Insert information of n books(n is received from keyboard))
                    2. Calculate interest for all books
                    3. Display all Books information
                    4. Sort Book with ascending price
                    5. Sort Book with descending interest
                    6. Find Book According To Book Name
                    7. List all Books with year public
                    8. List all Books with author
                    9. Exit""");

            System.out.println("Please enter your choice");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Please enter the amount of Books that you want to enter");
                    int number = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < number; i++) {
                        currentIndex++;
                        Book book = new Book();
                        book.inputData(currentIndex, arrBook);
                        arrBook[currentIndex-1] = book;

                    }
                    break;
                case 2:
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].calculateInterest();
                    }
                    break;
                case 3:
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                    }
                    break;
                case 4:

                    objMain.sortAscendingPrice(currentIndex, arrBook);
                    break;
                case 5:

                    objMain.sortDescendingInterest(currentIndex, arrBook);
                    break;
                case 6:
                    System.out.println("Please enter Book Name that you want to find");
                    String findBookName=scanner.nextLine();

                    for (int i = 0; i < currentIndex; i++) {
                        if(arrBook[i].getBookName().equals(findBookName)){
                            arrBook[i].displayData();
                        }
                    }
                    break;
                case 7:
                    System.out.println("Please enter the year that you want to find Book");
                    int yearFindBook=Integer.parseInt(scanner.nextLine());

                    for (int i = 0; i < currentIndex; i++) {
                        if(arrBook[i].getYear()==yearFindBook){
                            arrBook[i].displayData();
                        }
                    }
                    break;
                case 8:
                    System.out.println("Please enter the author of the Book");
                    String authorFindBook=scanner.nextLine();
                    for (int i = 0; i < currentIndex; i++) {
                        if(arrBook[i].getAuthor().equals(authorFindBook)){
                            arrBook[i].displayData();
                        }
                    }
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Please enter value from 1-9");
            }
        }
    }

    public void sortAscendingPrice(int currentIndex, Book[] arrBook) {


        for (int i = 0; i < currentIndex - 1; i++) {
            int minIndex = i;

            // Find the index of the minimum element in the unsorted part of the array
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrBook[j].getExportPrice() < arrBook[minIndex].getExportPrice()) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element in the unsorted part
            Book temp = arrBook[minIndex];
            arrBook[minIndex] = arrBook[i];
            arrBook[i] = temp;
        }
    }

    public void sortDescendingInterest(int currentIndex, Book[] arrBook) {


        for (int i = 0; i < currentIndex - 1; i++) {
            int maxIndex = i;

            // Find the index of the maximum element in the unsorted part of the array
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrBook[j].getInterest() > arrBook[maxIndex].getInterest()) {
                    maxIndex = j;
                }
            }

            // Swap the found maximum element with the first element in the unsorted part
            Book temp = arrBook[maxIndex];
            arrBook[maxIndex] = arrBook[i];
            arrBook[i] = temp;
        }
    }
}
