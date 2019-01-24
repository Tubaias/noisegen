
package logic;

public class ArrayFiller {
    public ArrayFiller() {

    }

    public double[][] fill2DArray(int width, int heigth, double scale, NoiseGenerator gen) {
        double[][] noiseArray = new double[width][heigth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                noiseArray[i][j] = gen.get2DNoise(i * scale, j * scale);
            }
        }

        return noiseArray;
    }

    public double[][][] fill3DArray(int width, int heigth, int depth, double scale, NoiseGenerator gen) {
        double[][][] noiseArray = new double[width][heigth][depth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                for (int k = 0; k < depth; k++) {
                    noiseArray[i][j][k] = gen.get3DNoise(i * scale, j * scale, k * scale);
                }
            }
        }

        return noiseArray;
    }
}