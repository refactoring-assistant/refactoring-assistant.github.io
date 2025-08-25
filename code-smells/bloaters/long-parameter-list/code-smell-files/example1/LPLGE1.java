class Shipment {
    private final double VEHICLESPEEDKMPH;
    private final int EARTHRADIUS;
    LocationCoordinates originCity;
    LocationCoordinates destCity;

    public double calculateDistanceFast() {
        double p = Math.PI / 180;
        double a = 0.5 - Math.cos((destCity.getLatitude() - originCity.getLatitude()) * p) / 2
                + Math.cos(originCity.getLatitude() * p) * Math.cos(destCity.getLatitude() * p)
                        * (1 - Math.cos((destCity.getLongitude() - originCity.getLongitude()) * p)) / 2;
        return 2 * this.EARTHRADIUS * Math.asin(Math.sqrt(a));
    }

    public double calculateTimeInHours() {
        return calculateDistanceFast() / this.VEHICLESPEEDKMPH;
    }

    public Shipment(LocationCoordinates city1, LocationCoordinates city2) {
        this.EARTHRADIUS = 6371;
        this.VEHICLESPEEDKMPH = 55.4;
        this.originCity = city1;
        this.destCity = city2;
    }
}

class LocationCoordinates {
    private double longitude;
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocationCoordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getRelativePositionToPoles(String direction) {
        if (direction.equals("North")) {
            return 90 - latitude;
        } else if (direction.equals("South")) {
            return 90 + latitude;
        } else {
            return 181;
        }
    }
}

public class LPLGE1 {
    public static void main(String[] args) {
        LocationCoordinates boston = new LocationCoordinates(42.3555, 71.0565);
        LocationCoordinates newYork = new LocationCoordinates(40.7128, 74.0060);
        Shipment cst = new Shipment(boston, newYork);
        System.out.println(cst.calculateTimeInHours());
        System.out.println(boston.getRelativePositionToPoles("North"));
    }

}
