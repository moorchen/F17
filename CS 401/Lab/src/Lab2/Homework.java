package Lab2;
import java.util.Scanner;
public class Homework {
    public static class Circle {
        private double radius;  //  construct private variable
        public void setRadius(double r){
            this.radius = r;
        }  //  use setter
        Circle(double r){
            this.setRadius(r);
        }  //  use constructor
        public double getArea(){  //  use getter
            double area = calcArea(radius);
            return area;
        }
        public double getCircumference(){  // use getter
            double circumference = calcCircumference(radius);
            return circumference;
        }
        public String toString() {  //  use toString method
            return "When radius was " + radius + ", Area is " + Math.round(getArea()) + ", and Circumference is "
                    + Math.round(getCircumference());  //  in order to match the result in example, I use Math.round()
        }
    }
    public static void main(String[] args){
        System.out.println("This is the assignment of Lab 2");  //  friendly instruction
        System.out.println("Please insert radius");
        Scanner input = new Scanner(System.in);  //  make input variable
        double r = input.nextDouble();  //  read input
        input.close();
        Circle r1 = new Circle(r);
        System.out.println(r1.toString());
    }
    static double calcArea(double r) {
        return Math.PI * Math.pow(r, 2);
    }  //  method that calculate area of a circle
    static double calcCircumference(double r) {
        return Math.PI * 2 * r;
    }  //  method that calculate circumference of a cricle
}
