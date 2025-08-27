enum MaterialVariation {
  CLAY,
  CONCRETE,
  PLASTIC,
  SANDSTONE,
  STONE,
  CEMENT
}

class BlockVariation {
  private final double length;
  private final double width;
  private final double height;

  public BlockVariation(double length, double width, double height) {
    if (length <= 0 || width <= 0 || height <= 0) {
      throw new IllegalArgumentException("All dimensions must be positive");
    }
    this.length = length;
    this.width = width;
    this.height = height;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public double getVolume() {
    return length * width * height;
  }

  public double getSurfaceArea() {
    return 2 * (length * width + length * height + width * height);
  }
}

class BuildingBrickVariation extends BlockVariation {
  private final MaterialVariation material;

  public BuildingBrickVariation(MaterialVariation material, double length, double width, double height) {
    super(length, width, height);
    if (material == null) {
      throw new IllegalArgumentException("material must not be null");
    }
    this.material = material;
  }

  public MaterialVariation getMaterial() {
    return material;
  }

  public boolean canClickWith(BuildingBrickVariation other) {
    if (other == null) return false;
    return this.material == other.material
      && Double.compare(getLength(), other.getLength()) == 0
      && Double.compare(getWidth(),  other.getWidth())  == 0
      && Double.compare(getHeight(), other.getHeight()) == 0;
  }
}

class LegoPartVariation extends BlockVariation {
  private final String partId;
  private final int studCount;
  private final String color;

  public LegoPartVariation(String partId,
                  double length,
                  double width,
                  double height,
                  int studCount,
                  String color) {
    super(length, width, height);
    if (partId == null || partId.isBlank())
      throw new IllegalArgumentException("partId required");
    if (studCount < 0)
      throw new IllegalArgumentException("studCount must be ≥0");
    if (color == null || color.isBlank())
      throw new IllegalArgumentException("color required");
    this.partId    = partId;
    this.studCount = studCount;
    this.color     = color;
  }

  public String getPartId()    { return partId; }
  public int    getStudCount() { return studCount; }
  public String getColor()     { return color; }

  public boolean fitsOnTopOf(LegoPartVariation other) {
    if (other == null) return false;
    return this.getLength() <= other.getLength()
      && this.getWidth()  <= other.getWidth();
  }

  public boolean canAttachTo(LegoPartVariation other) {
    return other != null
      && this.studCount == other.studCount
      && fitsOnTopOf(other);
  }

  public void attachTo(LegoPartVariation other) {
    if (!canAttachTo(other)) {
      throw new IllegalStateException(
        partId + " cannot attach to " + (other == null ? "null" : other.partId));
    }
    System.out.println("Attached " + partId + " onto " + other.partId);
  }

  public String getMarketingTagline() {
    return String.format(
      "Unleash creativity with %s – %d studs, vibrant %s!",
      partId, studCount, color
    );
  }

  public double calculateRetailPrice(double costPrice, double profitMarginPercent) {
    if (costPrice < 0 || profitMarginPercent < 0)
      throw new IllegalArgumentException("Costs and margins must be non-negative");
    return costPrice * (1 + profitMarginPercent / 100.0);
  }

  public String generateEmailSubject() {
    return String.format(
      "New Arrival: %s in %s – %d-stud fun awaits!",
      partId, color, studCount
    );
  }
}

public class RBGE2 {
  public static void main(String[] args) {
    BlockVariation block = new BlockVariation(1.0, 2.0, 3.0);
    System.out.println("Block length: "      + block.getLength());
    System.out.println("Block width: "       + block.getWidth());
    System.out.println("Block height: "      + block.getHeight());
    System.out.println("Block volume: "      + block.getVolume());
    System.out.println("Block surface area: " + block.getSurfaceArea());

    BuildingBrickVariation brick1 = new BuildingBrickVariation(MaterialVariation.CLAY, 0.5, 0.3, 0.2);
    BuildingBrickVariation brick2 = new BuildingBrickVariation(MaterialVariation.CONCRETE, 0.5, 0.3, 0.2);
    System.out.println("Brick1 material: " + brick1.getMaterial());
    System.out.println("Can brick1 click with brick2? " + brick1.canClickWith(brick2));

    LegoPartVariation part1 = new LegoPartVariation("LP001", 0.04, 0.02, 0.01, 4, "Red");
    LegoPartVariation part2 = new LegoPartVariation("LP002", 0.04, 0.02, 0.01, 4, "Blue");
    System.out.println("Part1 ID: "          + part1.getPartId());
    System.out.println("Part1 studs: "       + part1.getStudCount());
    System.out.println("Part1 color: "       + part1.getColor());
    System.out.println("Part1 volume: "      + part1.getVolume());
    System.out.println("Part1 surface area: " + part1.getSurfaceArea());
    System.out.println("Part1 fits on part2? " + part1.fitsOnTopOf(part2));
    System.out.println("Part1 can attach to part2? " + part1.canAttachTo(part2));
    part1.attachTo(part2);
    System.out.println("Tagline: "           + part1.getMarketingTagline());
    System.out.println("Retail price: $"     + part1.calculateRetailPrice(0.50, 100));
    System.out.println("Email subject: "     + part1.generateEmailSubject());
  }
}
