package Ierarhy;

import java.awt.*;

public interface Drawable {
    void draw(Graphics g);

    Drawable resetByDots(Point[] points);

    int getDots();

    void setColor(Color color);
}
