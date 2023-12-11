package Business;

import Entity.Book;

import java.util.Scanner;

public interface IBook {
    void inputData(Scanner scanner);
    void displayData();
    int compareTo(Book otherBook);
}
