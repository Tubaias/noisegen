
import io.IO;
import io.ImageDrawer;
import io.CommandLineIO;

/**
 * Main class.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Henlo wrlod,,");

        //IO io = new CommandLineIO();
        //io.run();

        ImageDrawer drawer = new ImageDrawer();
        drawer.run();
    }
}