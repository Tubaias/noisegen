
package logic;

/**
 * Interface for different noise generation algorithms.
 */
public interface NoiseGenerator {

    /**
     * Returns the noise value between 0 and 1 for a given 2D coordinate.
     */
    public double get2DNoise(double x, double y);

    /**
     * Returns the noise value between 0 and 1 for a given 3D coordinate.
     */
    public double get3DNoise(double x, double y, double z);
}