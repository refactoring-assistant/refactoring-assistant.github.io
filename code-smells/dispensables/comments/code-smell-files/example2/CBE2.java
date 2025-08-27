import java.util.ArrayList;
import java.util.List;

class RGBColor {
  private final int red;
  private final int green;
  private final int blue;

  private final int RGB_FULL = 255;
  private final double LUMINANCE_RED = 0.2126;
  private final double LUMINANCE_GREEN = 0.7152;
  private final double LUMINANCE_BLUE = 0.0722;
  private final double ADJUSTMENT = 0.05;

  public RGBColor(int red, int green, int blue) {
    // Should have the constructor of RGBColorBad should implement defensive programming to clamp the invalid values out.
    // RGB values: for each of the three components, their value should sit in between 0 and 255.
    // If not, throw an IllegalArgumentException.
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public String toHex() {
    return String.format("#%02X%02X%02X", red, green, blue);
  }

  public String toRGB() {
    return String.format("rgb(%d, %d, %d)", red, green, blue);
  }

  public RGBColor blend(RGBColor other) {
    return new RGBColor(
      (this.red + other.red)/2, (this.green + other.green)/2, (this.blue + other.blue)/2
    );
  }

  public double getContrastRatio(RGBColor other) {
    double luminance1 = (LUMINANCE_RED*red + LUMINANCE_GREEN*green + LUMINANCE_BLUE*blue) / RGB_FULL;
    double luminance2 = (LUMINANCE_RED*other.red + LUMINANCE_GREEN*other.green + LUMINANCE_BLUE*other.blue) / RGB_FULL;
    return (Math.max(luminance1, luminance2)+ADJUSTMENT) / (Math.min(luminance1, luminance2)+ADJUSTMENT);
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }
}

class ColorPalette {
  private List<RGBColor> colors;
  private final int RGB_FULL = 255;

  public ColorPalette() {
    this.colors = new ArrayList<>();
  }

  public void addRGBColor(RGBColor color) {
    this.colors.add(color);
  }

  public RGBColor findComplementary(RGBColor base) {
    return new RGBColor(RGB_FULL - base.getRed(),
      RGB_FULL - base.getGreen(),
      RGB_FULL - base.getBlue());
  }
}

public class CBE2 {
  public static void main(String[] args) {
    RGBColor red = new RGBColor(255, 0, 0);
    RGBColor blue = new RGBColor(0, 0, 255);

    RGBColor purple = red.blend(blue);
    System.out.println("Purple (HEX): " + purple.toHex());

    double contrast = red.getContrastRatio(blue);
    System.out.println("Contrast Ratio: " + contrast);

    ColorPalette palette = new ColorPalette();
    palette.addRGBColor(red);
    palette.addRGBColor(blue);
    palette.addRGBColor(purple);

    RGBColor complementary = palette.findComplementary(red);
    System.out.println("Complementary to red: " + complementary.toRGB());
  }
}
