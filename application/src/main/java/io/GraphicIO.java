
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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.utility.ArrayFiller;
import logic.utility.ArrayStats;
import logic.utility.LCGRandom;
import logic.generator.NoiseGenerator;
import logic.generator.PerlinGenerator;
import logic.generator.WorleyGenerator;

/**
 * A class for input/output via a graphical interface.
 */
public class GraphicIO implements IO, ActionListener, ChangeListener {
    private ImagePrinter printer;
    private double[][] noise2D;
    private double[][][] noise3D;
    private JFrame frame;

    private JFrame statsFrame;
    private JRadioButton perlinButton;
    private JRadioButton worleyButton;
    private JRadioButton _2DButton;
    private JRadioButton _3DButton;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField depthField;
    private JTextField scaleField;
    private JTextField seedField;
    private JButton statsButton;
    private JPanel imagePanel;
    private JSlider depthSlider;
    private long generationTime;
    private JLabel notificationLabel;

    /**
     * Constructor.
     *
     * @param printer Printer to print results into files.
     */
    public GraphicIO(ImagePrinter printer) {
        this.printer = printer;
    }

    /**
     * Starts the graphical interface and opens a new window on the screen.
     */
    public void run() {
        frame = new JFrame("noisegen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        notificationLabel = new JLabel();

        imagePanel = new JPanel();
        JPanel imageElementPanel = new JPanel();
        imageElementPanel.setLayout(new BorderLayout());
        depthSlider = new JSlider(0, 1, 0);
        depthSlider.setVisible(false);
        depthSlider.setPaintTicks(true);
        depthSlider.addChangeListener(this);

        imageElementPanel.add(imagePanel, BorderLayout.NORTH);
        imageElementPanel.add(depthSlider, BorderLayout.SOUTH);

        JPanel items = setupItems();

        frame.getContentPane().add(items, BorderLayout.WEST);
        frame.getContentPane().add(imageElementPanel, BorderLayout.EAST);
        frame.getContentPane().add(notificationLabel, BorderLayout.NORTH);

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
        } else if ("random".equals(e.getActionCommand())) {
            randomSeed();
        } else if ("stats".equals(e.getActionCommand())) {
            showStats();
        }
    }

    /**
     * Change listening function for the depth slider.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        ImageIcon img = new ImageIcon(printer.print2D(noise3D[depthSlider.getValue()]));
        imagePanel.removeAll();
        imagePanel.add(new JLabel(img));
        frame.pack();
    }

    /**
     * Generates a noise image and shows it on the screen.
     */
    private void generateImage() {
        int width;
        int height;
        int depth = 1;
        double scale;
        int seed;

        try {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
            scale = Double.parseDouble(scaleField.getText());
            seed = Integer.parseInt(seedField.getText());

            if (_3DButton.isSelected()) {
                depth = Integer.parseInt(depthField.getText());
            }
        } catch (Exception e) {
            notificationLabel.setText("[!] Illegal parameters.");
            statsButton.setEnabled(false);
            frame.pack();
            return;
        }

        if (width < 1 || height < 1 || depth < 1) {
            notificationLabel.setText("[!] Illegal parameters.");
            statsButton.setEnabled(false);
            frame.pack();
            return;
        }

        NoiseGenerator gen;
        if (perlinButton.isSelected()) {
            gen = new PerlinGenerator(seed);
        } else {
            gen = new WorleyGenerator(seed);
        }

        long time = System.nanoTime();

        if (_2DButton.isSelected()) {
            noise3D = null;
            noise2D = ArrayFiller.fill2DArray(width, height, scale, gen);
            depthSlider.setVisible(false);
        } else {
            noise2D = null;
            noise3D = ArrayFiller.fill3DArray(width, height, depth, scale, gen);
            depthSlider.setVisible(true);
        }

        long endTime = System.nanoTime();
        generationTime = endTime - time;

        ImageIcon img;

        if (_2DButton.isSelected()) {
            img = new ImageIcon(printer.print2D(noise2D));
        } else {
            img = new ImageIcon(printer.print2D(noise3D[0]));
            depthSlider.setMaximum(depth - 1);
            depthSlider.setLabelTable(null);
            depthSlider.setMajorTickSpacing((int) (depth / 5));
            depthSlider.setMinorTickSpacing(1);
        }

        imagePanel.removeAll();
        imagePanel.add(new JLabel(img), BorderLayout.NORTH);

        notificationLabel.setText("");
        statsButton.setEnabled(true);
        frame.pack();
    }

    /**
     * Generates a random seed and sets it in the appropriate field.
     */
    private void randomSeed() {
        LCGRandom rand = new LCGRandom(System.nanoTime());
        int seed = (int) rand.getRandom();

        if (seed < 0) {
            seed = -seed;
        }

        seedField.setText(seed + "");
    }

