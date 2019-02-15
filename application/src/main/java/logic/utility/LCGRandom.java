
package logic.utility;

/**
 * Linear congruential generator algorithm for generating pseudorandom numbers.
 */
public class LCGRandom {
    private long a;
    private int c;
    private long lastRandom;

    /**
     * Constructor.
     * @param seed Initial seed value for random generation.
     */
    public LCGRandom(long seed) {
        this.lastRandom = seed;

        // magic numbers
        a = 1103515245;
        c = 12345;
    }

    /**
     * Initializes the generator with a new seed that will be used for further generation.
     */
    public void setSeed(long seed) {
        this.lastRandom = seed;
    }

    /**
     * Returns a random number based on the seed or last generated value.
     */
    public long getRandom() {
        lastRandom = ((a * lastRandom + c) & (0x100000000l - 1));
        return lastRandom;
    }
}