package Ierarhy;

import java.awt.*;

public class Ellipse extends Line {

    public Ellipse(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
        Color old = g.getColor();
        g.setColor(getColor());
        g.drawOval(this.getX1(), this.getY1(), Math.abs(getX2() - getX1()), Math.abs(getY2() - getY1()));
        g.setColor(old);
    }

    @Override
    public Drawable resetByDots(Point[] points) {
        return new Ellipse(points[0].x, points[0].y, points[1].x, points[1].y);
    }

    @Override
    public int getDots() {
        return 2;
    }
}
