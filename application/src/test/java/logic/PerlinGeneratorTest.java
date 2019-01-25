
package logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PerlinGeneratorTest {
    private PerlinGenerator gen;

    @Before
    public void setUp() {
        gen = new PerlinGenerator();
    }

    @Test
    public void get2DNoiseReturnsValueBetween0And1() {
        double noise1 = gen.get2DNoise(1.55, 0.25);
        double noise2 = gen.get2DNoise(3.15, 0.35);

        assertTrue(noise1 <= 1 && noise1 >= 0);
        assertTrue(noise2 <= 1 && noise2 >= 0);
    }

    @Test
    public void get2DNoiseReturnsDifferentValuesForDifferentPoints() {
        double noise1 = gen.get2DNoise(1.55, 0.25);
        double noise2 = gen.get2DNoise(3.15, 0.35);

        assertFalse(noise1 == noise2);
    }

    @Test
    public void get2DNoiseAlwaysReturnsTheSameValueForTheSamePoint() {
        double x = 1.55;
        double y = 0.25;

        double noise1 = gen.get2DNoise(x, y);
        double noise2 = gen.get2DNoise(x, y);
        double noise3 = gen.get2DNoise(x, y);

        assertTrue(noise1 == noise2);
        assertTrue(noise2 == noise3);
    }
}