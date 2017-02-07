package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marek on 2017-02-06.
 */
public class PixelPanel extends JPanel {

    private final int rows;
    private final int cols;
    private final int panelSize;

    public PixelPanel(int rows, int cols, boolean isEnabled) {
        this.rows = rows;
        this.cols = cols;
        panelSize = rows * cols;

        setLayout(new GridLayout(rows,cols));
    }
}
