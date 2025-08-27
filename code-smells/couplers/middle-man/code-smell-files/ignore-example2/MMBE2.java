import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

enum ShapeType {
  CIRCLE, RECTANGLE, SQUARE, TRIANGLE
}

class Shape {
  private final ShapeType type;
  private int x, y;
  private double sizeParam;

  public Shape(ShapeType type, int x, int y, double sizeParam) {
    this.type = type;
    this.x = x;
    this.y = y;
    this.sizeParam = sizeParam;
  }

  public ShapeType getType() {
    return type;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public double getSizeParam() {
    return sizeParam;
  }

  public void setSizeParam(double s) {
    this.sizeParam = s;
  }

  public void setPosition(int nx, int ny) {
    x = nx; y = ny;
  }

  public String toString() {
    return String.format(
      "Shape[type=%s, x=%d, y=%d, sizeParam=%.2f]",
      type, x, y, sizeParam
    );
  }
}

@FunctionalInterface
interface TriConsumer<T,U,V> {
  void accept(T t, U u, V v);
}

interface LucyChartService {
  void draw(Shape s);
  void resize(Shape s, double factor);
  void move(Shape s, int deltaX, int deltaY);
}

class LucyChartServiceImpl implements LucyChartService {
  private final DrawService drawSvc;
  private final ResizeService resizeSvc;
  private final MoveService moveSvc;

  public LucyChartServiceImpl() {
    this.drawSvc   = new DrawService();
    this.resizeSvc = new ResizeService();
    this.moveSvc   = new MoveService();
  }

  @Override
  public void draw(Shape s) {
    drawSvc.draw(s);
  }

  @Override
  public void resize(Shape s, double factor) {
    resizeSvc.resize(s, factor);
  }

  @Override
  public void move(Shape s, int deltaX, int deltaY) {
    moveSvc.move(s, deltaX, deltaY);
  }
}


class DrawService {
  private final Map<ShapeType, Consumer<Shape>> drawMap;

  DrawService() {
    drawMap = Map.of(
      ShapeType.CIRCLE,    s -> System.out.println(
        "Draw circle at (" + s.getX() + "," + s.getY() +
          ") with radius " + s.getSizeParam()
      ),
      ShapeType.RECTANGLE, s -> System.out.println(
        "Draw rectangle at (" + s.getX() + "," + s.getY() +
          ") with width/height " + s.getSizeParam()
      ),
      ShapeType.SQUARE,    s -> System.out.println(
        "Draw square at (" + s.getX() + "," + s.getY() +
          ") with side " + s.getSizeParam()
      ),
      ShapeType.TRIANGLE,  s -> System.out.println(
        "Draw triangle at (" + s.getX() + "," + s.getY() +
          ") with side length " + s.getSizeParam()
      )
    );
  }

  void draw(Shape s) {
    var action = drawMap.get(s.getType());
    if (action == null) throw new IllegalArgumentException("No drawer for " + s.getType());
    action.accept(s);
  }
}

class ResizeService {
  private final Map<ShapeType, BiConsumer<Shape, Double>> resizeMap;

  ResizeService() {
    resizeMap = Map.of(
      ShapeType.CIRCLE,    (s, f) -> {
        System.out.println("Resizing circle by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      },
      ShapeType.RECTANGLE, (s, f) -> {
        System.out.println("Resizing rectangle by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      },
      ShapeType.SQUARE,    (s, f) -> {
        System.out.println("Resizing square by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      },
      ShapeType.TRIANGLE,  (s, f) -> {
        System.out.println("Resizing triangle by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      }
    );
  }

  void resize(Shape s, double factor) {
    var action = resizeMap.get(s.getType());
    if (action == null) throw new IllegalArgumentException("No resizer for " + s.getType());
    action.accept(s, factor);
  }
}

class MoveService {
  private final Map<ShapeType, TriConsumer<Shape, Integer, Integer>> moveMap;

  MoveService() {
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

  void move(Shape s, int deltaX, int deltaY) {
    var action = moveMap.get(s.getType());
    if (action == null) throw new IllegalArgumentException("No mover for " + s.getType());
    action.accept(s, deltaX, deltaY);
  }
}

public class MMBE2 {
  public static void main(String[] args) {
    LucyChartService lucy = new LucyChartServiceImpl();

    Shape circle    = new Shape(ShapeType.CIRCLE,    0,  0, 10.0);
    Shape rectangle = new Shape(ShapeType.RECTANGLE, 5,  5, 20.0);
    Shape square    = new Shape(ShapeType.SQUARE,   -5, 10,  8.0);
    Shape triangle  = new Shape(ShapeType.TRIANGLE, 3, -3,  6.0);

    System.out.println("\n--- CIRCLE ---");
    System.out.println("Initial:      " + circle);
    lucy.draw(circle);
    lucy.resize(circle, 1.5);
    System.out.println("After resize: " + circle);
    lucy.move(circle,  5,  5);
    System.out.println("After move:   " + circle);

    System.out.println("\n--- RECTANGLE ---");
    System.out.println("Initial:      " + rectangle);
    lucy.draw(rectangle);
    lucy.resize(rectangle, 0.5);
    System.out.println("After resize: " + rectangle);
    lucy.move(rectangle, -3,  7);
    System.out.println("After move:   " + rectangle);

    System.out.println("\n--- SQUARE ---");
    System.out.println("Initial:      " + square);
    lucy.draw(square);
    lucy.resize(square, 2.0);
    System.out.println("After resize: " + square);
    lucy.move(square, 10, -5);
    System.out.println("After move:   " + square);

    System.out.println("\n--- TRIANGLE ---");
    System.out.println("Initial:      " + triangle);
    lucy.draw(triangle);
    lucy.resize(triangle, 0.75);
    System.out.println("After resize: " + triangle);
    lucy.move(triangle,  0, 20);
    System.out.println("After move:   " + triangle);
  }
}