    /**
     * Opens a new window and shows statistics about the last generated noise array.
     */
    private void showStats() {
        if (statsFrame != null) {
            statsFrame.dispose();
        }

        statsFrame = new JFrame("statistics");
        statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statsFrame.setLocationRelativeTo(null);
        statsFrame.getContentPane().setLayout(new BorderLayout());

        JPanel textStatPanel = new JPanel();
        textStatPanel.setLayout(new BoxLayout(textStatPanel, BoxLayout.PAGE_AXIS));
        statsFrame.getContentPane().add(textStatPanel, BorderLayout.WEST);

        textStatPanel.add(new JLabel("Time taken to generate: " + (generationTime / 1_000_000.0) + "ms"));
        textStatPanel.add(new JLabel(" "));

        if (noise2D != null) {
            textStatPanel.add(new JLabel("Dimension: 2D"));
            textStatPanel.add(new JLabel(" "));
            textStatPanel.add(new JLabel("Points in range 0.0 - 0.2: " + ArrayStats.pointsInRange2D(noise2D, 0.0, 0.2)));
            textStatPanel.add(new JLabel("Points in range 0.2 - 0.4: " + ArrayStats.pointsInRange2D(noise2D, 0.2, 0.4)));
            textStatPanel.add(new JLabel("Points in range 0.4 - 0.6: " + ArrayStats.pointsInRange2D(noise2D, 0.4, 0.6)));
            textStatPanel.add(new JLabel("Points in range 0.6 - 0.8: " + ArrayStats.pointsInRange2D(noise2D, 0.6, 0.8)));
            textStatPanel.add(new JLabel("Points in range 0.8 - 1.0: " + ArrayStats.pointsInRange2D(noise2D, 0.8, 1.0)));
            textStatPanel.add(new JLabel(" "));
            textStatPanel.add(new JLabel("Largest single value: " + ArrayStats.largestValue2D(noise2D)));
            textStatPanel.add(new JLabel("Smallest single value: " + ArrayStats.smallestValue2D(noise2D)));
        } else {
            textStatPanel.add(new JLabel("Dimension: 3D"));
            textStatPanel.add(new JLabel(" "));
            textStatPanel.add(new JLabel("Points in range 0.0 - 0.2: " + ArrayStats.pointsInRange3D(noise3D, 0.0, 0.2)));
            textStatPanel.add(new JLabel("Points in range 0.2 - 0.4: " + ArrayStats.pointsInRange3D(noise3D, 0.2, 0.4)));
            textStatPanel.add(new JLabel("Points in range 0.4 - 0.6: " + ArrayStats.pointsInRange3D(noise3D, 0.4, 0.6)));
            textStatPanel.add(new JLabel("Points in range 0.6 - 0.8: " + ArrayStats.pointsInRange3D(noise3D, 0.6, 0.8)));
            textStatPanel.add(new JLabel("Points in range 0.8 - 1.0: " + ArrayStats.pointsInRange3D(noise3D, 0.8, 1.0)));
            textStatPanel.add(new JLabel(" "));
            textStatPanel.add(new JLabel("Largest single value: " + ArrayStats.largestValue3D(noise3D)));
            textStatPanel.add(new JLabel("Smallest single value: " + ArrayStats.smallestValue3D(noise3D)));
        }

        statsFrame.pack();
        statsFrame.setVisible(true);
    }

    /**
     * Initializes menu items for generation parameter inputs.
     */
    private JPanel setupItems() {
        JPanel items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.PAGE_AXIS));

        perlinButton = new JRadioButton("Perlin noise");
        worleyButton = new JRadioButton("Worley noise");
        perlinButton.setSelected(true);

        ButtonGroup algoButtons = new ButtonGroup();
        algoButtons.add(perlinButton);
        algoButtons.add(worleyButton);

        _2DButton = new JRadioButton("2D");
        _3DButton = new JRadioButton("3D");
        _2DButton.setSelected(true);

        ButtonGroup dimensionButtons = new ButtonGroup();
        dimensionButtons.add(_2DButton);
        dimensionButtons.add(_3DButton);

        widthField = new JTextField(20);
        heightField = new JTextField(20);
        depthField = new JTextField(20);
        scaleField = new JTextField(20);
        seedField = new JTextField(20);

        JPanel widthFieldPanel = new JPanel();
        widthFieldPanel.setLayout(new FlowLayout());
        widthFieldPanel.add(widthField);

        JPanel heightFieldPanel = new JPanel();
        heightFieldPanel.setLayout(new FlowLayout());
        heightFieldPanel.add(heightField);

        JPanel depthFieldPanel = new JPanel();
        depthFieldPanel.setLayout(new FlowLayout());
        depthFieldPanel.add(depthField);

        JPanel scaleFieldPanel = new JPanel();
        scaleFieldPanel.setLayout(new FlowLayout());
        scaleFieldPanel.add(scaleField);

        JPanel seedFieldPanel = new JPanel();
        seedFieldPanel.setLayout(new FlowLayout());
        seedFieldPanel.add(seedField);

        JButton randomSeedButton = new JButton("random seed");
        randomSeedButton.setActionCommand("random");
        randomSeedButton.addActionListener(this);

        JButton generateButton = new JButton("generate noise image");
        generateButton.setActionCommand("generate");
        generateButton.addActionListener(this);

        statsButton = new JButton("image statistics");
        statsButton.setEnabled(false);
        statsButton.setActionCommand("stats");
        statsButton.addActionListener(this);

        items.add(new JLabel("algorithm:"));
        items.add(perlinButton);
        items.add(worleyButton);
        items.add(new JLabel("generation dimensions:"));
        items.add(_2DButton);
        items.add(_3DButton);
        items.add(new JLabel(" "));
        items.add(new JLabel("width:"));
        items.add(widthFieldPanel);
        items.add(new JLabel("height:"));
        items.add(heightFieldPanel);
        items.add(new JLabel("depth:"));
        items.add(depthFieldPanel);
        items.add(new JLabel("scale (try values < 0.1):"));
        items.add(scaleFieldPanel);
        items.add(new JLabel("seed:"));
        items.add(seedFieldPanel);
        items.add(randomSeedButton);
        items.add(new JLabel(" "));
        items.add(generateButton);
        items.add(new JLabel(" "));
        items.add(statsButton);

        return items;
    }
}