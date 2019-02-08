
package io;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import logic.ArrayFiller;
import logic.generator.NoiseGenerator;
import logic.generator.PerlinGenerator;
import logic.generator.WorleyGenerator;

/**
 * A class for input/output via a graphical interface.
 */
public class GraphicIO implements IO, ActionListener {
    private ImagePrinter printer;
    private double[][] noise;
    private JFrame frame;

    private JRadioButton perlinButton;
    private JRadioButton worleyButton;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField scaleField;
    private JTextField seedField;
    private JPanel imagePanel;

    /**
     * Constructor.
     *
     * @param printer Printer to print results into files.
     */
    public GraphicIO(ImagePrinter printer) {
        this.printer = printer;
    }

    public void run() {
        frame = new JFrame("noisegen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        imagePanel = new JPanel();
        JPanel items = setupItems();

        frame.getContentPane().add(items, BorderLayout.WEST);
        frame.getContentPane().add(imagePanel, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Event listening function.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("generate".equals(e.getActionCommand())) {
            generateImage();
        }
    }

    private void generateImage() {
        // generates a noise image and shows it on the screen.

        NoiseGenerator gen = new PerlinGenerator();

        if (worleyButton.isSelected()) {
            gen = new WorleyGenerator(Integer.parseInt(seedField.getText()));
        }

        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());
        double scale = Double.parseDouble(scaleField.getText());
        noise = ArrayFiller.fill2DArray(width, height, scale, gen);

        ImageIcon img = new ImageIcon(printer.print2D(noise));
        imagePanel.removeAll();
        imagePanel.add(new JLabel(img));

        frame.pack();
    }

    private JPanel setupItems() {
        // setup menu items for generation parameter inputs.

        JPanel items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.PAGE_AXIS));

        perlinButton = new JRadioButton("Perlin noise");
        worleyButton = new JRadioButton("Worley noise");
        perlinButton.setSelected(true);

        ButtonGroup algoButtons = new ButtonGroup();
        algoButtons.add(perlinButton);
        algoButtons.add(worleyButton);

        widthField = new JTextField(20);
        heightField = new JTextField(20);
        scaleField = new JTextField(20);
        seedField = new JTextField(20);

        JPanel widthFieldPanel = new JPanel();
        widthFieldPanel.setLayout(new FlowLayout());
        widthFieldPanel.add(widthField);

        JPanel heightFieldPanel = new JPanel();
        heightFieldPanel.setLayout(new FlowLayout());
        heightFieldPanel.add(heightField);

        JPanel scaleFieldPanel = new JPanel();
        scaleFieldPanel.setLayout(new FlowLayout());
        scaleFieldPanel.add(scaleField);

        JPanel seedFieldPanel = new JPanel();
        seedFieldPanel.setLayout(new FlowLayout());
        seedFieldPanel.add(seedField);

        JButton generateButton = new JButton("generate noise image");
        generateButton.setActionCommand("generate");
        generateButton.addActionListener(this);

        items.add(perlinButton);
        items.add(worleyButton);
        items.add(new JLabel(" "));
        items.add(new JLabel("width:"));
        items.add(widthFieldPanel);
        items.add(new JLabel("height:"));
        items.add(heightFieldPanel);
        items.add(new JLabel("scale (try values < 0.1):"));
        items.add(scaleFieldPanel);
        items.add(new JLabel("seed (only affects Worley currently):"));
        items.add(seedFieldPanel);
        items.add(new JLabel(" "));
        items.add(generateButton);

        return items;
    }
}