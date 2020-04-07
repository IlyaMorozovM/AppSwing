package Ierarhy;

import java.awt.*;

public class Circle extends Ellipse{

    public Circle(int x, int y, int rad) {
        super(x - rad, y - rad, x + rad, y + rad);
    }

    public Circle() {
        super(0, 0, 0, 0);
    }

    @Override
    public Drawable resetByDots(Point[] points) {
        this.setX1(points[0].x);
        this.setY1(points[0].y);
        return new Circle(points[0].x, points[0].y, (int) Math.round(Math.sqrt((points[1].y - points[0].y) * (points[1].y - points[0].y) + (points[1].x - points[0].x) * (points[1].x - points[0].x))));
    }

    @Override
    public int getDots() {
        return 2;
    }

}
