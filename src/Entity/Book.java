package Entity;

import Business.IEntity;
import BusinessImp.CommonFunction;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements IEntity<Book>, Serializable {


    public static List<Book> listBook=new ArrayList<>();
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;


    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String inputId(Scanner scanner){
        String regex = "^B.{3}$";
        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.println("Please enter your Book ID");
            String id=scanner.nextLine();
            boolean isDuplicate=false;
            Matcher matcher = pattern.matcher(id);
            if(id.length()==4){
                if(matcher.matches()){
                    for (Book book:listBook
                    ) {
                        if(book.getId().equals(id)){
                            isDuplicate=true;
                            break;
                        }
                        isDuplicate=false;
                    }
                    if(isDuplicate){
                        System.err.println("The id already exist in the system");
                    }else {
                        return id;
                    }
                }else {
                    System.err.println("The Id must have form Bxxx (Where x is character)");
                }
            }else {
                System.err.println("The id must contains 4 letter");
            }
        }while (true);
    }

    public static String inputTitle(Scanner scanner){
        do {
            System.out.println("Please enter Book Title");
            String name=scanner.nextLine();
            boolean isDuplicate=false;
            if(!name.trim().isEmpty()){
                if(name.length()>6 && name.length()<50){
                    for (Book book :
                            listBook) {
                        if (book.getTitle().trim().equals(name.trim())){
                            isDuplicate=true;
                            break;
                        }
                        isDuplicate=false;
                    }
                    if(isDuplicate){
                        System.err.println("The title already exist in the system");
                    }else {
                        return name;
                    }
                }else {
                    System.err.println("Please enter name have length from 6 to 30");
                }
            }else {
                System.err.println("You must enter the name");
            }
        }while (true);
    }

    public static String inputAuthor(Scanner scanner) {
        do {
            System.out.println("Please enter Book Author");
            String description = scanner.nextLine();
            if (!description.trim().isEmpty()) {
                return description;
            } else {
                System.err.println("You must enter the book's author");
            }
        } while (true);
    }

    public static String inputPublisher(Scanner scanner) {
        do {
            System.out.println("Please enter Book Publisher");
            String description = scanner.nextLine();
            if (!description.trim().isEmpty()) {
                return description;
            } else {
                System.err.println("You must enter the book's publisher");
            }
        } while (true);
    }

    public static int inputYear(Scanner scanner) {
        int publishYear = CommonFunction.CheckInteger("Publish Year",scanner);
        while (!isValidatePublishYear(publishYear))
            publishYear = CommonFunction.CheckInteger("Publish Year",scanner);
        return publishYear;
    }

    public static boolean isValidatePublishYear(int publishYear) {
        if (publishYear < 1970 || publishYear > Year.now().getValue()) {
            System.err.println("Published year must be greater than 1970 and smaller than current year!");
            return false;
        }
        return true;
    }

    public static int inputCategoryId(Scanner scanner){
        Category categoryTrash=new Category();
        categoryTrash.displayData(Category.listCategory);
        do {
            int CategoryId= CommonFunction.CheckInteger("CategoryId",scanner);
            int index=CommonFunction.findIndexByIdCategory(CategoryId);
            if(index!=-1){
                return CategoryId;
            }else {
                System.err.println("The Category doesn't exist,please enter again");
            }
        }while (true);
    }

    public static String inputDescription(Scanner scanner) {
        do {
            System.out.println("Please enter Book description");
            String description = scanner.nextLine();
            if (!description.trim().isEmpty()) {
                return description;
            } else {
                System.err.println("You must enter the description");
            }
        } while (true);
    }
    @Override
    public void inputData(Scanner scanner) {
        this.id=inputId(scanner);
        this.title=inputTitle(scanner);
        this.author=inputAuthor(scanner);
        this.publisher=inputPublisher(scanner);
        this.year=inputYear(scanner);
        this.description=inputDescription(scanner);
        this.categoryId=inputCategoryId(scanner);
    }

    @Override
    public void displayData(List<Book> listItem) {
        printTableHeaderWithBoundaryAndAdditionalFields();
        for (Book book : listItem) {
            System.out.printf("| %-5s | %-12s | %-12s | %-12s | %-5d | %-30s | %-5d |%n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(),
                    book.getYear(), book.getDescription(), book.getCategoryId());
        }
        printTableFooterWithBoundary();
    }

    private static void printTableFooterWithBoundary() {
        printHorizontalLineWithBoundary();
    }

    private static void printTableHeaderWithBoundaryAndAdditionalFields() {
        printHorizontalLineWithBoundary();
        System.out.printf("| %-5s | %-12s | %-12s | %-12s | %-5s | %-30s | %-5s |%n",
                "ID", "Title", "Author", "Publisher", "Year", "Description","Category ID");
        printHorizontalLineWithBoundary();
    }

    private static void printHorizontalLineWithBoundary() {
        System.out.println("+-------+------------+------------+------------+-------+--------------------------------+-------+");
    }
}
