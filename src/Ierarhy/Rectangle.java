package Ierarhy;

import java.awt.*;

public class Rectangle extends Line{
    private Line left;
    private Line top;
    private Line bot;
    private Line right;

    public Rectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        left = new Line(this.getX1(), this.getY1(), this.getX1(), this.getY2());
        top = new Line(this.getX1(), this.getY1(), this.getX2(), this.getY1());
        bot = new Line(x2, this.getY2(), this.getX1(), this.getY2());
        right = new Line(this.getX2(), this.getY2(), this.getX2(), this.getY1());
    }


    @Override
    public void draw(Graphics g) {
        left.setColor(getColor());
        top.setColor(getColor());
        bot.setColor(getColor());
        right.setColor(getColor());
    left.draw(g);
    top.draw(g);
    bot.draw(g);
    right.draw(g);
    }

    @Override
    public Drawable resetByDots(Point[] points) {
        this.setX1(points[0].x);
        this.setY1(points[0].y);
        this.setX2(points[1].x);
        this.setY2(points[1].y);
        left = new Line(this.getX1(), this.getY1(), this.getX1(), this.getY2());
        top = new Line(this.getX1(), this.getY1(), this.getX2(), this.getY1());
        bot = new Line(this.getX2(), this.getY2(), this.getX1(), this.getY2());
        right = new Line(this.getX2(), this.getY2(), this.getX2(), this.getY1());
        return new Rectangle(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }

    @Override
    public int getDots() {
        return 2;
    }
}
