
package logic.utility;

import logic.generator.NoiseGenerator;

/**
 * Fills 2D or 3D arrays of given dimensions with a given noise algorithm.
 */
public class ArrayFiller {

    /**
     * Fills a 2D array of size width*heigth with noise.
     *
     * @param scale Scaling factor between 0 and 1 (exclusive) to determine the 'size' of the noise pattern relative to the array.
     * @param gen NoiseGenerator to use for generating the array.
     */
    public static double[][] fill2DArray(int width, int height, double scale, NoiseGenerator gen) {
        long time = System.nanoTime();

        double[][] noiseArray = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                noiseArray[i][j] = gen.get2DNoise(i * scale, j * scale);
            }
        }

        long endTime = System.nanoTime();
        System.out.println("generation duration: " + ((endTime - time) / 1_000_000.0));

        return noiseArray;
    }

    /**
     * Fills a 3D array of size width*heigth*depth with noise.
     *
     * @param scale Scaling factor to determine the 'size' of the noise pattern relative to the array.
     * @param gen NoiseGenerator to use for generating the array.
     */
    public static double[][][] fill3DArray(int width, int height, int depth, double scale, NoiseGenerator gen) {
        double[][][] noiseArray = new double[width][height][depth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < depth; k++) {
                    noiseArray[i][j][k] = gen.get3DNoise(i * scale, j * scale, k * scale);
                }
            }
        }

        return noiseArray;
    }
}