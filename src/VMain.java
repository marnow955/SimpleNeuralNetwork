import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class VMain extends JFrame {

    private final int signSize;

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private VPaintPanel buttons;
    private VPaintPanel answer;

    private JButton submitButton;
    public boolean IS_COMPLETE = false;

    public VMain(int signSize) {
        super("Sign recoginize");

        this.signSize = signSize;
        setMainPanel();
        setLeftPanel();
        setRightPanel();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IS_COMPLETE = true;
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
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
        buttons = new VPaintPanel(signSize, true);
        leftPanel.add(buttons);
        mainPanel.add(leftPanel, BorderLayout.LINE_START);
    }

    private void setRightPanel() {
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("I see:"), BorderLayout.PAGE_START);
        answer = new VPaintPanel(signSize, false);
        rightPanel.add(answer);
        mainPanel.add(rightPanel, BorderLayout.LINE_END);
    }

    public ArrayList<Integer> getButtonsValues() {
        return buttons.getPixelsValuesArray();
    }

    public void showResult(ArrayList<Integer> output) {
        answer.paintPixels(output);
    }
}
