package Ierarhy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class FigureList {
    private ArrayList<Drawable> list;
    private int xDelay = 50;

    //to the future
    public FigureList(int xDelay) {
        this.xDelay = xDelay;
        list = new ArrayList<Drawable>();
    }

    public FigureList() {
        list = new ArrayList<Drawable>();
    }

    public FigureList add(Drawable drawable){
        list.add(drawable);
        return this;
    }

    public void addAll(Collection<? extends Drawable> drawable){
        list.addAll(drawable);
    }

    public void remove(Drawable drawable){
        list.remove(drawable);
    }

    public void remove(int index){
        list.remove(index);
    }

    public void removeAll(Collection<? extends Drawable> drawable){
        list.removeAll(drawable);
    }

    public int getxDelay() {
        return xDelay;
    }

    public void setxDelay(int xDelay) {
        this.xDelay = xDelay;
    }

    public void drawList(Graphics g){
        for (Drawable d:list) {
        d.draw(g);
        }
    }
}
