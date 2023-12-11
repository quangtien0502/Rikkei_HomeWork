package HomeWork.Problem2;
//Test
public class Main {
    public static void main(String[] args) {
        double CircleArea1=StaticMethod.calCircleArea(3.5);
        double CircleArea2=StaticMethod.calCircleArea(6);
        double CircleArea3= StaticMethod.calRectangleArea(6,2.5);
        double CircleArea4=StaticMethod.calRectangleArea(7,4);
        double CircleArea5= StaticMethod.calTriangleArea(3,4,5);

        System.out.printf("CircleArea1 %,.2f CircleArea2 %,.2f CircleArea3 %,.2f CircleArea4 %,.2f CircleArea5 %,.2f",CircleArea1,CircleArea2,CircleArea3,CircleArea4,CircleArea5);
    }
}
