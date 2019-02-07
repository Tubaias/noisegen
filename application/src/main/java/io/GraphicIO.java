
package io;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import logic.ArrayFiller;
import logic.generator.NoiseGenerator;
import logic.generator.WorleyGenerator;

/**
 * A class for input/output via a graphical interface.
 */
public class GraphicIO implements IO {
    private ImagePrinter printer;

    /**
     * Constructor.
     *
     * @param scanner Scanner insert inputs for generation parameters.
     * @param printer Printer to print results into files.
     */
    public GraphicIO(ImagePrinter printer) {
        this.printer = printer;
    }

    public void run() {
        JFrame frame = new JFrame("noisegen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setResizable(false);

        frame.getContentPane().setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        NoiseGenerator gen = new WorleyGenerator(12345);
        int width = 512;
        int height = 512;
        double scale = 0.05;
        double[][] noise = ArrayFiller.fill2DArray(width, height, scale, gen);

        ImageIcon img = new ImageIcon(printer.print2D(noise));

        BoxLayout itemBox = new BoxLayout();

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(new JLabel(img), BorderLayout.EAST);
        frame.getContentPane().add(itemBox, BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
    }

}