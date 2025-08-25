interface GeometricObject {
    public double calculateArea();

    public void calculatePerimeter();
}

class Circle implements GeometricObject  {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public void calculatePerimeter() {
        System.out.println("Circumference: " + 2 * Math.PI * this.radius);
    }

}

public class SGGE1 {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        circle.calculateArea();
    }
}
