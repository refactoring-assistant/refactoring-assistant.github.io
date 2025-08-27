import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.Map;

enum ShapeTypeVariation {
  CIRCLE, RECTANGLE, SQUARE, TRIANGLE
}

class ShapeVariation {
  private final ShapeType type;
  private int x, y;
  private double sizeParam;

  public ShapeVariation(ShapeType type, int x, int y, double sizeParam) {
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

@FunctionalInterface
interface TriConsumer<T,U,V> extends BiConsumer<T,U> {
  void accept(T t, U u, V v);

  @Override
  default void accept(T t, U u) {
    throw new UnsupportedOperationException(
      "Use accept(T, U, V) instead"
    );
  }

  default TriConsumer<T,U,V> andThen(TriConsumer<? super T,? super U,? super V> after) {
    Objects.requireNonNull(after);
    return (t, u, v) -> {
      accept(t,u,v);
      after.accept(t,u,v);
    };
  }
}

class MoveServiceVariation {
  private final Map<ShapeType, TriConsumer<Shape, Integer, Integer>> moveMap;

  public MoveServiceVariation() {
    moveMap = Map.of(
      ShapeType.CIRCLE,    (s, dx, dy) -> {
        System.out.println("Moving circle from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      },
      ShapeType.RECTANGLE, (s, dx, dy) -> {
        System.out.println("Moving rectangle from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      },
      ShapeType.SQUARE,    (s, dx, dy) -> {
        System.out.println("Moving square from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      },
      ShapeType.TRIANGLE,  (s, dx, dy) -> {
        System.out.println("Moving triangle from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      }
    );
  }

  public void move(Shape shape, int dx, int dy) {
    TriConsumer<Shape, Integer, Integer> action = moveMap.get(shape.getType());
    if (action == null) {
      throw new IllegalArgumentException("No mover defined for shape type " + shape.getType());
    }
    action.accept(shape, dx, dy);
  }
}

public class ILCGE2 {
  public static void main(String[] args) {
    MoveService moveService = new MoveService();

    Shape circle    = new Shape(ShapeType.CIRCLE,    0,  0, 10.0);
    Shape rectangle = new Shape(ShapeType.RECTANGLE, 5,  5, 20.0);
    Shape square    = new Shape(ShapeType.SQUARE,   -5, 10,  8.0);
    Shape triangle  = new Shape(ShapeType.TRIANGLE, 3, -3,  6.0);

    System.out.println("Before moving:");
    System.out.println(circle);
    System.out.println(rectangle);
    System.out.println(square);
    System.out.println(triangle);

    System.out.println("\nApplying move() to each shape:");

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
