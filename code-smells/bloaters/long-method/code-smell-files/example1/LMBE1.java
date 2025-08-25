import java.util.ArrayList;

class Passenger {
    String name;
    ArrayList<String> items;
    String flightNumber;
    boolean boarded;

    public Passenger(String name, String flightNumber) {
        this.items = new ArrayList<String>();
        this.name = name;
        this.flightNumber = flightNumber;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String itemToRemove) {
        for (String item : this.items) {
            if (item.equals(itemToRemove)) {
                this.items.remove(itemToRemove);
            }
        }
    }

    public ArrayList<String> getItems() {
        return this.items;
    }

    public String getName() {
        return this.name;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void boardFlight() {
        this.boarded = true;
    }

    public boolean getBoardedStatus() {
        return this.boarded;
    }
}

class AirportTerminal {

    String[] INVALIDITEMS;
    String[] NOFLYLIST;
    String[] CURRENTFLIGHTS;
    Passenger passenger;

    public AirportTerminal(Passenger passenger) {
        this.passenger = passenger;
        this.INVALIDITEMS = new String[] { "Arms", "Knives", "Liquids" };
        this.NOFLYLIST = new String[] { "Joseph Carn", "Mel Gibbs", "Hali Burton" };
        this.CURRENTFLIGHTS = new String[] { "AC102", "BD309", "DL089" };
    }

    public void boardAirplane() {

        for (String invalid : INVALIDITEMS) {
            for (String item : passenger.getItems()) {
                if (item.equals(invalid)) {
                    throw new Error("Invalid Item Detected");
                }
            }
        }
        System.out.println("Finished Security Check");

        for (String noflyName : NOFLYLIST) {
            if (noflyName.equals(this.passenger.getName())) {
                throw new Error("This passenger is on the No Fly List");
            }
        }
        System.out.println("Passenger can proceed to their gate");

        for (String currentFlight : CURRENTFLIGHTS) {
            if (currentFlight.equals(this.passenger.getFlightNumber())) {
                System.out.println("Flight has begun boarding");
                this.passenger.boardFlight();
            }
        }

        if (!this.passenger.getBoardedStatus()) {
            throw new Error("Flight isn't ready to board");
        }

    }
}

public class LMBE1 {
    public static void main(String[] args) {
        Passenger john = new Passenger("John Gruber", "AC102");
        john.addItem("Knives");
        john.addItem("Teddy bear");
        AirportTerminal at = new AirportTerminal(john);
        at.boardAirplane();
    }
}
