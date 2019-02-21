
package logic.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import logic.generator.PerlinGenerator;

public class ArrayFillerTest {
    private PerlinGenerator gen;

    @Before
    public void setUp() {
        gen = new PerlinGenerator(123);
    }

    @Test
    public void fill2DArrayReturnsArrayOfWantedSize() {
        int width = 64;
        int heigth = 145;

        double[][] array = ArrayFiller.fill2DArray(width, heigth, 0.5, gen);

        assertEquals(width, array.length);
        assertEquals(heigth, array[0].length);
    }

    @Test
    public void fill2DArrayReturnsAnIdenticalArrayWithIdenticalParams() {
        int width = 64;
        int heigth = 145;

        double[][] array1 = ArrayFiller.fill2DArray(width, heigth, 0.5, gen);
        double[][] array2 = ArrayFiller.fill2DArray(width, heigth, 0.5, gen);
        assertTrue(Arrays.deepEquals(array1, array2));
    }

    @Test
    public void fill3DArrayReturnsArrayOfWantedSize() {
        int width = 64;
        int heigth = 145;
        int depth = 5;

        double[][][] array = ArrayFiller.fill3DArray(width, heigth, depth, 0.5, gen);

        assertEquals(depth, array.length);
        assertEquals(width, array[0].length);
        assertEquals(heigth, array[0][0].length);
    }

    @Test
    public void fill3DArrayReturnsAnIdenticalArrayWithIdenticalParams() {
        int width = 64;
        int heigth = 145;
        int depth = 5;

        double[][][] array1 = ArrayFiller.fill3DArray(width, heigth, depth, 0.5, gen);
        double[][][] array2 = ArrayFiller.fill3DArray(width, heigth, depth, 0.5, gen);
        assertTrue(Arrays.deepEquals(array1, array2));
    }
}