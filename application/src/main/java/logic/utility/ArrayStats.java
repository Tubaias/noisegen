package logic.utility;

/**
 * A class for calculating statistics for given 2D arrays via static methods.
 */
public class ArrayStats {

    /**
     * Returns the amount of points in a given range in the given array.
     * @param min Lower bound, inclusive.
     * @param max Upper bound, exclusive.
     * @return Amount of points in the range between min and max.
     */
    public static int pointsInRange(double[][] array, double min, double max) {
        int points = 0;

        for (double[] row : array) {
            for (double value : row) {
                if (value < max && value >= min) {
                    points++;
                }
            }
        }

        return points;
    }

    /**
     * Returns the largest single value in the given array.
     */
    public static double largestValue(double[][] array) {
        double largest = 0;

        for (double[] row : array) {
            for (double value : row) {
                if (value > largest) {
                    largest = value;
                }
            }
        }

        return largest;
    }

    /**
     * Returns the smallest single value in the given array.
     */
    public static double smallestValue(double[][] array) {
        double smallest = 1;

        for (double[] row : array) {
            for (double value : row) {
                if (value < smallest) {
                    smallest = value;
                }
            }
        }

        return smallest;
    }
}