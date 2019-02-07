
package io;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagePrinter {

    public BufferedImage print2D(double[][] noise) {
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

    public void print2DToFile(double[][] noise) {
        BufferedImage img = new BufferedImage(noise.length, noise[0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < noise.length; i++) {
            double[] row = noise[i];

            for (int j = 0; j < row.length; j++) {
                int rgb = Math.max(0, Math.min(255, (int) Math.floor(row[j] * 256.0)));
                rgb = (255 << 24) + (rgb << 16) + (rgb << 8) + rgb;

                img.setRGB(i, j, rgb);
            }
        }

        try {
            ImageIO.write(img, "PNG", new File("./asdfasd.png"));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}