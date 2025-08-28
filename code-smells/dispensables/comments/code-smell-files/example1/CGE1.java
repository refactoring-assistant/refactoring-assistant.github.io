import java.util.HashMap;
import java.awt.image.BufferedImage;

/**
 * Represents an image buffer that stores pixel data in a 2D array format.
 * This class provides methods to access and manipulate the pixel data of an
 * image.
 */
class ImageBuffer {
    private final long[][] pixels;
    private final int width;
    private final int height;

    /**
     * Initializes a Buffer using an existing {@link BufferedImage}.
     * This constructor extracts the pixel data from the provided BufferedImage.
     *
     * @param image The BufferedImage to be stored in this buffer.
     *              This image's pixel data will be copied to the internal storage.
     */
    public ImageBuffer(BufferedImage image) {
        width = image.getWidth();
        height = image.getHeight();
        pixels = new long[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = image.getRGB(x, y);
            }
        }
    }

    /**
     * Initializes an empty Buffer with the specified dimensions.
     * The pixel data will not be initialized and will default to zero.
     *
     * @param width  The width of the image to be created.
     * @param height The height of the image to be created.
     */
    public ImageBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new long[width][height];
    }

    /**
     * Retrieves the RGB value of a pixel at the specified coordinates.
     *
     * @param x The x-coordinate of the pixel (horizontal position).
     * @param y The y-coordinate of the pixel (vertical position).
     * @return The RGB value of the pixel at the specified coordinates.
     * @throws IllegalArgumentException if the specified coordinates are out of
     *                                  bounds.
     */
    public long getRGB(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return pixels[x][y];
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
    }

    /**
     * Sets the RGB value of a pixel at the specified coordinates.
     *
     * @param x     The x-coordinate of the pixel (horizontal position).
     * @param y     The y-coordinate of the pixel (vertical position).
     * @param pixel The RGB value to be set for the pixel.
     * @throws IllegalArgumentException if the specified coordinates are out of
     *                                  bounds.
     */
    public void setRGB(int x, int y, long pixel) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[x][y] = pixel;
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
    }

    /**
     * Returns the width of the image.
     *
     * @return The width of the image in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the image.
     *
     * @return The height of the image in pixels.
     */
    public int getHeight() {
        return height;
    }
}

/**
 * Provides the method to alter image brightness
 */
class ImageModel {
    private final HashMap<String, ImageBuffer> loadedImages;

    public ImageModel() {
        this.loadedImages = new HashMap<>();
    }

    public ImageBuffer getImage(String name) {
        return loadedImages.get(name);
    }

    public void setImage(String key, ImageBuffer image) {
        loadedImages.put(key, image);
    }

    private long clamp(long value) {
        return Math.min(255, Math.max(0, value));
    }

    /**
     * Alters the brightness of the specified image.
     * Each pixel's RGB value is adjusted by a specified brightness factor.
     *
     * @param image      The original image to be brightened.
     * @param brightness The amount to adjust the brightness. Positive values
     *                   increase brightness, while negative values decrease it.
     * @return A new Buffer containing the brightened image.
     */
    public ImageBuffer alterBrightness(ImageBuffer image, float brightness) {
        int width = image.getWidth();
        int height = image.getHeight();

        ImageBuffer brightenedImage = new ImageBuffer(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                long rgb = image.getRGB(x, y);
                long newRGB = applyBrightnessToPixel(rgb, brightness);
                brightenedImage.setRGB(x, y, newRGB);
            }
        }

        return brightenedImage;
    }

    private long applyBrightnessToPixel(long rgb, float brightness) {

        long red = (rgb >> 16) & 0xFF;
        long green = (rgb >> 8) & 0xFF;
        long blue = rgb & 0xFF;

        red = clamp((int) (red + brightness));
        green = clamp((int) (green + brightness));
        blue = clamp((int) (blue + brightness));

        return (red << 16) | (green << 8) | blue;
    }

}