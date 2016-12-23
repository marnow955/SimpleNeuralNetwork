import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class VPaintPanel extends JPanel{

    private final int panelSize;
    private ArrayList<JButton> buttons;

    public VPaintPanel (int size){
        setLayout(new GridLayout(3,3));
        panelSize = size*size;
        buttons = new ArrayList<>();
        for (int i=0; i<panelSize; i++){
            buttons.add(i,new JButton());
        }
        setButtons();
    }

    public void setButtons(){
        for (int i=0; i<panelSize; i++) {
            buttons.get(i).setPreferredSize(new Dimension(30,30));
            buttons.get(i).setFocusPainted(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setMargin(new Insets(0,0,0,0));
            add(buttons.get(i));
        }
    }
}
