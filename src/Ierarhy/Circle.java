package Ierarhy;

import java.awt.*;

public class Circle extends Ellipse{

    public Circle(int x, int y, int rad) {
        super(x - rad, y - rad, x + rad, y + rad);
    }

}
