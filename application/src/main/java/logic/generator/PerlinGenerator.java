
package logic.generator;

import logic.utility.LCGRandom;

/**
 * Implements the Perlin noise algorithm to calculate a pseudorandom value
 * between 0 and 1 for a given point.
 */
public class PerlinGenerator implements NoiseGenerator {
    private final int[] p;

    /**
     * Constructor.
     *
     * @param seed Seed value affecting the pseudorandom generation.
     */
    public PerlinGenerator(int seed) {
        p = createPermutation(new LCGRandom(seed));
    }

    public double get2DNoise(double x, double y) {
        // coordinates of the cell the point is in
        int cellX = (int) x & 255;
        int cellY = (int) y & 255;
        // relative coordinates of the point within the cell
        double localX = x - (int) x;
        double localY = y - (int) y;

        double u = fade(localX);
        double v = fade(localY);

        // calculating hashes
        int aa = p[p[cellX] + cellY];
        int ab = p[p[cellX] + cellY + 1];
        int ba = p[p[cellX + 1] + cellY];
        int bb = p[p[cellX + 1] + cellY + 1];

        // calculate a pseudorandom value for the point with the gradient and
        // interpolation algorithms
        double x1 = lerp(grad2D(aa, localX, localY), grad2D(ba, localX - 1, localY), u);
        double x2 = lerp(grad2D(ab, localX, localY - 1), grad2D(bb, localX - 1, localY - 1), u);
        return (lerp(x1, x2, v) + 1) / 2;
    }

    public double get3DNoise(double x, double y, double z) {
        // coordinates of the cell the point is in
        int cellX = (int) x & 255;
        int cellY = (int) y & 255;
        int cellZ = (int) z & 255;
        // relative coordinates of the point within the cell
        double localX = x - (int) x;
        double localY = y - (int) y;
        double localZ = z - (int) z;

        double u = fade(localX);
        double v = fade(localY);
        double w = fade(localZ);

        // calculating hashes
        int aaa = p[p[p[cellX] + cellY] + cellZ];
        int aba = p[p[p[cellX] + cellY + 1] + cellZ];
        int aab = p[p[p[cellX] + cellY] + cellZ + 1];
        int abb = p[p[p[cellX] + cellY + 1] + cellZ + 1];
        int baa = p[p[p[cellX + 1] + cellY] + cellZ];
        int bba = p[p[p[cellX + 1] + cellY + 1] + cellZ];
        int bab = p[p[p[cellX + 1] + cellY] + cellZ + 1];
        int bbb = p[p[p[cellX + 1] + cellY + 1] + cellZ + 1];

        // calculate a pseudorandom value for the point with the gradient and
        // interpolation algorithms
        double x1 = lerp(grad3D(aaa, localX, localY, localZ), grad3D(baa, localX - 1, localY, localZ), u);
        double x2 = lerp(grad3D(aba, localX, localY - 1, localZ), grad3D(bba, localX - 1, localY - 1, localZ), u);
        double y1 = lerp(x1, x2, v);

        x1 = lerp(grad3D(aab, localX, localY, localZ - 1), grad3D(bab, localX - 1, localY, localZ - 1), u);
        x2 = lerp(grad3D(abb, localX, localY - 1, localZ - 1), grad3D(bbb, localX - 1, localY - 1, localZ - 1), u);
        double y2 = lerp(x1, x2, v);

        return (lerp(y1, y2, w) + 1) / 2;
    }

    /**
     * Fade function used to ease coordinate values towards integral values to
     * create a smoother final output. Uses the formula 6t^5-15t^4+10t^3 .
     */
    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    /**
     * Linear interpolation.
     */
    private double lerp(double a, double b, double x) {
        return b * x + a * (1 - x);
    }

    /**
     * Randomly selects a gradient value for a 2D point based on the hash.
     */
    private double grad2D(int hash, double x, double y) {
        switch (hash & 3) {
        case 0:
            return x + y;
        case 1:
            return -x + y;
        case 2:
            return x - y;
        case 3:
            return -x - y;
        default:
            throw new RuntimeException("Something went horribly wrong");
        }
    }

    /**
     * Randomly selects a gradient value for a 3D point based on the hash.
     */
    private double grad3D(int hash, double x, double y, double z) {
        switch(hash & 15) {
            case 0: return  x + y;
            case 1: return -x + y;
            case 2: return  x - y;
            case 3: return -x - y;
            case 4: return  x + z;
            case 5: return -x + z;
            case 6: return  x - z;
            case 7: return -x - z;
            case 8: return  y + z;
            case 9: return -y + z;
            case 10: return  y - z;
            case 11: return -y - z;
            case 12: return  y + x;
            case 13: return -y + z;
            case 14: return  y - x;
            case 15: return -y - z;
            default: throw new RuntimeException("Something went horribly wrong");
        }
    }

    /**
     * Creates a random permutation array for hashing.
     *
     * @return Permutation array containing integers from 0 to 255 in a random
     *         order, twice in a row to avoid overflow issues.
     */
    private int[] createPermutation(LCGRandom rand) {
        int[] permutation = new int[256];

        // fill with values
        for (int i = 0; i < 256; i++) {
            permutation[i] = i;
        }

        // shuffle
        for (int i = 255; i > 0; i--) {
            int value = permutation[i];
            int random = (int) (rand.getRandom() % i);

            permutation[i] = permutation[random];
            permutation[random] = value;
        }

        // duplicate
        int[] doublePermutation = new int[512];
        for (int i = 0; i < 256; i++) {
            doublePermutation[i] = permutation[i];
            doublePermutation[i + 256] = permutation[i];
        }

        return doublePermutation;
    }
}