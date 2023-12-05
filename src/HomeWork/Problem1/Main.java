package HomeWork.Problem1;

public class Main {
    public static void main(String[] args) {
        MyClass myClass= new MyClass();
        System.out.println("My String value of My Class "+myClass.getMyString());

        myClass.setMyString("ABC");

        System.out.println("Value of My String after changing "+myClass.getMyString());
    }
}
