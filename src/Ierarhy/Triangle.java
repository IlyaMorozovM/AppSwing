package Ierarhy;

import java.awt.*;

public class Triangle extends Line{
    private Line bot;
    private Line left;
    private Line right;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        super(x1, y1, x2, y2);
        bot = new Line(this.getX1(), this.getY1(), this.getX2(), this.getY2());
        left = new Line(this.getX2(), this.getY2(), x3, y3);
        right = new Line(this.getX1(), this.getY1(), x3, y3);
    }

    @Override
    public void draw(Graphics g) {
       // super.draw(g);
        bot.draw(g);
        left.draw(g);
        right.draw(g);
    }
}
