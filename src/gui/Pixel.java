package gui;

import java.awt.*;

/**
 * Created by marek on 2017-02-07.
 */
public class Pixel {

    private final int x;
    private final int y;
    private int width = 20;
    private int height = 20;

    private Color color = Color.WHITE;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paintPixel(boolean paint) {
        if (paint) {
            color = Color.BLACK;
        } else {
            color = Color.WHITE;
        }
    }

    public boolean isPainted() {
        return color == Color.BLACK;
    }
}
