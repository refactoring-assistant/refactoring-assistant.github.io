import java.util.List;
import java.util.ArrayList;

class Passenger {
    private String name;
    private List<String> items;
    private String flightNumber;
    private boolean boarded;

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

    public List<String> getItems() {
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

    private boolean securityCheck() {
        for (String invalid : INVALIDITEMS) {
            for (String item : passenger.getItems()) {
                if (item.equals(invalid)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean noFlyListCheck() {
        for (String noflyName : NOFLYLIST) {
            if (noflyName.equals(this.passenger.getName())) {
                return false;
            }
        }
        return true;
    }

    private boolean passengerCanBoard() {
        for (String currentFlight : CURRENTFLIGHTS) {
            if (currentFlight.equals(this.passenger.getFlightNumber())) {
                this.passenger.boardFlight();
                return true;
            }
        }
        return false;
    }

    public void boardAirplane() {
        if (securityCheck()) {
            System.out.println("Security Check Passed Successfully");
        } else {
            throw new Error("Security Check Failed");
        }

        if (noFlyListCheck()) {
            System.out.println("Passenger is safe to fly");
        } else {
            throw new Error("Passenger is on the No Fly List");
        }

        if (passengerCanBoard()) {
            System.out.println("Passenger has boarded the flight");
        } else {
            throw new Error("Flight is not ready to be boarded");
        }

    }
}

public class LMGE1 {
    public static void main(String[] args) {
        Passenger john = new Passenger("John Gruber", "AC102");
        john.addItem("Knives");
        john.addItem("Teddy bear");
        AirportTerminal at = new AirportTerminal(john);
        at.boardAirplane();
    }
}
