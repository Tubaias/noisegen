
package logic;

public class PerlinGenerator implements NoiseGenerator {
    private final int[] p;
    private int repeat;
    public int[][] hashes;

    public PerlinGenerator() {
        p = createPermutation();

        hashes = new int[8][8];
    }

    public double get2DNoise(double x, double y) {
        if (repeat > 0) {
            x = x % repeat;
            y = y % repeat;
        }

        int cellX = (int) x & 255;
        int cellY = (int) y & 255;
        double localX = x - (int) x;
        double localY = y - (int) y;

        double u = fade(localX);
        double v = fade(localY);

        int aa = p[p[cellX] + cellY];
        int ab = p[p[cellX] + inc(cellY)];
        int ba = p[p[inc(cellX)] + cellY];
        int bb = p[p[inc(cellX)] + inc(cellY)];

        hashes[cellX * 2][cellY * 2] = aa & 3;
        hashes[cellX * 2][cellY * 2 + 1] = ab & 3;
        hashes[cellX * 2 + 1][cellY * 2] = ba & 3;
        hashes[cellX * 2 + 1][cellY * 2 + 1] = bb & 3;

        double x1 = lerp(grad(aa, localX, localY), grad(ba, localX - 1, localY), u);
        double x2 = lerp(grad(ab, localX, localY - 1), grad(bb, localX - 1, localY - 1), u);

        return (lerp(x1, x2, v) + 1) / 2;
    }

    public double get3DNoise(double x, double y, double z) {
        return 0;
    }

    /**
     * Linear interpolation.
     */
    private double lerp(double a, double b, double x) {
        return b * x + a * (1 - x);
    }

    /**
     * Fade function designed by Ken Perlin. Used to ease coordinate values towards
     * integral values to create a smoother final output. Uses the formula
     * 6t^5-15t^4+10t^3 .
     */
    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    /**
     * Incrementation function with optional wrapping.
     */
    private int inc(int num) {
        num++;

        if (repeat > 0) {
            num = num % repeat;
        }

        return num;
    }

    /**
     * Randomly selects a gradient value based on the hash.
     */
    private double grad(int hash, double x, double y) {
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

    private double _grad(int hash, double x, double y) {
        int h = hash & 15;
        double u = h < 8 ? x : y;

        double v;

        if (h < 4) {
            v = y;
        } else {
            v = x;
        }

        return ((h&1) == 0 ? u : -u)+((h&2) == 0 ? v : -v);
    }

    /**
     * Creates the hardcoded permutation array for hashing.
     *
     * @return Permutation array containing integers from 0 to 255 in a random
     *         order, twice in a row to avoid overflow issues.
     */
    private int[] createPermutation() {
        return new int[] { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69,
                142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117,
                35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134,
                139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46,
                245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200,
                196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5,
                202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183,
                170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19,
                98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210,
                144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157,
                184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243,
                141, 128, 195, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233,
                7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26,
                197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171,
                168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133,
                230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76,
                132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64,
                52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58,
                17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167,
                43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97,
                228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49,
                192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205,
                93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 };
    }
}