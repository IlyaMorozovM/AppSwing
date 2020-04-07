package Ierarhy;

import java.awt.*;

public class Square extends Rectangle{
    public Square(int x, int y, int a) {
        super(x - a / 2, y - a / 2, x + a / 2, y + a / 2);
    }

    @Override
    public Drawable resetByDots(Point[] points) {
        this.setX1(points[0].x);
        this.setY1(points[0].y);
        this.setX2(points[1].x);
        this.setY2(points[1].y);
        return new Square(points[0].x, points[0].y, Math.max(Math.abs(points[0].x - points[1].x), Math.abs(points[0].y - points[1].y)) * 2);
    }

    @Override
    public int getDots() {
        return 2;
    }
}
