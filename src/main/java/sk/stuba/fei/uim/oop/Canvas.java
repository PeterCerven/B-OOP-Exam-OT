package sk.stuba.fei.uim.oop;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseMotionListener {

    private final List<Point> points;

    private int length;
    private int radius;
    private int spacing;
    private Shape shape;

    public Canvas() {
        this.points = new ArrayList<>();
        this.length = App.INITIAL_LENGTH;
        this.radius = App.INITIAL_RADIUS;
        this.spacing = App.INITIAL_SPACING;
        this.shape = App.INITIAL_SHAPE;

        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = points.size() - 1; i >= 0; i--) {
            g.setColor(computeColor(i));

            Point point = points.get(i);

            if (i % this.spacing == 0 || i == points.size() - 1) {
                this.shape.paint(g, point, this.radius);
            }

            if (i != 0) {
                Point nextPoint = points.get(i - 1);
                g.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
            }
        }
    }

    private Color computeColor(int i) {
        float gradient = i / (this.length - 1.0f);
        return Color.getHSBColor(gradient, 1, 1);
//        return new Color(1 - gradient, 1 - gradient, gradient);
//        return new Color(1-gradient, 0, 0);
    }

    public void setLength(int length) {
        this.length = length;
        this.trimPoints();
        this.repaint();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        this.repaint();
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
        this.repaint();
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        points.add(0, e.getPoint());
        trimPoints();
        this.repaint();
    }

    private void trimPoints() {
        while (points.size() > length) {
            points.remove(points.size() - 1);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseMoved(e);
    }
}
