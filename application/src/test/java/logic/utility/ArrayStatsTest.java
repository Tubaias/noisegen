
package logic.utility;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ArrayStatsTest {
    private double[][] array;

    @Before
    public void setUp() {
        array = new double[3][3];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                array[x][y] = x + y;
            }
        }
    }

    @Test
    public void largestValue2DReturnsLargest() {
        double largest = ArrayStats.largestValue2D(array);
        assertEquals(4, largest, 0.0);
    }

    @Test
    public void smallestValue2DReturnsSmallest() {
        double smallest = ArrayStats.smallestValue2D(array);
        assertEquals(0, smallest, 0.0);
    }

    @Test
    public void pointsInRange2DWithPointsReturnsRightAmount() {
        int points = ArrayStats.pointsInRange2D(array, 0, 1.1);
        assertEquals(3, points);
    }

    @Test
    public void pointsInRange2DWithoutPointsReturnsRightAmount() {
        int points = ArrayStats.pointsInRange2D(array, 5.0, 10.0);
        assertEquals(0, points);
    }
}