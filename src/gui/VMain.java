package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class VMain extends JFrame {

    private final int rows;
    private final int cols;

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private VPaintPanel buttons;
    private VPaintPanel answer;

    private JButton submitButton;
    public static boolean IS_COMPLETE = false;

    private PropertyChangeSupport pCS = new PropertyChangeSupport(this);

    public VMain(int rows, int cols) {
        super("Sign recoginize");

        this.rows = rows;
        this.cols = cols;

        setMainPanel();
        setLeftPanel();
        setRightPanel();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean oldIS_COMPLETE = IS_COMPLETE;
                IS_COMPLETE = true;
                pCS.firePropertyChange("IS_COMPLETE",oldIS_COMPLETE,IS_COMPLETE);
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pCS.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pCS.removePropertyChangeListener(listener);
    }

    private void setMainPanel() {
        mainPanel = new JPanel(new BorderLayout(10,10));
        submitButton = new JButton("RECOGNIZE");
        JLabel label = new JLabel("Symbol recognizer",SwingConstants.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(label,BorderLayout.PAGE_START);
        mainPanel.add(submitButton, BorderLayout.PAGE_END);
        setContentPane(mainPanel);
    }

    private void setLeftPanel() {
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Draw:"), BorderLayout.PAGE_START);
        buttons = new VPaintPanel(rows, cols, true);
        leftPanel.add(buttons);
        mainPanel.add(leftPanel, BorderLayout.LINE_START);
    }

    private void setRightPanel() {
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("I see:"), BorderLayout.PAGE_START);
        answer = new VPaintPanel(rows, cols, false);
        rightPanel.add(answer);
        mainPanel.add(rightPanel, BorderLayout.LINE_END);
    }

    public ArrayList<Double> getButtonsValues() {
        return buttons.getPixelsValuesArray();
    }

    public void showResult(ArrayList<Double> output) {
        answer.paintPixels(output);
    }
}
