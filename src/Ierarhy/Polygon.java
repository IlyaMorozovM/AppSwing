package Ierarhy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Polygon extends Line {

    private ArrayList<Point> pointsList;

    public ArrayList<Point> getPointsList() {
        return pointsList;
    }

    public void setPointsList(ArrayList<Point> pointsList) {
        this.pointsList = pointsList;
    }

    @Override
    public void draw(Graphics g) {
        Point lastPoint = pointsList.get(pointsList.size() - 1);
        for (Point point:pointsList){
            Line l = new Line(lastPoint.x, lastPoint.y, point.x, point.y);
            lastPoint = point;
            l.setColor(getColor());
            l.draw(g);
        }
    }

    public Polygon() {
        super(0,0,0,0);
    }

    public Polygon(Point ... points) {
        super(points[0].x, points[0].y, points[1].x, points[1].y);
        assert pointsList != null;
        pointsList = new ArrayList<Point>();
        Collections.addAll(pointsList, points);
    }

    @Override
    public Drawable resetByDots(Point[] points) {
        return new Polygon(points);
    }

    @Override
    public int getDots() {
        if (pointsList != null) {
            return pointsList.size();
        }
        else
        return -3;
    }
}
