package Ierarhy;

import java.awt.*;

public class Line implements Drawable {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color = Color.black;

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        Color old = g.getColor();
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
        g.setColor(old);
    }

    @Override
    public Drawable resetByDots(Point[] points) {
        this.x1 = points[0].x;
        this.y1 = points[0].y;
        this.x2 = points[1].x;
        this.y2 = points[1].y;
        return new Line(this.x1, this.y1, this.x2, this.y2);
    }

    @Override
    public int getDots() {
        return 2;
    }

}
