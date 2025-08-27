interface ISensorVariation {
  double takeNewReading();
  double lastReading();
}

interface IDiscreteSensorVariation extends ISensorVariation {
  boolean status();
  void flipStatus();
  void setStatus(boolean value);

  @Override
  default double lastReading() {
    return 0;
  }

  @Override
  default double takeNewReading() {
    return 0;
  }
}

abstract class SensorVariation implements ISensorVariation {
  protected double currentValue;
  protected double lastValue;
  protected final BatteryVariation battery;

  public SensorVariation(double value, BatteryVariation battery) {
    this.currentValue = this.lastValue = value;
    this.battery      = battery;
  }

  public SensorVariation(double value) {
    this(value, new BatteryVariation(1.0));
  }

  public SensorVariation() {
    this(0);
  }

  @Override
  public double lastReading() {
    return lastValue;
  }

  public double getBatteryLevel() {
    return battery.getLevel();
  }
}

class BatteryVariation {
  private double level;
  private final double minLevel;
  private final double drainPerUse;

  public BatteryVariation(double initialLevel, double minLevel, double drainPerUse) {
    if (initialLevel < 0 || initialLevel > 1.0)
      throw new IllegalArgumentException("initialLevel must be 0.0–1.0");
    this.level       = initialLevel;
    this.minLevel    = minLevel;
    this.drainPerUse = drainPerUse;
  }

  public BatteryVariation(double initialLevel) {
    this(initialLevel, 0.1, 0.05);
  }

  public void check() {
    if (level < minLevel) {
      throw new IllegalStateException(
        String.format("Battery too low (%.2f < %.2f)", level, minLevel)
      );
    }
  }

  public void use() {
    level = Math.max(0.0, level - drainPerUse);
  }

  public double getLevel() {
    return level;
  }
}

class AtmosphericSensorVariation extends SensorVariation {
  public AtmosphericSensorVariation(double value, BatteryVariation battery) {
    super(value, battery);
  }

  public AtmosphericSensorVariation(double value) {
    super(value);
  }

  public AtmosphericSensorVariation() {
    super();
  }

  @Override
  public double takeNewReading() {
    battery.check();
    lastValue     = currentValue;
    currentValue  = SensorData.currentReading();
    battery.use();
    return currentValue;
  }
}

class WaterSensorVariation extends SensorVariation implements IDiscreteSensorVariation {
  private boolean flooding;

  public WaterSensorVariation(double value, BatteryVariation battery) {
    super(value, battery);
    this.flooding = value > 0.5;
  }

  public WaterSensorVariation(double value) {
    super(value);
  }

  public WaterSensorVariation() {
    super();
  }

  @Override
  public double takeNewReading() {
    battery.check();
    lastValue     = currentValue;
    currentValue  = SensorData.currentReading();
    flooding      = (currentValue > 0.5);
    battery.use();
    return currentValue;
  }

  @Override
  public boolean status() {
    return flooding;
  }

  @Override
  public void flipStatus() {
    flooding = !flooding;
  }

  @Override
  public void setStatus(boolean value) {
    flooding = value;
  }
}

class SmokeSensorVariation extends SensorVariation implements IDiscreteSensorVariation {
  private boolean smokeDetected;
  private static final double SMOKE_THRESHOLD = 0.7;

  public SmokeSensorVariation(double value, BatteryVariation battery) {
    super(value, battery);
    this.smokeDetected = value > SMOKE_THRESHOLD;
  }

  public SmokeSensorVariation(double value) {
    super(value);
  }

  public SmokeSensorVariation() {
    super();
  }

  @Override
  public double takeNewReading() {
    battery.check();
    lastValue        = currentValue;
    currentValue     = SensorData.currentReading();
    smokeDetected    = (currentValue > SMOKE_THRESHOLD);
    battery.use();
    return currentValue;
  }

  @Override
  public boolean status() {
    return smokeDetected;
  }

  @Override
  public void flipStatus() {
    smokeDetected = !smokeDetected;
  }

  @Override
  public void setStatus(boolean value) {
    smokeDetected = value;
  }
}


class SensorDataVariation {
  private static double[] readings = {
    0.1, 0.4, 0.0, 0.51, 0.5, 0.7, 0.0, 2.2, 1.0
  };
  private static int counter = 0;

  public static double currentReading() {
    int idx = counter++;
    if (counter >= readings.length) counter = 0;
    return readings[idx];
  }

  public static void reset() {
    counter = 0;
  }
}

class SGGE2 {
  public static void main(String[] args) {
    SensorData.reset();

    System.out.println("=== AtmosphericSensor Demo ===");
    AtmosphericSensorVariation atm = new AtmosphericSensorVariation(0.2);
    System.out.printf("Initial battery: %.2f%n", atm.getBatteryLevel());
    double atmReading = atm.takeNewReading();
    System.out.printf("takeNewReading() → %.2f%n", atmReading);
    System.out.printf("lastReading()    → %.2f%n", atm.lastReading());
    System.out.printf("Battery now      → %.2f%n", atm.getBatteryLevel());

    System.out.println("\n=== WaterSensor Demo ===");
    WaterSensorVariation water = new WaterSensorVariation(0.6);
    System.out.println("Initial flooding status: " + water.status());
    System.out.printf("Initial battery: %.2f%n", water.getBatteryLevel());
    double waterReading = water.takeNewReading();
    System.out.printf("takeNewReading() → %.2f%n", waterReading);
    System.out.printf("lastReading()    → %.2f%n", water.lastReading());
    System.out.println("Flooding now     → " + water.status());
    System.out.printf("Battery now      → %.2f%n", water.getBatteryLevel());
    water.flipStatus();
    System.out.println("After flipStatus(): flooding → " + water.status());
    water.setStatus(false);
    System.out.println("After setStatus(false): flooding → " + water.status());

    System.out.println("\n=== SmokeSensor Demo ===");
    SmokeSensorVariation smoke = new SmokeSensorVariation(0.8);
    System.out.println("Initial smokeDetected: " + smoke.status());
    System.out.printf("Initial battery: %.2f%n", smoke.getBatteryLevel());
    double smokeReading = smoke.takeNewReading();
    System.out.printf("takeNewReading() → %.2f%n", smokeReading);
    System.out.printf("lastReading()    → %.2f%n", smoke.lastReading());
    System.out.println("Smoke detected   → " + smoke.status());
    System.out.printf("Battery now      → %.2f%n", smoke.getBatteryLevel());
    smoke.flipStatus();
    System.out.println("After flipStatus(): smokeDetected → " + smoke.status());
    smoke.setStatus(true);
    System.out.println("After setStatus(true): smokeDetected → " + smoke.status());
  }
}
