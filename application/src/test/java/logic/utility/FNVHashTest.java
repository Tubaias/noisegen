
package logic.utility;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FNVHashTest {
    private FNVHash fnv;

    @Before
    public void setUp() {
        fnv = new FNVHash();
    }

    @Test
    public void get2DHashReturnsNonNegativeValues() {
        long hash1 = fnv.get2DHash(0, 0);
        long hash2 = fnv.get2DHash(1000, 1000);
        long hash3 = fnv.get2DHash(123456, 0);
        long hash4 = fnv.get2DHash(15, 256);

        assertTrue(hash1 >= 0);
        assertTrue(hash2 >= 0);
        assertTrue(hash3 >= 0);
        assertTrue(hash4 >= 0);
    }
}