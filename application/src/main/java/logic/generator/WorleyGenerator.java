
package logic.generator;

import logic.LCGRandom;
import logic.FNVHash;

public class WorleyGenerator implements NoiseGenerator {
    private LCGRandom rand;
    private FNVHash fnv;
    private int seed;
    private int featurePointLower;
    private int featurePointUpper;

    /**
     * Constructor.
     * @param seed Seed value affecting the pseudorandom generation.
     */
    public WorleyGenerator(int seed) {
        rand = new LCGRandom(seed);
        fnv = new FNVHash();
        this.seed = seed;
        this.featurePointLower = 1;
        this.featurePointUpper = 1;
    }

    /**
     * Sets the bounds for the amount of feature points per cell.
     * @param lower Lower bound.
     * @param upper Upper bound.
     */
    public void setFeaturePointBounds(int lower, int upper) {
        this.featurePointLower = lower;
        this.featurePointUpper = upper;
    }

    public double get2DNoise(double x, double y) {
        // coordinates of the cell the point is in
        int originCellX = (int) x;
        int originCellY = (int) y;

        // counter for lowest found distance
        double minDist = 2;

        // double for-loop going through the cell the point is in and the 8 adjacent cells
        for (int i = -1; i < 2; i++) {
            int cellX = originCellX + i;

            for (int j = -1; j < 2; j++) {
                int cellY = originCellY + j;

                // generate initial hash to be used as a seed for the RNG
                long hashValue = fnv.get2DHash(cellX + seed, cellY);
                rand.setSeed(hashValue);

                // generate the amount of feature points for this cell
                long lastRandom = rand.getRandom();
                int featurePointAmount = featurePointCount(lastRandom, featurePointLower, featurePointUpper);

                // generate the feature points and check their distance to the initial point
                for (int p = 0; p < featurePointAmount; p++) {
                    lastRandom = rand.getRandom();
                    double randomX = (double) lastRandom / 0x100000000l;

                    lastRandom = rand.getRandom();
                    double randomY = (double) lastRandom / 0x100000000l;

                    double featurePointX = cellX + randomX;
                    double featurePointY = cellY + randomY;

                    double dist = distance(x, y, featurePointX, featurePointY);

                    if (dist < minDist) {
                        minDist = dist;
                    }
                }
            }
        }

        if (minDist > 1) {
            minDist = 1;
        }

        return minDist;
    }

    /**
     * Not yet implemented.
     */
    public double get3DNoise(double x, double y, double z) {
        return 0;
    }

    private int featurePointCount(long value, int lower, int upper) {
        if (lower == upper) {
            return lower;
        }

        return (int) (value % (upper - lower)) + lower;
    }

    //Standard library square root function to be replaced.
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }
}