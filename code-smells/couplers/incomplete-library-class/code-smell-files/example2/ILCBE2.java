import java.awt.Point;
import java.util.Map;
import java.util.function.BiConsumer;

enum ShapeType {
  CIRCLE, RECTANGLE, SQUARE, TRIANGLE
}

class Shape {
  private final ShapeType type;
  private int x, y;
  private double sizeParam;

  public Shape(ShapeType type, int x, int y, double sizeParam) {
    this.type = type;
    this.x = x; this.y = y; this.sizeParam = sizeParam;
  }

  public ShapeType getType() { return type; }
  public int getX() { return x; }
  public int getY() { return y; }
  public double getSizeParam() { return sizeParam; }
  public void setSizeParam(double s) { this.sizeParam = s; }
  public void setPosition(int nx, int ny) { x = nx; y = ny; }

  @Override
  public String toString() {
    return String.format("Shape[type=%s, x=%d, y=%d, sizeParam=%.2f]",
      type, x, y, sizeParam);
  }
}

class MoveService {
  private final Map<ShapeType, BiConsumer<Shape, Point>> moveMap;

  MoveService() {
    moveMap = Map.of(
      ShapeType.CIRCLE, (s, p) -> {
        System.out.println("Moving circle from ("
          + s.getX() + "," + s.getY() + ") by ("
          + p.x + "," + p.y + ")");
        s.setPosition(s.getX() + p.x, s.getY() + p.y);
      },
      ShapeType.RECTANGLE, (s, p) -> {
        System.out.println("Moving rectangle from ("
          + s.getX() + "," + s.getY() + ") by ("
          + p.x + "," + p.y + ")");
        s.setPosition(s.getX() + p.x, s.getY() + p.y);
      },
      ShapeType.SQUARE, (s, p) -> {
        System.out.println("Moving square from ("
          + s.getX() + "," + s.getY() + ") by ("
          + p.x + "," + p.y + ")");
        s.setPosition(s.getX() + p.x, s.getY() + p.y);
      },
      ShapeType.TRIANGLE, (s, p) -> {
        System.out.println("Moving triangle from ("
          + s.getX() + "," + s.getY() + ") by ("
          + p.x + "," + p.y + ")");
        s.setPosition(s.getX() + p.x, s.getY() + p.y);
      }
    );
  }

  public void move(Shape s, int dx, int dy) {
    BiConsumer<Shape, Point> action = moveMap.get(s.getType());
    if (action == null) {
      throw new IllegalArgumentException("No mover for " + s.getType());
    }
    action.accept(s, new Point(dx, dy));
  }
}


public class ILCBE2 {
  public static void main(String[] args) {
    MoveService moveService = new MoveService();

    Shape circle = new Shape(ShapeType.CIRCLE,    0,  0, 10.0);
    Shape rectangle = new Shape(ShapeType.RECTANGLE, 5,  5, 20.0);
    Shape square = new Shape(ShapeType.SQUARE,   -5, 10,  8.0);
    Shape triangle = new Shape(ShapeType.TRIANGLE, 3, -3,  6.0);

    System.out.println("Before moving:");
    System.out.println(circle);
    System.out.println(rectangle);
    System.out.println(square);
    System.out.println(triangle);

    System.out.println("\nMoving each shape:");

    moveService.move(circle,    2,  3);
    moveService.move(rectangle, -4,  7);
    moveService.move(square,     5, -2);
    moveService.move(triangle,   0, 10);

    System.out.println("\nAfter moving:");
    System.out.println(circle);
    System.out.println(rectangle);
    System.out.println(square);
    System.out.println(triangle);
  }
}
