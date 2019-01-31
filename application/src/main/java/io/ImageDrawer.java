
package io;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import logic.ArrayFiller;
import logic.NoiseGenerator;
import logic.PerlinGenerator;
import logic.WorleyGenerator;

public class ImageDrawer implements IO {

    public void run() {
        System.out.println("drawer running");

        NoiseGenerator gen = new PerlinGenerator();
        NoiseGenerator gen2 = new WorleyGenerator();

        int x = 256 * 4;
        int y = 256 * 4;

        double scale = 0.025;
        double[][] noise = ArrayFiller.fill2DArray(x, y, scale, gen2, 54312312);
        print2DArray(noise);
    }

    public static void print2DArray(double[][] noise) {
        BufferedImage img = new BufferedImage(noise.length, noise[0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < noise.length; i++) {
            double[] row = noise[i];

            for (int j = 0; j < row.length; j++) {
                int rgb = Math.max(0, Math.min(255, (int) Math.floor(row[j] * 256.0)));
                rgb = (rgb << 24) + (rgb << 16) + (rgb << 8) + rgb;

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