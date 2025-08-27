import java.util.Set;

class BlackFriday {
  private final String marketSector;
  private double discount;
  private double spending;
  private boolean isPromotionActive;
  private int numberOfAvailableCoupons;
  private int couponCode;
  private int consumerID;

  private final double TECH_DISCOUNT = 0.30;
  private final double FASHION_DISCOUNT = 0.50;
  private final double FOOD_DISCOUNT = 0.10;
  private static final Set<Integer> VALID_COUPON_CODES = Set.of(12345, 67890, 11111);

  public BlackFriday(String marketSector, int numberOfAvailableCoupons) {
    if (marketSector == null || marketSector.isEmpty()) {
      throw new IllegalArgumentException("Market sector must not be null or empty!");
    }

    this.marketSector = marketSector;
    this.discount = 0.0;
    this.spending = 0.0;
    this.isPromotionActive = false;
    this.numberOfAvailableCoupons = numberOfAvailableCoupons;
  }

  public void setSpending(double spending) {
    this.spending = spending;
  }

  public boolean couponAssigned() {
    return VALID_COUPON_CODES.contains(this.couponCode);
  }

  public double getConsumerID() {
    return consumerID;
  }

  public void setConsumerID(int consumerID) {
    this.consumerID = consumerID;
  }

  public void activatePromotion(boolean couponVerified, boolean IDVerified, double spending, int numberOfAvailableCoupons) {
    this.spending = spending;
    this.numberOfAvailableCoupons = numberOfAvailableCoupons;

    if (couponVerified && IDVerified && spending >= 100.0 && numberOfAvailableCoupons > 0) {
      isPromotionActive = true;

      if (marketSector.equals("Tech")) {
        discount = TECH_DISCOUNT;
      } else if (marketSector.equals("Fashion")) {
        discount = FASHION_DISCOUNT;
      } else if (marketSector.equals("Food")) {
        discount = FOOD_DISCOUNT;
      } else {
        discount = 0.05;
      }

      this.numberOfAvailableCoupons--;
    } else {
      isPromotionActive = false;
      discount = 0.0;
    }
  }
}

public class LPLBE2 {
  public static void main(String[] args) {
    BlackFriday promo = new BlackFriday("Fashion", 3);

    promo.setSpending(200.0);
    promo.setConsumerID(55555);
    int userCouponCode = 12345;
    double userIDInput = 55555;

    try {
      var field = promo.getClass().getDeclaredField("couponCode");
      field.setAccessible(true);
      field.setInt(promo, userCouponCode);
    } catch (Exception e) {
      e.printStackTrace();
    }

    boolean couponVerified = promo.couponAssigned();
    boolean idVerified = promo.getConsumerID() == userIDInput;
    promo.activatePromotion(couponVerified, idVerified, 200.0, 3);

    if (couponVerified && idVerified) {
      System.out.println("Promotion activated successfully.");
    } else {
      System.out.println("Promotion activation failed.");
    }
  }
}
