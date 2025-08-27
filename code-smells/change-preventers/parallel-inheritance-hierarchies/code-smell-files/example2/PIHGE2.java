import java.util.Objects;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

enum OperatingSystemVariation {
  IOS, ANDROID
}

class PlatformDetectorVariation {
  public static OperatingSystemVariation detectPlatform() {
    String os = System.getProperty("os.name", "").toLowerCase();
    if (os.contains("mac") || os.contains("ios")) {
      return OperatingSystemVariation.IOS;
    } else {
      return OperatingSystemVariation.ANDROID;
    }
  }

  public static UIFactoryVariation getFactory() {
    return new PlatformUIFactoryVariation();
  }
}

abstract class AbstractAlertButtonVariation {
  abstract void render();
}

class PlatformAlertButtonVariation extends AbstractAlertButtonVariation {
  private final OperatingSystemVariation operatingSystemVariation;

  private static final Map<OperatingSystemVariation, Runnable> RENDERERS = new EnumMap<>(OperatingSystemVariation.class);
  static {
    RENDERERS.put(OperatingSystemVariation.IOS, () -> System.out.println("🐦 Rendering iOS‑style button"));
    RENDERERS.put(OperatingSystemVariation.ANDROID, () -> System.out.println("🤖 Rendering Android‑style button"));
  }

  public PlatformAlertButtonVariation() {
    this(PlatformDetectorVariation.detectPlatform());
  }

  public PlatformAlertButtonVariation(OperatingSystemVariation operatingSystemVariation) {
    this.operatingSystemVariation = Objects.requireNonNull(operatingSystemVariation);
  }

  @Override
  void render() {
    Runnable renderer = RENDERERS.get(operatingSystemVariation);
    if (renderer == null) {
      throw new IllegalStateException("Unknown operatingSystemVariation: " + operatingSystemVariation);
    }
    renderer.run();
  }
}

abstract class AbstractAlertDialogVariation {
  abstract void show(String message);
}

class PlatformAlertDialogVariation extends AbstractAlertDialogVariation {
  private final OperatingSystemVariation operatingSystemVariation;

  private static final Map<OperatingSystemVariation, Consumer<String>> DISPLAYERS = new EnumMap<>(OperatingSystemVariation.class);
  static {
    DISPLAYERS.put(OperatingSystemVariation.IOS, message -> System.out.println("🐦 iOS Alert: " + message));
    DISPLAYERS.put(OperatingSystemVariation.ANDROID, message -> System.out.println("🤖 Android Alert: " + message));
  }

  public PlatformAlertDialogVariation() {
    this(PlatformDetectorVariation.detectPlatform());
  }

  public PlatformAlertDialogVariation(OperatingSystemVariation operatingSystemVariation) {
    this.operatingSystemVariation = Objects.requireNonNull(operatingSystemVariation);
  }

  @Override
  void show(String message) {
    Consumer<String> displayer = DISPLAYERS.get(operatingSystemVariation);
    if (displayer == null) {
      throw new IllegalStateException("Unknown operatingSystemVariation: " + operatingSystemVariation);
    }
    displayer.accept(message);
  }
}

interface UIFactoryVariation {
  AbstractAlertButtonVariation createButton();
  AbstractAlertDialogVariation createAlertDialog();
}

class PlatformUIFactoryVariation implements UIFactoryVariation {
  private final OperatingSystemVariation operatingSystemVariation;

  public PlatformUIFactoryVariation() {
    this(PlatformDetectorVariation.detectPlatform());
  }

  public PlatformUIFactoryVariation(OperatingSystemVariation operatingSystemVariation) {
    this.operatingSystemVariation = Objects.requireNonNull(operatingSystemVariation);
  }

  @Override
  public AbstractAlertButtonVariation createButton() {
    return new PlatformAlertButtonVariation(operatingSystemVariation);
  }

  @Override
  public AbstractAlertDialogVariation createAlertDialog() {
    return new PlatformAlertDialogVariation(operatingSystemVariation);
  }
}

public class PIHGE2 {
  public static void main(String[] args) {
    UIFactoryVariation factory = PlatformDetectorVariation.getFactory();

    AbstractAlertButtonVariation abstractAlertButtonVariation = factory.createButton();
    AbstractAlertDialogVariation alert       = factory.createAlertDialog();

    abstractAlertButtonVariation.render();
    alert.show("Welcome to the cross‑operatingSystemVariation app!");
  }
}
