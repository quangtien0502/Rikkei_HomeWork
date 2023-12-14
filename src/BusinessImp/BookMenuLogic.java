package BusinessImp;

import Entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookMenuLogic {
    public static void updateBook(Scanner scanner) {
        boolean isExit = false;
        System.out.println("Please enter the Id of the Book");
        String id = scanner.nextLine();
        int index = CommonFunction.findIndexByIdBook(id);
        if (index != -1) {
            do {
                System.out.println("""
                        =====UPDATE BOOKS MENU =====
                        1. Update Book Title.
                        2. Update Book Author
                        3. Update Book Publisher\s
                        4. Update Book Year\s
                        5. Update Book Description\s
                        6. Update Book Category Id\s
                        7. Back.
                        """
                );
                int choice = CommonFunction.CheckInteger("choice", scanner);
                switch (choice) {
                    case 1:
                        Book.listBook.get(index).setTitle(Book.inputTitle(scanner));
                        break;
                    case 2:
                        Book.listBook.get(index).setDescription(Book.inputAuthor(scanner));
                        break;
                    case 3:
                        Book.listBook.get(index).setPublisher(Book.inputPublisher(scanner));
                        break;
                    case 4:
                        Book.listBook.get(index).setYear(Book.inputYear(scanner));
                        break;
                    case 5:
                        Book.listBook.get(index).setDescription(Book.inputDescription(scanner));
                        break;
                    case 6:
                        Book.listBook.get(index).setCategoryId(Book.inputCategoryId(scanner));
                        break;
                    case 7:
                        isExit = true;
                        break;
                    case 8:
                        CommonFunction.displayBook(Book.listBook);
                }
            } while (!isExit);
        } else {
            System.err.println("The Book doesn't exist in the system");
        }
    }

    public static void deleteBook(Scanner scanner) {
        int index;
        System.out.println("Please enter Book Id");
        String id = scanner.nextLine();
        index = CommonFunction.findIndexByIdBook(id);
        if (index != -1) {
            Book.listBook.remove(index);
        } else {
            System.err.println("Id like that doesn't exist");
        }

    }

    public static void findBookByKeyWord(Scanner scanner) {
        List<Book> listBookResult = new ArrayList<>();
        System.out.println("Please enter the keyword that you want to search for Book");
        String keyword = scanner.nextLine();
        for (Book book :
                Book.listBook) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getPublisher().contains(keyword)) {
                listBookResult.add(book);
            }
        }
        CommonFunction.displayBook(listBookResult);

    }

    public static void listBooksNumberOfAllCategories() {
        Map<String, List<Book>> booksByCategoryId = Book.listBook.stream().collect(Collectors.groupingBy(bookGroup -> CategoryMenuLogic.findCategoryNameById(bookGroup.getCategoryId())));
        booksByCategoryId.forEach((category, books) -> {
            System.out.println("Category: " + category);
            books.forEach(book -> {
                System.out.println("Books: " + book.getTitle());
            });
            System.out.println("---------------------------------------------");
        });
    }

}
