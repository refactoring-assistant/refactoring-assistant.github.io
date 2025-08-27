import java.util.ArrayList;
import java.util.List;

class RGBColorVariation {
  private final int red;
  private final int green;
  private final int blue;

  private final int RGB_FULL = 255;
  private final double LUMINANCE_RED = 0.2126;
  private final double LUMINANCE_GREEN = 0.7152;
  private final double LUMINANCE_BLUE = 0.0722;
  private final double ADJUSTMENT = 0.05;

  public RGBColorVariation(int red, int green, int blue) {
    if (red<0 || red>255 || green<0 || green>255 || blue<0 || blue>255) {
      throw new IllegalArgumentException("Invalid RGB values (0-255)");
    }
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

  public RGBColorVariation blend(RGBColorVariation other) {
    return new RGBColorVariation(
      (this.red + other.getRed())/2, (this.green + other.getGreen())/2, (this.blue + other.getBlue())/2
    );
  }

  public double getContrastRatio(RGBColorVariation other) {
    double luminance1 = (LUMINANCE_RED*red + LUMINANCE_GREEN*green + LUMINANCE_BLUE*blue) / RGB_FULL;
    double luminance2 = (LUMINANCE_RED*other.getRed() + LUMINANCE_GREEN*other.getGreen() + LUMINANCE_BLUE*other.getBlue()) / RGB_FULL;
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

class ColorPaletteVariation {
  private List<RGBColorVariation> colors;
  private final int RGB_FULL = 255;

  public ColorPaletteVariation() {
    this.colors = new ArrayList<>();
  }

  public void addRGBColor(RGBColorVariation color) {
    colors.add(color);
  }

  public RGBColorVariation findComplementary(RGBColorVariation base) {
    return new RGBColorVariation(RGB_FULL - base.getRed(),
      RGB_FULL - base.getGreen(),
      RGB_FULL - base.getBlue());
  }
}

public class CGE2 {
  public static void main(String[] args) {
    RGBColorVariation red = new RGBColorVariation(255, 0, 0);
    RGBColorVariation blue = new RGBColorVariation(0, 0, 255);

    RGBColorVariation purple = red.blend(blue);
    System.out.println("Purple (HEX): " + purple.toHex());

    double contrast = red.getContrastRatio(blue);
    System.out.println("Contrast Ratio: " + contrast);

    ColorPaletteVariation palette = new ColorPaletteVariation();
    palette.addRGBColor(red);
    palette.addRGBColor(blue);
    palette.addRGBColor(purple);

    RGBColorVariation complementary = palette.findComplementary(red);
    System.out.println("Complementary to red: " + complementary.toRGB());
  }
}
