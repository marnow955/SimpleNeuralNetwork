import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class CMain {

    private static final int numberOfPattern = 3;  //numberOfOutputs
    private static final int rows = 3;
    private static final int cols = 3;
    private static final int numbersOfInputs = rows * cols;
    private static final int numberOfHiddenNeurons = 5; //>= numberOfPatterns

    private static VMain window;
    private static Network network;

    private static ArrayList<String> patternsSymbols;
    private static ArrayList<ArrayList<Double>> patterns;

    public static final void main(String[] args){
        window = new VMain(rows, cols);
        network = new Network(numbersOfInputs,numberOfHiddenNeurons,numberOfPattern);
        patterns = new ArrayList<>();
        patternsSymbols = new ArrayList<>();
        for (int i=0; i<numberOfPattern; i++) {
            String filename = "Pattern" + (i+1) + ".txt";
            patternsSymbols.add(i,PatternFileReader.getPatternSymbol(filename));
            ArrayList<Double> input = PatternFileReader.readFromFile(filename);
            patterns.add(i,input);
        }
        network.trainNetwork(patterns);
        listenSubmitButton();
    }

    public static void listenSubmitButton() {
        window.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (window.IS_COMPLETE){
                    calculateAnswer();
                    window.IS_COMPLETE = false;
                }
            }
        });
    }

    public static void calculateAnswer() {
        ArrayList<Double> input = window.getButtonsValues();

        ArrayList<Double> networkAnswer = network.getAnswer(input);
        System.out.println("Network answer:");
        for (int i=0; i<numberOfPattern; i++) {
            System.out.println(patternsSymbols.get(i)+": "+networkAnswer.get(i));
        }
        int maxIndex = 0;
        for (int i=1; i<numberOfPattern; i++) {
            if (networkAnswer.get(i) > networkAnswer.get(maxIndex)) {
                maxIndex = i;
            }
        }
        window.showResult(patterns.get(maxIndex));
    }
}
