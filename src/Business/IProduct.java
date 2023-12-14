package Business;

import java.util.List;
import java.util.Scanner;

public interface IProduct<T> {
    void inputData(Scanner scanner);
    void displayData(List<T> Category);
    void calProfit();
    float MIN_INTEREST_RATE=0.2F;



}
