import java.util.LinkedList;
import java.util.Queue;

class Order {
    private String item;
    private String destination;
    private boolean isDelivered;

    public Order(String item, String destination) {
        this.item = item;
        this.destination = destination;
        this.isDelivered = false;
    }

    public String getOrderDetails() {
        return "Item: " + this.item + " to: " + this.destination + "\nDelivered: " + this.isDelivered;
    }

    public String getDestination() {
        return destination;
    }

    public void markOrderDelivered() {
        this.isDelivered = true;
    }

    public boolean getDeliveredStatus() {
        return this.isDelivered;
    }
}

class Warehouse {
    private String address;
    private String owningEntity;
    private Queue<Order> orders;

    public Warehouse(String address, String owningEntity) {
        this.address = address;
        this.owningEntity = owningEntity;
        this.orders = new LinkedList<>();
    }

    public void addOrder(int orderId, Order newOrder) {
        orders.add(newOrder);
    }

    public Order getLatestOrder() {
        return orders.peek();
    }

    public String getWarehouseDetails() {
        return "Owned by " + this.owningEntity + "located at " + this.address;
    }

    public int getPendingOrderSize() {
        return orders.size();
    }

    public void checkAndEmptyWarehouse() throws Error {
        for (Order order : orders) {
            if (!order.getDeliveredStatus()) {
                throw new Error("Warehouse is not empty!");
            }
        }
        orders.clear();
    }
}

class Distributor {
    private String owner;
    private String address;
    private Warehouse warehouse;

    public Distributor(String owner, String address, Warehouse warehouse) {
        this.owner = owner;
        this.address = address;
        this.warehouse = warehouse;
    }

    public Order getLatestOrder() {
        return this.warehouse.getLatestOrder();
    }

    public String getDistributorDetails() {
        return "Owned by " + this.owner + " located at " + this.address;
    }

    public String getLatestOrderDestination() {
        return this.warehouse.getLatestOrder().getDestination();
    }

}

class Vehicle {
    private String vehicleNumber;
    private String model;
    private Distributor distributor;

    public Vehicle(Distributor distributor, String vehicleNumber, String model) {
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.distributor = distributor;
    }

    public String getVehicleDetails() {
        return this.vehicleNumber + "\n" + this.model;
    }

    public void stationVehicle() {
        System.out.println(this.vehicleNumber + " is now stationed");
    }

    public void startVehicle() {
        System.out.println("Vehicle has been started");
    }

    public void getOrderRoute() {
        String destination = distributor.getLatestOrderDestination();
        System.out.println(vehicleNumber + " inbound to " + destination);
    }

}

public class MCGE1 {
    public static void main(String[] args) {
        Order boots = new Order("Timberlands", "1191 Boylston St");
        Order hat = new Order("Red Sox Cap", "1193 Boylston St");
        Warehouse fenway = new Warehouse("1203 Akron St", "Fenway Goods Storage");
        fenway.addOrder(1, boots);
        fenway.addOrder(2, hat);
        Distributor redSox = new Distributor("Red Sox Co.", "42 Chanice Blvd", fenway);
        Vehicle jeep = new Vehicle(redSox, "9XJ3F", "Mercedes GLX");
        jeep.getOrderRoute();
    }
}
