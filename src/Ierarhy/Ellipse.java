package Ierarhy;

import java.awt.*;

public class Ellipse extends Line {

    public Ellipse(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(this.getX1(), this.getY1(), Math.abs(getX2() - getX1()), Math.abs(getY2() - getY1()));
    }
}
