package Presentation;

import BusinessImp.BookMenuLogic;
import BusinessImp.CommonFunction;
import Entity.Book;
import Entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void displayMenu(Scanner scanner){
        boolean isExit=false;
        do {
            Book.listBook=readDataFromFileForBook();
            System.out.println("""
                    ===== Books MANAGEMENT =====
                    1. Add new Books.
                    2. Update Books
                    3. Delete Books.
                    4. Find Books
                    5. Display Books by Category
                    6. Back.\s
                    """
            );
            int choice= CommonFunction.CheckInteger("choice",scanner);
            switch (choice){
                case 1:
                    if(Category.listCategory.size()!=0){
                        int number=CommonFunction.CheckInteger("The amount of books that you want to enter",scanner);
                        for (int i = 0; i < number; i++) {
                            Book book = new Book();
                            book.inputData(scanner);
                            Book.listBook.add(book);
                        }
                    }else {
                        System.err.println("You must enter a category first");
                    }
                    writeDataToFile(Book.listBook);
                    break;
                case 2:
                    BookMenuLogic.updateBook(scanner);
                    writeDataToFile(Book.listBook);
                    break;
                case 3:
                    BookMenuLogic.deleteBook(scanner);
                    writeDataToFile(Book.listBook);
                    break;
                case 4:
                    BookMenuLogic.findBookByKeyWord(scanner);
                    writeDataToFile(Book.listBook);
                    break;
                case 5:
                    BookMenuLogic.listBooksNumberOfAllCategories();
                    break;
                case 6:
                    isExit=true;
                    break;

            }
        }while (!isExit);
    }

    public static List<Book> readDataFromFileForBook(){
        List<Book> listBook = null;
        File file = new File("books.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listBook = (List<Book>) ois.readObject();
            return listBook;
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

    public static void writeDataToFile(List<Book> ListBook){
        File file = new File("books.txt");

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ListBook);
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
