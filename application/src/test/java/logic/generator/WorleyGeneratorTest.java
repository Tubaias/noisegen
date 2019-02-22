
package logic.generator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import logic.generator.WorleyGenerator;

public class WorleyGeneratorTest {
    private WorleyGenerator gen;

    @Before
    public void setUp() {
        gen = new WorleyGenerator(12345);
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

    @Test
    public void get3DNoiseReturnsValueBetween0And1() {
        double noise1 = gen.get3DNoise(1.55, 0.25, 3.5);
        double noise2 = gen.get3DNoise(3.15, 0.35, 7.0);

        assertTrue(noise1 <= 1 && noise1 >= 0);
        assertTrue(noise2 <= 1 && noise2 >= 0);
    }

    @Test
    public void get3DNoiseReturnsDifferentValuesForDifferentPoints() {
        double noise1 = gen.get3DNoise(1.55, 0.25, 3.5);
        double noise2 = gen.get3DNoise(3.15, 0.35, 7.0);

        assertFalse(noise1 == noise2);
    }

    @Test
    public void get3DNoiseAlwaysReturnsTheSameValueForTheSamePoint() {
        double x = 1.55;
        double y = 0.25;
        double z = 3.5;

        double noise1 = gen.get3DNoise(x, y, z);
        double noise2 = gen.get3DNoise(x, y, z);
        double noise3 = gen.get3DNoise(x, y, z);

        assertTrue(noise1 == noise2);
        assertTrue(noise2 == noise3);
    }
}