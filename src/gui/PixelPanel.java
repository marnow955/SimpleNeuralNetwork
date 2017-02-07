package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marek on 2017-02-06.
 */
public class PixelPanel extends JPanel {

    private final int rows;
    private final int cols;
    private final int panelSize;

    private ArrayList<Pixel> pixels;

    public PixelPanel(int rows, int cols, boolean isEnabled) {
        this.rows = rows;
        this.cols = cols;
        panelSize = rows * cols;
        setLayout(new GridLayout(rows,cols));

        pixels = new ArrayList<>();
        for (int y=0; y<rows; y++) {
            for (int x=0; x<cols; x++) {
                pixels.add(y*cols+x, new Pixel(x,y));
            }
        }
    }

    public ArrayList<Double> getPixelsValuesArray() {
        ArrayList<Double> list = new ArrayList<>();
        for (int i=0; i<panelSize; i++) {
            list.add(i,pixels.get(i).isPainted()? 1.0:0.0);
        }
        return list;
    }

    public void paintPixels(ArrayList<Double> output) {
        for (int i=0; i<output.size(); i++) {
            if (output.get(i)>0) {
                pixels.get(i).paintPixel(true);
            } else {
                pixels.get(i).paintPixel(false);
            }
        }
    }
}
