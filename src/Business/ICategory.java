package Business;

import java.util.List;
import java.util.Scanner;

public interface ICategory<T> {
    void inputData(Scanner scanner);
    void displayData(List<T> Category);
}
