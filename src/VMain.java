import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class VMain extends JFrame {

    private final int signSize = 3;

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private VPaintPanel buttons;
    private VPaintPanel answer;

    private JButton submitButton;

    public VMain() {
        super("Sign recoginize");

        setMainPanel();
        setLeftPanel();
        setRightPanel();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> inputMatrix = buttons.getPixelsValuesArray();
                MMain calc = new MMain();
                ArrayList<Integer> input = buttons.getPixelsValuesArray();
                Matrix inputM = new Matrix(9,1);
                for (int i=0; i<input.size(); i++) {
                    inputM.add(i,0,input.get(i));
                }
                Matrix result = calc.getAnswer(inputM);
                ArrayList<Integer> output = new ArrayList<Integer>();
                for (int i=0; i<result.getNumberOfRows(); i++){
                    for (int j=0; j<result.getNumberOfColumns(); j++){
                        output.add(i*result.getNumberOfColumns()+j,result.get(i,j));
                    }
                }
                answer.paintPixels(output);
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
}
