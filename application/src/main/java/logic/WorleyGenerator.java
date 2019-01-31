
package logic;

import logic.LCGRandom;
import logic.FNVHash;

public class WorleyGenerator implements NoiseGenerator {
    private LCGRandom rand;
    private FNVHash hash;

    public WorleyGenerator() {
        rand = new LCGRandom();
        hash = new FNVHash();
    }

    public double get2DNoise(double x, double y, int seed) {
        // coordinates of the cell the point is in
        int originCellX = (int) x;
        int originCellY = (int) y;

        double minDist = 2;

        for (int i = -1; i < 2; i++) {
            int cellX = originCellX + i;

            for (int j = -1; j < 2; j++) {
                int cellY = originCellY + j;
                long hashValue = hash.get2DHash(cellX + seed, cellY);
                long lastRandom = rand.getRandom(hashValue);
                int featurePointAmount = probLookup(lastRandom);

                for (int l = 0; l < featurePointAmount; l++) {
                    lastRandom = rand.getRandom(lastRandom);
                    double randomX = (double) lastRandom / 0x100000000l;

                    lastRandom = rand.getRandom(lastRandom);
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

        return minDist;
    }

    public double get3DNoise(double x, double y, double z, int seed) {
        return 0;
    }

    private int probLookup(long value) {
        /*
        if (value < 393325350l)
            return 1;
        if (value < 1022645910l)
            return 2;
        if (value < 1861739990l)
            return 3;
        if (value < 2700834071l)
            return 4;
        if (value < 3372109335l)
            return 5;
        if (value < 3819626178l)
            return 6;
        if (value < 4075350088l)
            return 7;
        if (value < 4203212043l)
            return 8;
        return 9;
        */

        return 1;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }
}