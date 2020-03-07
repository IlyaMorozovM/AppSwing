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
    left.draw(g);
    top.draw(g);
    bot.draw(g);
    right.draw(g);
    }
}
