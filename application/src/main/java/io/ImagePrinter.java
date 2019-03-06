
package io;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Class for printing 2D noise arrays into BufferedImages or saving them into PNG files.
 */
public class ImagePrinter {
    private int fileNameCounter;
    private boolean spuge;

    public ImagePrinter() {
        fileNameCounter = 1;
        this.spuge = false;
    }

    /**
     * Sets a boolean determining whether to generate grayscale or spugedelic images.
     */
    public void setSpuge(boolean spuge) {
        this.spuge = spuge;
    }

    /**
     * Returns a BufferedImage representing the given noise array.
     */
    public BufferedImage print(double[][] noise) {
        if (!spuge) {
            return createImage(noise);
        } else {
            return createSpugeImage(noise);
        }
    }

    /**
     * Saves a PNG image representing the noise array into the application's root folder.
     */
    public void printToFile(double[][] noise) {
        BufferedImage img = print(noise);

        try {
            File directory = new File("./noisegen-images/");
            if (!directory.exists()) {
                directory.mkdir();
            }

            ImageIO.write(img, "PNG", new File("./noisegen-images/image" + fileNameCounter + ".png"));
            fileNameCounter++;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Draws a grayscale image.
     */
    private BufferedImage createImage(double[][] noise) {
        BufferedImage img = new BufferedImage(noise.length, noise[0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < noise.length; i++) {
            double[] row = noise[i];

            for (int j = 0; j < row.length; j++) {
                int rgb = Math.max(0, Math.min(255, (int) (row[j] * 256.0)));
                rgb = (255 << 24) + (rgb << 16) + (rgb << 8) + rgb;

                img.setRGB(i, j, rgb);
            }
        }

        return img;
    }

    /**
     * Draws a spugedelic image.
     */
    private BufferedImage createSpugeImage(double[][] noise) {
        BufferedImage img = new BufferedImage(noise.length, noise[0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < noise.length; i++) {
            double[] row = noise[i];

            for (int j = 0; j < row.length; j++) {
                int red = Math.max(0, Math.min(255, (int) (Math.sin(row[j] * Math.sin(row[j])) * 256.0)));
                int green = Math.max(0, Math.min(255, (int) ((1 - row[j]) * 256.0)));
                int blue = Math.max(0, Math.min(255, (int) (Math.tan(row[j] * 5) * 256.0)));

                int rgb = Math.max(0, Math.min(255, (int) (row[j] * 256.0)));
                rgb = (255 << 24) + (red << 16) + (green << 8) + blue;

                img.setRGB(i, j, rgb);
            }
        }

        return img;
    }
}