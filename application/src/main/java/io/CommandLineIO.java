
package io;

import java.util.Scanner;
import logic.ArrayFiller;
import logic.generator.NoiseGenerator;
import logic.generator.PerlinGenerator;
import logic.generator.WorleyGenerator;

/**
 * A class for input/output via the command line.
 */
public class CommandLineIO implements IO {
    private Scanner scanner;
    private ImagePrinter printer;

    /**
     * Constructor.
     * @param scanner Scanner insert inputs for generation parameters.
     * @param printer Printer to print results into files.
     */
    public CommandLineIO(Scanner scanner, ImagePrinter printer) {
        this.scanner = scanner;
        this.printer = printer;
    }

    public void run() {
        NoiseGenerator gen = queryGenerator();
        if (gen == null) {
            System.out.println("Invalid seed.");
            return;
        }

        int[] dimensions = queryDimensions();
        if (dimensions == null) {
            System.out.println("Invalid value.");
            return;
        }

        int h = dimensions[0];
        int w = dimensions[1];

        double scale = queryScale();
        if (scale == -1) {
            System.out.println("Invalid value.");
            return;
        }

        System.out.println("Generating noise.");
        double[][] noise = ArrayFiller.fill2DArray(w, h, scale, gen);

        System.out.println("How do you want to print the result?");
        System.out.println("[a]scii / [p]ng");
        String input = scanner.nextLine().toLowerCase();

        if ("ascii".contains(input)) {
            print2DArray(noise);
        } else if ("png".contains(input)) {
            printer.print2DToFile(noise);
        }
    }

    private NoiseGenerator queryGenerator() {
        NoiseGenerator gen = null;

        System.out.println("Which type of noise do you want to generate?");
        System.out.println("[p]erlin / [w]orley");
        String input = scanner.nextLine().toLowerCase();

        if ("perlin noise".contains(input)) {
            gen = new PerlinGenerator(123);
        } else if ("worley noise".contains(input)) {
            System.out.println("Input an integer seed:");
            try {
                int seed = Integer.parseInt(scanner.nextLine());
                gen = new WorleyGenerator(seed);
            } catch (Exception e) {
                return null;
            }
        }

        return gen;
    }

    private int[] queryDimensions() {
        int h = 0;
        int w = 0;

        System.out.println("Insert image height:");
        try {
            h = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return null;
        }

        System.out.println("Insert image width:");
        try {
            w = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return null;
        }

        return new int[] {h, w};
    }

    private double queryScale() {
        System.out.println("Insert pattern scale between 0 and 1 (exclusive):");
        try {
            double input = Double.parseDouble(scanner.nextLine());

            if (input > 1 || input < 0) {
                return -1;
            }

            return input;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Prints a 2D noise array on the command line with ASCII symbols representing
     * different numeric values.
     *
     * @param noise Array to print.
     */
    public void print2DArray(double[][] noise) {
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