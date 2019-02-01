
package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class LCGRandomTest {
    private LCGRandom rand;

    @Before
    public void setUp() {
        rand = new LCGRandom(12345);
    }

    @Test
    public void getRandomReturnsNonNegativeValues() {
        long random1 = rand.getRandom();
        long random2 = rand.getRandom();
        long random3 = rand.getRandom();
        long random4 = rand.getRandom();
        long random5 = rand.getRandom();

        assertTrue(random1 >= 0);
        assertTrue(random2 >= 0);
        assertTrue(random3 >= 0);
        assertTrue(random4 >= 0);
        assertTrue(random5 >= 0);
    }

    @Test
    public void sameSeedGeneratesSameValues() {
        int seed = 54321;

        rand.setSeed(seed);
        long random1 = rand.getRandom();
        long random2 = rand.getRandom();
        long random3 = rand.getRandom();

        rand.setSeed(seed);
        long random4 = rand.getRandom();
        long random5 = rand.getRandom();
        long random6 = rand.getRandom();

        assertEquals(random1, random4);
        assertEquals(random2, random5);
        assertEquals(random3, random6);
    }
}