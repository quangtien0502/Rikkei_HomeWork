package HomeWork.Problem2;
//Test
public class StaticMethod {
    private static final double PI=3.14F;
    public static double calCircleArea(double radius){
        return radius*radius*PI;
    }

    public static double calRectangleArea(double height,double width){
        return height*width;
    }

    public static double calTriangleArea(double a,double b,double c){
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));

    }
}
