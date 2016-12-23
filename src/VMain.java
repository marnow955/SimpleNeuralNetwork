import javax.swing.*;

/**
 * Created by marek on 2016-12-23.
 */
public class VMain extends JFrame {

    private final int signSize = 3;

    private JPanel mainPanel;
    private JPanel leftPanel;
    private VPaintPanel buttons;
    private JPanel rightPanel;

    private JButton submitButton;

    public VMain() {
        super("Sign recoginize");
        setSize(1000,500);

        setMainPanel();
        setLeftPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        setContentPane(mainPanel);
    }

    private void setLeftPanel() {
        leftPanel = new JPanel();
        buttons = new VPaintPanel(signSize);
        leftPanel.add(buttons);
        mainPanel.add(leftPanel);
    }
}
