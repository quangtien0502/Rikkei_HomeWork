package Entity;

import java.util.Scanner;

public class Book {


    private String bookId;
    private String bookName;
    private  float importPrice;
    private float exportPrice;
    private  String author;
    private float interest;
    private int year;

    public Book() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(int currentIndex,Book[] arrBook){
        boolean isNotCorrectBookId=true,isNotCorrectBookName=true;
        Scanner scanner= new Scanner(System.in);
        System.out.println("Please enter your bookId");
        this.bookId=scanner.nextLine();
        if(currentIndex>1){
            while (isNotCorrectBookId){
                for (int i = 0; i < currentIndex-1; i++) {
                    if(arrBook[i].bookId.equals(this.bookId)){
                        System.out.println("Please re enter your Book Id again");
                        this.bookId=scanner.nextLine();
                        break;
                    }
                    isNotCorrectBookId=false;
                }
            }
        }

        System.out.println("Please enter your Book Name");
        this.bookName=scanner.nextLine();

        while (isNotCorrectBookName){
            if(this.bookName.length()==4 && this.bookName.charAt(0)=='B'){
                if(currentIndex>1){
                    for (int i = 0; i < currentIndex-1; i++) {
                        if(arrBook[i].bookName.equals(this.bookName)){
                            System.out.println("Please re enter your Book Name again");
                            this.bookName=scanner.nextLine();
                            break;
                        }
                        isNotCorrectBookName=false;
                    }
                }else {
                    isNotCorrectBookName=false;
                }
            }else {
                System.out.println("Please enter your Book Name again");
                this.bookName=scanner.nextLine();
            }
        }

        System.out.println("Please enter import Price");
        this.importPrice=Float.parseFloat(scanner.nextLine());
        while (true){
            if(this.importPrice>0){

                break;
            }else {
                System.out.println("Please re enter your import Price");
                this.importPrice=Float.parseFloat(scanner.nextLine());
            }

        }

        System.out.println("Please enter your export Price");
        this.exportPrice=Float.parseFloat(scanner.nextLine());

        while (true){
            if(this.exportPrice>1.3*this.importPrice){

                break;
            }else {
                System.out.println("Please re enter your import Price");
                this.importPrice=Float.parseFloat(scanner.nextLine());
            }

        }

        System.out.println("Please enter author");
        this.author=scanner.nextLine();
        while (true){
            if(this.author.length()>6 && this.author.length()<50){

                break;
            }else {
                System.out.println("Please re enter your author");
                this.author=scanner.nextLine();
            }

        }

        System.out.println("Please enter public year");
        this.year =Integer.parseInt(scanner.nextLine());

        while (true){
            if(this.year>2000){

                break;
            }else {
                System.out.println("Please re enter your year");
                this.year =Integer.parseInt(scanner.nextLine());
            }

        }


    }

    public void calculateInterest(){
        this.interest=this.exportPrice-this.importPrice;
    }

    public void displayData(){
        System.out.printf("Book Id: %s Book Name: %s import Price: %f export Price: %f interest:%f author: %s year: %d \n",bookId,bookName,importPrice,exportPrice,interest,author,year);
    }


}
