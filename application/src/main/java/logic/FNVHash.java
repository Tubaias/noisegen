
package logic;

public class FNVHash {
    private long offsetBasis;
    private long FNV_prime;

    public FNVHash() {
        // FNV-1 magic numbers for 32-bit hash generation.
        offsetBasis = 2166136261l;
        FNV_prime = 16777619l;
    }

    public long get2DHash(int x, int y) {
        long hash = offsetBasis;
        byte[] bytes = toByteArray(new int[]{x, y});

        for (byte b : bytes) {
            hash ^= b;
            hash *= FNV_prime;
        }

        if (hash < 0) {
            hash = -hash;
        }

        return hash;
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