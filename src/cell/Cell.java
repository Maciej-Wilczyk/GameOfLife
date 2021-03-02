package cell;

import javafx.scene.paint.Color;

public class Cell {
    Color color;
    int wi;
    int hi;

    public Cell() {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWi() {
        return wi;
    }

    public void setWi(int wi) {
        this.wi = wi;
    }

    public int getHi() {
        return hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }
}
