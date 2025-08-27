enum Material {
  CLAY,
  CONCRETE,
  PLASTIC,
  SANDSTONE,
  STONE,
  CEMENT
}

class BuildingBrick {
  private Material material;
  private double length, width, height;

  public BuildingBrick(Material material, double length, double width, double height) {
    if (material == null || length <= 0 || width <= 0 || height <= 0)
      throw new IllegalArgumentException("Invalid material or dimensions");
    this.material = material;
    this.length = length;
    this.width = width;
    this.height = height;
  }

  public double getLength() { return length; }
  public double getWidth() { return width; }
  public double getHeight() { return height; }
  public Material getMaterial() { return material; }
  public double   getVolume()   { return length * width * height; }

  public double getSurfaceArea() {
    return 2 * (length * width + length * height + width * height);
  }

  public boolean canClickWith(BuildingBrick other) {
    if (other == null) return false;
    return this.material == other.material
      && Double.compare(this.length, other.length) == 0
      && Double.compare(this.width,  other.width)  == 0
      && Double.compare(this.height, other.height) == 0;
  }
}

class LegoPart extends BuildingBrick {
  private final String partId;
  private final int studCount;
  private final String color;

  public LegoPart(Material material,
                  String partId,
                  double length,
                  double width,
                  double height,
                  int studCount,
                  String color) {
    super(material, length, width, height);
    if (partId == null || partId.isBlank()) throw new IllegalArgumentException("partId required");
    if (studCount < 0)              throw new IllegalArgumentException("studCount must be ≥0");
    if (color == null || color.isBlank()) throw new IllegalArgumentException("color required");
    this.partId    = partId;
    this.studCount = studCount;
    this.color     = color;
  }

  public String getPartId()    { return partId; }
  public int    getStudCount() { return studCount; }
  public String getColor()     { return color; }

  public boolean fitsOnTopOf(LegoPart other) {
    if (other == null) return false;
    return this.getLength() <= other.getLength()
      && this.getWidth()  <= other.getWidth();
  }

  public boolean canAttachTo(LegoPart other) {
    return other != null
      && this.studCount == other.studCount
      && this.fitsOnTopOf(other);
  }

  public void attachTo(LegoPart other) {
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

public class RBBE2 {
  public static void main(String[] args) {
    LegoPart part1 = new LegoPart(
      Material.PLASTIC,
      "LP100",
      0.008,
      0.008,
      0.009,
      4,
      "Red"
    );
    LegoPart part2 = new LegoPart(
      Material.PLASTIC,
      "LP200",
      0.008,
      0.008,
      0.009,
      4,
      "Blue"
    );

    System.out.println("Volume of part1: " + part1.getVolume());
    System.out.println("part1 fits on part2? " + part1.fitsOnTopOf(part2));

    System.out.println("ID: "          + part1.getPartId());
    System.out.println("Studs: "       + part1.getStudCount());
    System.out.println("Color: "       + part1.getColor());
    System.out.println("Tagline: "     + part1.getMarketingTagline());
    System.out.println("Email subj: "  + part1.generateEmailSubject());
    System.out.println("Retail price: $"
      + part1.calculateRetailPrice(0.50, 100));
    System.out.println("Can attach? "  + part1.canAttachTo(part2));
    part1.attachTo(part2);

    System.out.println(part1);
    System.out.println(part2);

    BuildingBrick brick1 = new BuildingBrick(Material.CLAY, 0.2, 0.1, 0.05);
    System.out.println("Material: "      + brick1.getMaterial());
    System.out.println("Volume: "        + brick1.getVolume());
    System.out.println("Surface Area: "  + brick1.getSurfaceArea());
    System.out.println("Can click with itself? " + brick1.canClickWith(brick1));

    BuildingBrick brick2 = new BuildingBrick(Material.CONCRETE, 0.5, 0.1, 0.07);
    System.out.println("Material: "      + brick2.getMaterial());
    System.out.println("Volume: "        + brick2.getVolume());
    System.out.println("Surface Area: "  + brick2.getSurfaceArea());
    System.out.println("Can click with itself? " + brick2.canClickWith(brick2));
  }
}
