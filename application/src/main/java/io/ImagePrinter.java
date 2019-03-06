
package io;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Class for printing 2D noise arrays into BufferedImages or saving them into PNG files.
 */
public class ImagePrinter {

    /**
     * Returns a BufferedImage representing the given noise array.
     */
    public BufferedImage print(double[][] noise) {
        return createImage(noise);
    }

    /**
     * Saves a PNG image representing the noise array into the application's root folder.
     */
    public void printToFile(double[][] noise) {
        BufferedImage img = createImage(noise);

        try {
            ImageIO.write(img, "PNG", new File("./savedimage.png"));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private BufferedImage createImage(double[][] noise) {
        BufferedImage img = new BufferedImage(noise.length, noise[0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < noise.length; i++) {
            double[] row = noise[i];

            for (int j = 0; j < row.length; j++) {
                int rgb = Math.max(0, Math.min(255, (int) Math.floor(row[j] * 256.0)));
                rgb = (255 << 24) + (rgb << 16) + (rgb << 8) + rgb;

                img.setRGB(i, j, rgb);
            }
        }

        return img;
    }
}