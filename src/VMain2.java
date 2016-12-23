import javax.swing.*;

/**
 * Created by marek on 2016-12-23.
 */
public class VMain2 extends JFrame {
    private JButton button1;
    private JPanel contener;
    private JPanel leftPanel;
    private JPanel rightPanel;

    public VMain2() {
        super("Sign recognize");
        setContentPane(contener);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
