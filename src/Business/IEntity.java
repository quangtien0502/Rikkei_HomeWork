package Business;

import java.util.List;
import java.util.Scanner;

public interface IEntity<T> {
    void inputData(Scanner scanner);
    void displayData(List<T> Category);
}
