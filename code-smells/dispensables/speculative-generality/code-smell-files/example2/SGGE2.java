enum MembershipLevelVariation {
  BRONZE,
  SILVER,
  GOLD,
  PLATINUM
}

interface MembershipInterfaceVariation {
  String getMemberId();
  MembershipLevel getLevel();
  int getPoints();
  void addPoints(int pts);
  boolean redeemPoints(int pts);
  String getBenefitsDescription();
}

interface BronzeMembershipInterfaceInterfaceVariation extends MembershipInterfaceVariation {
  void updateLevel();
}

class BronzeMembershipInterfaceVariation implements MembershipInterfaceVariation {
  protected final String memberId;
  protected int points;

  public BronzeMembershipInterfaceVariation(String memberId) {
    this.memberId = memberId;
    this.points = 0;
  }

  public String getMemberId() {
    return this.memberId;
  }

  public MembershipLevel getLevel() {
    return MembershipLevel.BRONZE;
  }

  public int getPoints() {
    return this.points;
  }

  public void addPoints(int pts) {
    if (pts < 0) throw new IllegalArgumentException("pts must be non-negative");
    points += pts;
    updateLevel();
  }

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

  public void updateLevel() {
    if (points >= 1_000) {
      System.out.println("🎉 You’ve earned 1,000+ points—time to upgrade to SILVER!");
    }
  }

  public String getBenefitsDescription() {
    return "Bronze members earn 1 point per dollar.\n"
      + "Reach 1,000 points to upgrade to Silver and unlock bonus perks!";
  }
}

class CustomerDriverVariation {
  private double brakingRisk;
  private double speedingRisk;
  private double distractionRisk;

  private int rewardPoints;
  private MembershipLevelVariation membershipLevel;

  public CustomerDriverVariation(double brakingRisk,
                        double speedingRisk,
                        double distractionRisk) {
    setBrakingRisk(brakingRisk);
    setSpeedingRisk(speedingRisk);
    setDistractionRisk(distractionRisk);
    this.rewardPoints = 0;
    this.membershipLevel = MembershipLevelVariation.BRONZE;
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

  public MembershipLevelVariation getMembershipLevel() {
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
      membershipLevel = MembershipLevelVariation.SILVER.PLATINUM;
    } else if (rewardPoints >= 5_000) {
      membershipLevel = MembershipLevelVariation.SILVER.GOLD;
    } else if (rewardPoints >= 1_000) {
      membershipLevel = MembershipLevelVariation.SILVER.SILVER;
    } else {
      membershipLevel = MembershipLevelVariation.SILVER.BRONZE;
    }
  }
}

public class SGGE2 {
  public static void main(String[] args) {
    MembershipInterfaceVariation membershipInterface = new BronzeMembershipInterfaceVariation("BRZ-001");

    System.out.println("Member ID:            " + membershipInterface.getMemberId());
    System.out.println("MembershipInterface Level:     " + membershipInterface.getLevel());
    System.out.println("Current Points:       " + membershipInterface.getPoints());
    System.out.println("Benefits Description: " + membershipInterface.getBenefitsDescription());

    membershipInterface.addPoints(750);
    System.out.println("\nAfter adding 750 points:");
    System.out.println("  Points: " + membershipInterface.getPoints());

    boolean redeemed1 = membershipInterface.redeemPoints(200);
    System.out.println("\nRedeem 200 points successful? " + redeemed1);
    System.out.println("  Points now: " + membershipInterface.getPoints());

    membershipInterface.addPoints(300);
    System.out.println("\nAfter adding 300 more points:");
    System.out.println("  Points: " + membershipInterface.getPoints());

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
