package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ChangeListener, ActionListener {

    public static final int INITIAL_LENGTH = 50;
    public static final int INITIAL_RADIUS = 5;
    public static final int INITIAL_SPACING = 5;
    public static final Shape INITIAL_SHAPE = Shape.CIRCLE;


    private final Canvas canvas;

    private final JSlider length;
    private final JSlider radius;
    private final JSlider spacing;

    public App() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.canvas = new Canvas();

        this.add(canvas, BorderLayout.CENTER);

        JPanel sideMenu = new JPanel(new BorderLayout());

        JPanel labels = new JPanel(new GridLayout(1, 3));
        labels.add(new JLabel("Length"));
        labels.add(new JLabel("Radius"));
        labels.add(new JLabel("Spacing"));
        sideMenu.add(labels, BorderLayout.NORTH);

        JPanel sliders = new JPanel(new GridLayout(1, 3));
        this.length = this.createSlider(20, 200, INITIAL_LENGTH, 10);
        this.radius = this.createSlider(1, 20, INITIAL_RADIUS, 1);
        this.spacing = this.createSlider(1, 20, INITIAL_SPACING, 1);
        sliders.add(this.length);
        sliders.add(this.radius);
        sliders.add(this.spacing);
        sideMenu.add(sliders, BorderLayout.CENTER);

        JComboBox<Shape> shape = new JComboBox<>();
        shape.addItem(Shape.CIRCLE);
        shape.addItem(Shape.SQUARE);
        shape.addItem(Shape.HOURGLASS);
        shape.setSelectedItem(INITIAL_SHAPE);
        shape.addActionListener(this);
        sideMenu.add(shape, BorderLayout.SOUTH);

        this.add(sideMenu, BorderLayout.EAST);

        this.setSize(new Dimension(700, 700));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(this.length)) {
            this.canvas.setLength(this.length.getValue());
        } else if (e.getSource().equals(this.radius)) {
            this.canvas.setRadius(this.radius.getValue());
        } else if (e.getSource().equals(this.spacing)) {
            this.canvas.setSpacing(this.spacing.getValue());
        }
    }

    private JSlider createSlider(int min, int max, int initial, int spacing) {
        JSlider result = new JSlider();
        result.setMaximum(max);
        result.setMinimum(min);
        result.setMajorTickSpacing(spacing);
        result.setMinorTickSpacing(spacing);
        result.setPaintTicks(true);
        result.setPaintLabels(true);
        result.setValue(initial);
        result.setSnapToTicks(true);
        result.addChangeListener(this);
        result.setOrientation(JSlider.VERTICAL);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.canvas.setShape((Shape) ((JComboBox<?>) e.getSource()).getSelectedItem());
    }
}
