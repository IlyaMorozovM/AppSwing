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
        this.setX1(points[0].x);
        this.setY1(points[0].y);
        this.setX2(points[1].x);
        this.setY2(points[1].y);
        return new Ellipse(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }

    @Override
    public int getDots() {
        return 2;
    }
}
