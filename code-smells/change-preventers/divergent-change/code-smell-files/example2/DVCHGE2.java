import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

enum ShapeTypeVariation {
  CIRCLE, RECTANGLE, SQUARE, TRIANGLE
}

class ShapeVariation {
  private final ShapeTypeVariation type;
  private int x, y;
  private double sizeParam;

  public ShapeVariation(ShapeTypeVariation type, int x, int y, double sizeParam) {
    this.type = type;
    this.x = x;
    this.y = y;
    this.sizeParam = sizeParam;
  }

  public ShapeTypeVariation getType() {
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
      "ShapeVariation[type=%s, x=%d, y=%d, sizeParam=%.2f]",
      type, x, y, sizeParam
    );
  }
}

@FunctionalInterface
interface TriConsumer<T,U,V> {
  void accept(T t, U u, V v);
}

interface LucyChartServiceVariation {
  void draw(ShapeVariation s);
  void resize(ShapeVariation s, double factor);
  void move(ShapeVariation s, int deltaX, int deltaY);
}

class LucyChartServiceVariationImpl implements LucyChartServiceVariation {
  private static final Logger logger = Logger.getLogger(LucyChartServiceVariationImpl.class.getName());

  private final DrawService drawSvc;
  private final ResizeService resizeSvc;
  private final MoveService moveSvc;

  private String userId, password;
  private int numberOfOperations = 0;

  public LucyChartServiceVariationImpl() {
    this.drawSvc   = new DrawService();
    this.resizeSvc = new ResizeService();
    this.moveSvc   = new MoveService();

    Scanner scanner = new Scanner(System.in);

    logger.info("Prompting for userId");
    System.out.print("Enter userId: ");
    this.userId = scanner.nextLine().trim();

    logger.info("Prompting for password");
    System.out.print("Enter password: ");
    this.password = scanner.nextLine().trim();

    logger.info("LucyChartServiceVariation initialized for userId=" + this.userId);
  }

  @Override
  public void draw(ShapeVariation s) {
    numberOfOperations++;
    logger.info("[" + userId + "] draw() called, total ops=" + numberOfOperations);
    drawSvc.draw(s);
  }

  @Override
  public void resize(ShapeVariation s, double factor) {
    numberOfOperations++;
    logger.info("[" + userId + "] resize() called, total ops=" + numberOfOperations);
    resizeSvc.resize(s, factor);
  }

  @Override
  public void move(ShapeVariation s, int deltaX, int deltaY) {
    numberOfOperations++;
    logger.info("[" + userId + "] move() called, total ops=" + numberOfOperations);
    moveSvc.move(s, deltaX, deltaY);
  }

  public void printUsageFact() {
    System.out.printf("Fact: user %s has %d operations on LucyChart.%n",
      userId, numberOfOperations);
  }
}

class DrawService {
  private final Map<ShapeTypeVariation, Consumer<ShapeVariation>> drawMap;

  DrawService() {
    drawMap = Map.of(
      ShapeTypeVariation.CIRCLE, s -> System.out.println(
        "Draw circle at (" + s.getX() + "," + s.getY() +
          ") with radius " + s.getSizeParam()
      ),
      ShapeTypeVariation.RECTANGLE, s -> System.out.println(
        "Draw rectangle at (" + s.getX() + "," + s.getY() +
          ") with width/height " + s.getSizeParam()
      ),
      ShapeTypeVariation.SQUARE, s -> System.out.println(
        "Draw square at (" + s.getX() + "," + s.getY() +
          ") with side " + s.getSizeParam()
      ),
      ShapeTypeVariation.TRIANGLE, s -> System.out.println(
        "Draw triangle at (" + s.getX() + "," + s.getY() +
          ") with side length " + s.getSizeParam()
      )
    );
  }

  void draw(ShapeVariation s) {
    var action = drawMap.get(s.getType());
    if (action == null) throw new IllegalArgumentException("No drawer for " + s.getType());
    action.accept(s);
  }
}

class ResizeService {
  private final Map<ShapeTypeVariation, BiConsumer<ShapeVariation, Double>> resizeMap;

  ResizeService() {
    resizeMap = Map.of(
      ShapeTypeVariation.CIRCLE,    (s, f) -> {
        System.out.println("Resizing circle by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      },
      ShapeTypeVariation.RECTANGLE, (s, f) -> {
        System.out.println("Resizing rectangle by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      },
      ShapeTypeVariation.SQUARE,    (s, f) -> {
        System.out.println("Resizing square by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      },
      ShapeTypeVariation.TRIANGLE,  (s, f) -> {
        System.out.println("Resizing triangle by a factor of " + f);
        s.setSizeParam(s.getSizeParam() * f);
      }
    );
  }

  void resize(ShapeVariation s, double factor) {
    var action = resizeMap.get(s.getType());
    if (action == null) throw new IllegalArgumentException("No resizer for " + s.getType());
    action.accept(s, factor);
  }
}

class MoveService {
  private final Map<ShapeTypeVariation, TriConsumer<ShapeVariation, Integer, Integer>> moveMap;

  MoveService() {
    moveMap = Map.of(
      ShapeTypeVariation.CIRCLE,    (s, dx, dy) -> {
        System.out.println("Moving circle from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      },
      ShapeTypeVariation.RECTANGLE, (s, dx, dy) -> {
        System.out.println("Moving rectangle from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      },
      ShapeTypeVariation.SQUARE,    (s, dx, dy) -> {
        System.out.println("Moving square from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      },
      ShapeTypeVariation.TRIANGLE,  (s, dx, dy) -> {
        System.out.println("Moving triangle from (" + s.getX() + "," + s.getY() + ") by (" + dx + "," + dy + ")");
        s.setPosition(s.getX() + dx, s.getY() + dy);
      }
    );
  }

  void move(ShapeVariation s, int deltaX, int deltaY) {
    var action = moveMap.get(s.getType());
    if (action == null) throw new IllegalArgumentException("No mover for " + s.getType());
    action.accept(s, deltaX, deltaY);
  }
}

public class DVCHGE2 {
  public static void main(String[] args) {
    LucyChartServiceVariation lucy = new LucyChartServiceVariationImpl();

    ShapeVariation circle    = new ShapeVariation(ShapeTypeVariation.CIRCLE,    0,  0, 10.0);
    ShapeVariation rectangle = new ShapeVariation(ShapeTypeVariation.RECTANGLE, 5,  5, 20.0);
    ShapeVariation square    = new ShapeVariation(ShapeTypeVariation.SQUARE,   -5, 10,  8.0);
    ShapeVariation triangle  = new ShapeVariation(ShapeTypeVariation.TRIANGLE, 3, -3,  6.0);

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

    System.out.println();
    ((LucyChartServiceVariationImpl)lucy).printUsageFact();
  }
}
