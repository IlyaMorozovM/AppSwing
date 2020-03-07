package Ierarhy;

public class Square extends Rectangle{
    public Square(int x, int y, int a) {
        super(x - a / 2, y - a / 2, x + a / 2, y + a / 2);
    }
}
