
import io.GraphicIO;
import io.ImagePrinter;

/**
 * Main class.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Henlo wrlod,,");

        GraphicIO io = new GraphicIO(new ImagePrinter());
        io.run();
    }
}