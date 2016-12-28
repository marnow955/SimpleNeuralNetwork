import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class VPaintPanel extends JPanel{

    private final int size;
    private final int panelSize;
    private ArrayList<JButton> buttons;

    public VPaintPanel (int size, boolean isEnabled) {
        setLayout(new GridLayout(size,size));
        this.size = size;
        panelSize = size*size;
        buttons = new ArrayList<>();
        for (int i=0; i<panelSize; i++){
            buttons.add(i,new JButton());
        }
        setButtons();
        if (!isEnabled) {
            setButtonsDisabled();
            return;
        }
        setListeners();
    }

    public ArrayList<Integer> getPixelsValuesArray() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<panelSize; i++) {
            list.add(i,buttons.get(i).isContentAreaFilled()? 1:0);
        }
        return list;
    }

    public void paintPixels(ArrayList<Integer> output) {
        for (int i=0; i<output.size(); i++) {
            if (output.get(i)>0){
                buttons.get(i).setContentAreaFilled(true);
            } else {
                buttons.get(i).setContentAreaFilled(false);
            }
        }
    }

    private void setButtons(){
        for (int i=0; i<panelSize; i++) {
            buttons.get(i).setPreferredSize(new Dimension(30,30));
            buttons.get(i).setFocusPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setMargin(new Insets(0,0,0,0));
            buttons.get(i).setBackground(Color.black);
            add(buttons.get(i));
        }
    }

    private void setListeners() {
        for (int i=0; i<panelSize; i++) {
            int finalI = i;
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buttons.get(finalI).isContentAreaFilled()){
                        buttons.get(finalI).setContentAreaFilled(false);
                    } else {
                        buttons.get(finalI).setContentAreaFilled(true);
                    }
                }
            });
        }
    }

    private void setButtonsDisabled() {
        for (int i=0; i<panelSize; i++) {
            buttons.get(i).setEnabled(false);
        }
    }
}
