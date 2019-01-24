
package io;

import logic.ArrayFiller;
import logic.NoiseGenerator;
import logic.PerlinGenerator;

public class CommandLineIO implements IO {

    public CommandLineIO() {

    }

    public void run() {
        System.out.println("cmd IO running");

        PerlinGenerator gen = new PerlinGenerator();
        ArrayFiller filler = new ArrayFiller();

        double[][] noise = filler.fill2DArray(64, 64, 0.05, gen);

        //
        for (int i = 0; i < gen.hashes.length; i++) {
            for (int j = 0; j < gen.hashes[i].length; j++) {
                System.out.print(gen.hashes[i][j] + ",");

                if (j % 2 == 1) {
                    System.out.print("  ");
                }
            }
            if (i % 2 == 1) {
                System.out.println();
            }

            System.out.println();
        }
        //

        print2DNoiseArray(noise);
    }

    private void print2DNoiseArray(double[][] noise) {
        String[] symbols = new String[] {".", "-", "~", "+", "o", "O", "0", "£", "#", "@"};

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