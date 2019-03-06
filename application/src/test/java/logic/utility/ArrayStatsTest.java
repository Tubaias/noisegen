
package logic.utility;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ArrayStatsTest {
    private double[][] array2D;
    private double[][][] array3D;

    @Before
    public void setUp() {
        array2D = new double[3][3];
        array3D = new double[3][3][3];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                array2D[x][y] = x + y;

                for (int z = 0; z < 3; z++) {
                    array3D[x][y][z] = x + y + z;
                }
            }
        }
    }

    @Test
    public void largestValue2DReturnsLargest() {
        double largest = ArrayStats.largestValue(array2D);
        assertEquals(4, largest, 0.0);
    }

    @Test
    public void smallestValue2DReturnsSmallest() {
        double smallest = ArrayStats.smallestValue(array2D);
        assertEquals(0, smallest, 0.0);
    }

    @Test
    public void pointsInRange2DWithPointsReturnsRightAmount() {
        int points = ArrayStats.pointsInRange(array2D, 0, 1.1);
        assertEquals(3, points);
    }

    @Test
    public void pointsInRange2DWithoutPointsReturnsRightAmount() {
        int points = ArrayStats.pointsInRange(array2D, 5.0, 10.0);
        assertEquals(0, points);
    }

    @Test
    public void largestValue3DReturnsLargest() {
        double largest = ArrayStats.largestValue(array3D);
        assertEquals(6, largest, 0.0);
    }

    @Test
    public void smallestValue3DReturnsSmallest() {
        double smallest = ArrayStats.smallestValue(array3D);
        assertEquals(0, smallest, 0.0);
    }

    @Test
    public void pointsInRange3DWithPointsReturnsRightAmount() {
        int points = ArrayStats.pointsInRange(array3D, 0, 1.1);
        assertEquals(4, points);
    }

    @Test
    public void pointsInRange3DWithoutPointsReturnsRightAmount() {
        int points = ArrayStats.pointsInRange(array3D, 7.0, 10.0);
        assertEquals(0, points);
    }
}