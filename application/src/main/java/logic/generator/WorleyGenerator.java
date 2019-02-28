
package logic.generator;

import logic.utility.LCGRandom;
import logic.utility.FNVHash;

public class WorleyGenerator implements NoiseGenerator {
    private LCGRandom rand;
    private FNVHash fnv;
    private int seed;
    private int featurePointLower;
    private int featurePointUpper;

    /**
     * Constructor.
     *
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
     *
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
                int featurePointAmount = featurePointCount(rand.getRandom(), featurePointLower, featurePointUpper);

                // generate the feature points and check their distance to the initial point
                for (int p = 0; p < featurePointAmount; p++) {
                    double randomX = (double) rand.getRandom() / 0x100000000l;
                    double randomY = (double) rand.getRandom() / 0x100000000l;

                    double featurePointX = cellX + randomX;
                    double featurePointY = cellY + randomY;

                    double dist = distance2D(x, y, featurePointX, featurePointY);

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

    public double get3DNoise(double x, double y, double z) {
        // coordinates of the cell the point is in
        int originCellX = (int) x;
        int originCellY = (int) y;
        int originCellZ = (int) z;

        // counter for lowest found distance
        double minDist = 2;

        // double for-loop going through the cell the point is in and the 8 adjacent cells
        for (int i = -1; i < 2; i++) {
            int cellX = originCellX + i;

            for (int j = -1; j < 2; j++) {
                int cellY = originCellY + j;

                for (int k = -1; k < 2; k++) {
                    int cellZ = originCellZ + k;

                    // generate initial hash to be used as a seed for the RNG
                    long hashValue = fnv.get3DHash(cellX + seed, cellY, cellZ);
                    rand.setSeed(hashValue);

                    // generate the amount of feature points for this cell
                    int featurePointAmount = featurePointCount(rand.getRandom(), featurePointLower, featurePointUpper);

                    // generate the feature points and check their distance to the initial point
                    for (int p = 0; p < featurePointAmount; p++) {
                        double randomX = (double) rand.getRandom() / 0x100000000l;
                        double randomY = (double) rand.getRandom() / 0x100000000l;
                        double randomZ = (double) rand.getRandom() / 0x100000000l;

                        double featurePointX = cellX + randomX;
                        double featurePointY = cellY + randomY;
                        double featurePointZ = cellZ + randomZ;

                        double dist = distance3D(x, y, z, featurePointX, featurePointY, featurePointZ);

                        if (dist < minDist) {
                            minDist = dist;
                        }
                    }
                }
            }
        }

        if (minDist > 1) {
            minDist = 1;
        }

        return minDist;
    }

    private int featurePointCount(long value, int lower, int upper) {
        if (lower > upper) {
            return 0;
        } else if (lower == upper) {
            return lower;
        }

        return (int) (value % (upper - lower + 1)) + lower;
    }

    /**
     * 2D point distance calculation using the standard library square root function due to it's superior speed.
     */
    private double distance2D(double x1, double y1, double x2, double y2) {
        double xDiff = x2 - x1;
        double yDiff = y2 - y1;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * 3D point distance calculation using the standard library square root function due to it's superior speed.
     */
    private double distance3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        double xDiff = x2 - x1;
        double yDiff = y2 - y1;
        double zDiff = z2 - z1;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
    }
}