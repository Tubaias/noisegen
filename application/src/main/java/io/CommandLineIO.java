
package io;

import logic.ArrayFiller;
import logic.NoiseGenerator;
import logic.PerlinGenerator;
import logic.WorleyGenerator;

/**
 * A class for input/output via the command line.
 */
public class CommandLineIO implements IO {

    /**
     * Constructor. Dependency injection to be added, propably.
     */
    public CommandLineIO() {

    }

    public void run() {
        System.out.println("cmd IO running");

        NoiseGenerator gen = new PerlinGenerator();
        NoiseGenerator gen2 = new WorleyGenerator();

        double scale = 0.1;
        double[][] noise = ArrayFiller.fill2DArray(64, 64, scale, gen2, 11111);
        print2DArray(noise);
    }

    /**
     * Prints a 2D noise array on the command line with ASCII symbols representing
     * different numeric values.
     *
     * @param noise Array to print.
     */
    public static void print2DArray(double[][] noise) {
        String[] symbols = new String[] { ".", "-", "~", "+", "o", "£", "O", "0", "#", "@" };

        for (double[] xs : noise) {
            for (double y : xs) {
                for (int i = 0; i < symbols.length; i++) {
                    String symbol = symbols[i];
                    double limit = (double) (i + 1) / symbols.length;

                    if (y < limit || i == symbols.length - 1) {
                        System.out.print(symbol + " ");
                        break;
                    }
                }
            }

            System.out.println();
        }
    }
}