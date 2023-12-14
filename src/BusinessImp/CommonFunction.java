package BusinessImp;

import Entity.Book;
import Entity.Category;

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

    public static int findIndexByIdBook(String id){
        int index=-1;
        for (Book book:Book.listBook
        ) {
            if(book.getId().equals(id)){
                index= Book.listBook.indexOf(book);
            }
        }
        return index;
    }

    public static void displayCategory(List<Category> categories){
        Category category=new Category();
        category.displayData(categories);
    }

    public static void displayBook(List<Book> books){
        Book book=new Book();
        book.displayData(books);
    }
}
