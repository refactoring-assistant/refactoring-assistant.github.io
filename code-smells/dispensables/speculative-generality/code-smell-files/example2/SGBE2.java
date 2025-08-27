import java.util.Objects;

enum MembershipLevel {
  BRONZE,
  SILVER,
  GOLD,
  PLATINUM
}

interface MembershipInterface {
  String getMemberId();
  MembershipLevel getLevel();
  int getPoints();
  void addPoints(int pts);
  boolean redeemPoints(int pts);
  String getBenefitsDescription();
}

interface BaseMembershipInterfaceInterface extends MembershipInterface {
  void updateLevel();
}

abstract class BaseMembership implements BaseMembershipInterfaceInterface {
  protected final String memberId;
  protected int points;

  protected BaseMembership(String memberId) {
    this.memberId = Objects.requireNonNull(memberId, "memberId must not be null");
    this.points = 0;
  }

  @Override
  public String getMemberId() {
    return this.memberId;
  }

  @Override
  public int getPoints() {
    return this.points;
  }

  public abstract void addPoints(int pts);
  public abstract boolean redeemPoints(int pts);
  public abstract MembershipLevel getLevel();
  public abstract String getBenefitsDescription();
  public abstract void updateLevel();
}

class BronzeMembership extends BaseMembership {
  protected BronzeMembership(String memberId) {
    super(memberId);
  }

  @Override
  public void addPoints(int pts) {
    if (pts < 0) throw new IllegalArgumentException("pts must be non-negative");
    points += pts;
    updateLevel();
  }

  @Override
  public boolean redeemPoints(int pts) {
    if (pts < 0) {
      throw new IllegalArgumentException("pts must be non-negative");
    }
    if (pts > points) {
      return false;
    }
    points -= pts;
    updateLevel();
    return true;
  }

  @Override
  public void updateLevel() {
    if (points >= 1_000) {
      System.out.println("🎉 Congratulations! You’ve earned enough points to upgrade to Silver membership.");
    }
  }

  @Override
  public MembershipLevel getLevel() {
    return MembershipLevel.BRONZE;
  }

  @Override
  public String getBenefitsDescription() {
    return "";
  }
}

class SilverMembership extends BronzeMembership {
  protected SilverMembership(String memberId) {
    super(memberId);
  }
}

class GoldMembership extends SilverMembership {
  protected GoldMembership(String memberId) {
    super(memberId);
  }
}

class PlatinumMembership extends GoldMembership {
  protected PlatinumMembership(String memberId) {
    super(memberId);
  }
}

class CustomerDriver {
  private double brakingRisk;
  private double speedingRisk;
  private double distractionRisk;

  private int rewardPoints;
  private MembershipLevel membershipLevel;

  public CustomerDriver(double brakingRisk,
                        double speedingRisk,
                        double distractionRisk) {
    setBrakingRisk(brakingRisk);
    setSpeedingRisk(speedingRisk);
    setDistractionRisk(distractionRisk);
    this.rewardPoints = 0;
    this.membershipLevel = MembershipLevel.BRONZE;
  }

  public void setBrakingRisk(double v)     { validateFactor(v, "brakingRisk");     this.brakingRisk     = v; }
  public void setSpeedingRisk(double v)    { validateFactor(v, "speedingRisk");    this.speedingRisk    = v; }
  public void setDistractionRisk(double v) { validateFactor(v, "distractionRisk"); this.distractionRisk = v; }

  public double getBrakingRisk()     { return brakingRisk; }
  public double getSpeedingRisk()    { return speedingRisk; }
  public double getDistractionRisk() { return distractionRisk; }

  private void validateFactor(double value, String name) {
    if (value < 0.0 || value > 1.0) {
      throw new IllegalArgumentException(name + " must be between 0.0 and 1.0");
    }
  }

  public double calculateOverallRisk() {
    return (brakingRisk + speedingRisk + distractionRisk) / 3.0;
  }

  public boolean isHighRisk(double threshold) {
    return calculateOverallRisk() >= threshold;
  }

  public int getRewardPoints() {
    return rewardPoints;
  }

  public MembershipLevel getMembershipLevel() {
    return membershipLevel;
  }

  public void addRewardPoints(int basePoints) {
    if (basePoints < 0) throw new IllegalArgumentException("basePoints must be non-negative");
    int earned = (int) Math.round(basePoints * (1.0 - calculateOverallRisk()));
    rewardPoints += earned;
    updateMembershipLevel();
  }

  public boolean redeemRewardPoints(int points) {
    if (points < 0) throw new IllegalArgumentException("points must be non-negative");
    if (points > rewardPoints) return false;
    rewardPoints -= points;
    updateMembershipLevel();
    return true;
  }

  private void updateMembershipLevel() {
    if (rewardPoints >= 10_000) {
      membershipLevel = MembershipLevel.SILVER.PLATINUM;
    } else if (rewardPoints >= 5_000) {
      membershipLevel = MembershipLevel.SILVER.GOLD;
    } else if (rewardPoints >= 1_000) {
      membershipLevel = MembershipLevel.SILVER.SILVER;
    } else {
      membershipLevel = MembershipLevel.SILVER.BRONZE;
    }
  }
}

public class SGBE2 {
  public static void main(String[] args) {
    MembershipInterface membership = new BronzeMembership("BRZ-001");

    System.out.println("Member ID:            " + membership.getMemberId());
    System.out.println("MembershipInterface Level:     " + membership.getLevel());
    System.out.println("Current Points:       " + membership.getPoints());
    System.out.println("Benefits Description: " + membership.getBenefitsDescription());

    membership.addPoints(750);
    System.out.println("\nAfter adding 750 points:");
    System.out.println("  Points: " + membership.getPoints());

    boolean redeemed1 = membership.redeemPoints(200);
    System.out.println("\nRedeem 200 points successful? " + redeemed1);
    System.out.println("  Points now: " + membership.getPoints());

    membership.addPoints(300);
    System.out.println("\nAfter adding 300 more points:");
    System.out.println("  Points: " + membership.getPoints());

    System.out.println("\n=== CustomerDriver Demo ===");
    CustomerDriver driver = new CustomerDriver(0.2, 0.3, 0.1);

    driver.setBrakingRisk(0.15);
    driver.setSpeedingRisk(0.25);
    driver.setDistractionRisk(0.05);

    System.out.printf("Braking Risk:     %.2f%n", driver.getBrakingRisk());
    System.out.printf("Speeding Risk:    %.2f%n", driver.getSpeedingRisk());
    System.out.printf("Distraction Risk: %.2f%n", driver.getDistractionRisk());

    System.out.printf("Overall Risk:     %.2f%n", driver.calculateOverallRisk());
    System.out.println("Is high risk ≥0.5? " + driver.isHighRisk(0.5));

    driver.addRewardPoints(1_000);
    System.out.println("\nAfter awarding 1,000 base points:");
    System.out.println("  Reward Points:     " + driver.getRewardPoints());
    System.out.println("  MembershipInterface Level:  " + driver.getMembershipLevel());

    boolean driverRedeem = driver.redeemRewardPoints(300);
    System.out.println("\nRedeem 300 reward points successful? " + driverRedeem);
    System.out.println("  Remaining Points:  " + driver.getRewardPoints());
    System.out.println("  MembershipInterface Level:  " + driver.getMembershipLevel());
  }
}
