class Shipment {
    private final int EARTHRADIUS;

    public double calculateDistance(double originLat, double originLong, double destLat, double destLong) {
        double p = Math.PI / 180;
        double a = 0.5 - Math.cos((destLat - originLat) * p) / 2
                + Math.cos(originLat * p) * Math.cos(destLat * p)
                        * (1 - Math.cos((destLong - originLong) * p)) / 2;
        return 2 * this.EARTHRADIUS * Math.asin(Math.sqrt(a));
    }

    public Shipment() {
        this.EARTHRADIUS = 6371;
    }
}

public class LPLBE1 {
    public static void main(String[] args) {
        Shipment cst = new Shipment();
        System.out.println(cst.calculateDistance(42.3555, 71.0565, 40.7128, 74.0060));
    }

}
