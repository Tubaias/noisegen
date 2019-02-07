import java.util.Scanner;

import io.CommandLineIO;
import io.GraphicIO;
import io.IO;
import io.ImagePrinter;

/**
 * Main class.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Henlo wrlod,,");

        //IO io = new CommandLineIO(new Scanner(System.in), new ImagePrinter());
        IO io = new GraphicIO(new ImagePrinter());
        io.run();
    }
}