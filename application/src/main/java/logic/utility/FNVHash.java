
package logic.utility;

/**
 * FNV-1a hashing algorithm implementation.
 */
public class FNVHash {
    private long offsetBasis;
    private long FNV_prime;

    public FNVHash() {
        // FNV-1 magic numbers for 32-bit hash generation.
        offsetBasis = 2166136261l;
        FNV_prime = 16777619l;
    }

    /**
     * Returns a hash based on 2D coordinate inputs.
     */
    public long get2DHash(int x, int y) {
        long hash = offsetBasis;
        byte[] bytes = toByteArray(new int[]{x, y});

        for (byte b : bytes) {
            hash ^= b;
            hash *= FNV_prime;
        }

        return hash & (0x100000000l - 1);
    }

    /**
     * Returns a hash based on 3D coordinate inputs.
     */
    public long get3DHash(int x, int y, int z) {
        long hash = offsetBasis;
        byte[] bytes = toByteArray(new int[]{x, y, z});

        for (byte b : bytes) {
            hash ^= b;
            hash *= FNV_prime;
        }

        return hash & (0x100000000l - 1);
    }

    private byte[] toByteArray(int[] ints) {
        byte[] bytes = new byte[ints.length * 4];

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < 4; j++) {
                bytes[i + j] = (byte) (ints[i] >> (8 * j) & 0xff);
            }
        }

        return bytes;
    }
}