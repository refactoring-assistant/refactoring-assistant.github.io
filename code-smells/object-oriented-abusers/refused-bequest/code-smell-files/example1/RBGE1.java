interface Vehicle {
    public void forward();

    public void reverse();

    public void turn(String direction);
}

interface GasVehicle extends Vehicle {
    public void refuel();

    public void replaceEngine(String newEngine);
}

class Car implements GasVehicle {
    private int fuelPercentage;
    private int distanceTravelled;
    private String direction;
    private String engine;

    public Car(String engine) {
        this.fuelPercentage = 100;
        this.distanceTravelled = 0;
        this.direction = "Forward";
        this.engine = engine;
    }

    @Override
    public void forward() {
        this.distanceTravelled += 10;
        this.fuelPercentage -= 1;
        System.out.println("New distance travelled: " + this.distanceTravelled);
        System.out.println("Current fuel percentage: " + this.fuelPercentage);
    }

    @Override
    public void reverse() {
        this.distanceTravelled -= 10;
        this.fuelPercentage -= 1;
        System.out.println("New distance travelled: " + this.distanceTravelled);
        System.out.println("Current fuel percentage: " + this.fuelPercentage);
    }

    @Override
    public void turn(String direction) {
        this.direction = direction;
        System.out.println("New direction: " + this.direction);
    }

    public void refuel() {
        if (this.fuelPercentage == 100) {
            System.out.println("Fuel is full");
        } else {
            this.fuelPercentage = 100;
        }
    }

    public void replaceEngine(String newEngine) {
        this.engine = newEngine;
        System.out.println("New engine: " + this.engine);
    }

}

class Bicycle implements Vehicle {

    private int distanceTravelled;
    private String direction;

    public Bicycle() {
        this.distanceTravelled = 0;
        this.direction = "Forward";
    }

    @Override
    public void forward() {
        this.distanceTravelled += 10;
        System.out.println("New distance travelled: " + this.distanceTravelled);
    }

    @Override
    public void reverse() {
        this.distanceTravelled -= 10;
        System.out.println("New distance travelled: " + this.distanceTravelled);
    }

    @Override
    public void turn(String direction) {
        this.direction = direction;
        System.out.println("New direction: " + this.direction);
    }
}

public class RBGE1 {

    public static void main(String[] args) {
        GasVehicle car = new Car("V6");
        Vehicle cycle = new Bicycle();
        car.forward();
        car.forward();
        car.forward();
        car.refuel();
        cycle.forward();
        cycle.forward();
        cycle.forward();
    }

}
