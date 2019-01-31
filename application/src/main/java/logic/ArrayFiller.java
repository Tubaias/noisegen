
package logic;

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
    public static double[][] fill2DArray(int width, int heigth, double scale, NoiseGenerator gen, int seed) {
        double[][] noiseArray = new double[width][heigth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                noiseArray[i][j] = gen.get2DNoise(i * scale, j * scale, seed);
            }
        }

        return noiseArray;
    }

    /**
     * Fills a 3D array of size width*heigth*depth with noise.
     *
     * @param scale Scaling factor to determine the 'size' of the noise pattern relative to the array.
     * @param gen NoiseGenerator to use for generating the array.
     */
    public static double[][][] fill3DArray(int width, int heigth, int depth, double scale, NoiseGenerator gen, int seed) {
        double[][][] noiseArray = new double[width][heigth][depth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                for (int k = 0; k < depth; k++) {
                    noiseArray[i][j][k] = gen.get3DNoise(i * scale, j * scale, k * scale, seed);
                }
            }
        }

        return noiseArray;
    }
}