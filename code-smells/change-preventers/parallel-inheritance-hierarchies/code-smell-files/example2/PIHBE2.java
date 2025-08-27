enum OperatingSystem {
  IOS, ANDROID
}

class PlatformDetector {
  public static OperatingSystem detectPlatform() {
    String os = System.getProperty("os.name", "").toLowerCase();
    if (os.contains("mac") || os.contains("ios")) {
      return OperatingSystem.IOS;
    } else {
      return OperatingSystem.ANDROID;
    }
  }

  public static UIFactory getFactory() {
    return detectPlatform() == OperatingSystem.IOS
      ? new iOSUIFactory()
      : new AndroidUIFactory();
  }
}

abstract class AbstractAlertButton {
  abstract void render();
}

class iOSAbstractAlertButton extends AbstractAlertButton {
  @Override
  void render() {
    System.out.println("🐦 Rendering iOS‑style button");
  }
}

class AndroidAbstractAlertButton extends AbstractAlertButton {
  @Override
  void render() {
    System.out.println("🤖 Rendering Android‑style button");
  }
}

abstract class AbstractAlertDialog {
  abstract void show(String message);
}

class iOSAbstractAlertDialog extends AbstractAlertDialog {
  @Override
  void show(String message) {
    System.out.println("🐦 iOS Alert: " + message);
  }
}

class AndroidAbstractAlertDialog extends AbstractAlertDialog {
  @Override
  void show(String message) {
    System.out.println("🤖 Android Alert: " + message);
  }
}

interface UIFactory {
  AbstractAlertButton createButton();
  AbstractAlertDialog createAlertDialog();
}

class iOSUIFactory implements UIFactory {
  @Override
  public AbstractAlertButton createButton() {
    return new iOSAbstractAlertButton();
  }

  @Override
  public AbstractAlertDialog createAlertDialog() {
    return new iOSAbstractAlertDialog();
  }
}

class AndroidUIFactory implements UIFactory {
  @Override
  public AbstractAlertButton createButton() {
    return new AndroidAbstractAlertButton();
  }

  @Override
  public AbstractAlertDialog createAlertDialog() {
    return new AndroidAbstractAlertDialog();
  }
}

public class PIHBE2 {
  public static void main(String[] args) {
    UIFactory factory = PlatformDetector.getFactory();

    AbstractAlertButton abstractAlertButton = factory.createButton();
    AbstractAlertDialog alert = factory.createAlertDialog();

    abstractAlertButton.render();
    alert.show("Welcome to the cross‑platform app!");
  }
}
