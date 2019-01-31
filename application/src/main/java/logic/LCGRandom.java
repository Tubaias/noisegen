
package logic;

public class LCGRandom {
    private long a;
    private int c;

    /**
     * Linear congruential generator algorithm for generating pseudorandom numbers.
     */
    public LCGRandom() {
        // magic numbers
        a = 1103515245;
        c = 12345;
    }

    /**
     * Returns a random number based on the input value, which is either a seed or the last generated number.
     * @param lastRandom Seed used for generation, use the last generated value if you don't want to supply another seed.
     */
    public long getRandom(long lastRandom) {
        long value = ((a * lastRandom + c) % 0x100000000l);

        if (value < 0) {
            value = -value;
        }

        return value;
    }
}