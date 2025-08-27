interface ISensor {
  double takeNewReading();
  double lastReading();
}

interface IDiscreteSensor extends ISensor {
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

abstract class Sensor implements ISensor {
  protected double lastValue;
  protected double batteryLevel;

  protected static final double MIN_BATTERY_LEVEL = 0.1;
  protected static final double DRAIN_PER_USE     = 0.05;

  public Sensor(double initialValue, double batteryLevel) {
    this.lastValue    = initialValue;
    this.batteryLevel = batteryLevel;
  }

  public Sensor(double initialValue) {
    this(initialValue, 1.0);
  }

  public Sensor() {
    this(0, 1.0);
  }

  @Override
  public double lastReading() {
    return lastValue;
  }

  public double getBatteryLevel() {
    return batteryLevel;
  }
}

class AtmosphericSensor extends Sensor {
  private double currentValue;

  public AtmosphericSensor(double value, double batteryLevel) {
    super(value, batteryLevel);
    this.currentValue = value;
  }

  public AtmosphericSensor(double value) {
    super(value);
    this.currentValue = value;
  }

  public AtmosphericSensor() {
    super();
    this.currentValue = 0;
  }

  @Override
  public double takeNewReading() {
    if (batteryLevel < MIN_BATTERY_LEVEL) {
      throw new IllegalStateException(
        "Cannot take reading: battery too low (" + batteryLevel + ")"
      );
    }

    batteryLevel = Math.max(0.0, batteryLevel - DRAIN_PER_USE);
    lastValue    = currentValue;
    currentValue = SensorData.currentReading();
    return currentValue;
  }
}

class WaterSensor extends Sensor implements IDiscreteSensor {
  private double currentValue;
  private boolean flooding;

  public WaterSensor(double value, double batteryLevel) {
    super(value, batteryLevel);
    this.currentValue = value;
    this.flooding     = value > 0.5;
  }

  public WaterSensor(double value) {
    super(value);
    this.currentValue = value;
    this.flooding     = value > 0.5;
  }

  public WaterSensor() {
    super();
    this.currentValue = 0;
    this.flooding     = false;
  }

  @Override
  public double takeNewReading() {
    if (batteryLevel < MIN_BATTERY_LEVEL) {
      throw new IllegalStateException(
        "Cannot take reading: battery too low (" + batteryLevel + ")"
      );
    }

    batteryLevel = Math.max(0.0, batteryLevel - DRAIN_PER_USE);
    lastValue    = currentValue;
    currentValue = SensorData.currentReading();
    flooding     = currentValue > 0.5;
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

class SmokeSensor extends Sensor implements IDiscreteSensor {
  private double currentValue;
  private boolean smokeDetected;

  private static final double SMOKE_THRESHOLD = 0.7;

  public SmokeSensor(double value, double batteryLevel) {
    super(value, batteryLevel);
    this.currentValue  = value;
    this.smokeDetected = value > SMOKE_THRESHOLD;
  }

  public SmokeSensor(double value) {
    super(value);
    this.currentValue  = value;
    this.smokeDetected = value > SMOKE_THRESHOLD;
  }

  public SmokeSensor() {
    super();
    this.currentValue  = 0;
    this.smokeDetected = false;
  }

  @Override
  public double takeNewReading() {
    if (batteryLevel < MIN_BATTERY_LEVEL) {
      throw new IllegalStateException(
        "Cannot take reading: battery too low (" + batteryLevel + ")"
      );
    }

    batteryLevel    = Math.max(0.0, batteryLevel - DRAIN_PER_USE);
    lastValue       = currentValue;
    currentValue    = SensorData.currentReading();
    smokeDetected   = currentValue > SMOKE_THRESHOLD;
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

class SensorData {
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

class SGBE2 {
  public static void main(String[] args) {
    SensorData.reset();

    System.out.println("=== AtmosphericSensor Demo ===");
    AtmosphericSensor atm = new AtmosphericSensor(0.2);
    System.out.printf("Initial battery: %.2f%n", atm.getBatteryLevel());
    double atmReading = atm.takeNewReading();
    System.out.printf("takeNewReading() → %.2f%n", atmReading);
    System.out.printf("lastReading()    → %.2f%n", atm.lastReading());
    System.out.printf("Battery now      → %.2f%n", atm.getBatteryLevel());

    System.out.println("\n=== WaterSensor Demo ===");
    WaterSensor water = new WaterSensor(0.6);
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
    SmokeSensor smoke = new SmokeSensor(0.8);
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