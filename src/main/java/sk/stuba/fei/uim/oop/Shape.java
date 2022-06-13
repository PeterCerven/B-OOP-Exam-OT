package sk.stuba.fei.uim.oop;

import java.awt.*;

public enum Shape {

    CIRCLE,
    SQUARE,
    HOURGLASS;

    public void paint(Graphics g, Point center, int radius) {
        if (this.equals(CIRCLE)) {
            g.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
        } else if (this.equals(SQUARE)) {
            g.fillRect(center.x - radius, center.y - radius, radius * 2, radius * 2);
        } else if (this.equals(HOURGLASS)) {
            g.fillPolygon(
                    new int[]{center.x - radius, center.x, center.x - radius, center.x + radius, center.x, center.x + radius},
                    new int[]{center.y - radius, center.y, center.y + radius, center.y + radius, center.y, center.y - radius},
                    6);
        }
    }
}
