import data.ConfigReader;
import data.PatternFileReader;
import gui.VMain;
import network.Network;
import network.Neuron;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by marek on 2016-12-23.
 */
public class CMain {

    private static final String dataSetName = "resources/x,o,-,+/";

    private static final ArrayList<String> propertyNames = new ArrayList<String>(Arrays.asList(
            "rows","cols","numberOfPatterns","numberOfHiddenNeurons","errorMargin","learningRatio","betaRate"
    ));
    private static int numberOfPatterns = 3;  //numberOfOutputs
    private static int rows = 3;
    private static int cols = 3;
    private static int numbersOfInputs;
    private static int numberOfHiddenNeurons = 5; //>= numberOfPatterns

    private static VMain window;
    private static Network network;

    private static ArrayList<String> patternsSymbols;
    private static ArrayList<ArrayList<Double>> patterns;

    public static final void main(String[] args){
        loadProperties();
        readPatterns();
        network = new Network(numbersOfInputs,numberOfHiddenNeurons, numberOfPatterns);
        network.trainNetwork(patterns);
        window = new VMain(rows, cols);
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
        System.out.println("network.Network answer:");
        for (int i = 0; i< numberOfPatterns; i++) {
            System.out.println(patternsSymbols.get(i)+": "+networkAnswer.get(i));
        }
        int maxIndex = 0;
        for (int i = 1; i< numberOfPatterns; i++) {
            if (networkAnswer.get(i) > networkAnswer.get(maxIndex)) {
                maxIndex = i;
            }
        }
        window.showResult(patterns.get(maxIndex));
    }

    private static void readPatterns() {
        patterns = new ArrayList<>();
        patternsSymbols = new ArrayList<>();
        for (int i = 0; i< numberOfPatterns; i++) {
            String filename = dataSetName + "Pattern" + (i+1) + ".txt";
            patternsSymbols.add(i, PatternFileReader.getPatternSymbol(filename));
            ArrayList<Double> input = PatternFileReader.readFromFile(filename);
            patterns.add(i,input);
        }
    }
    
    private static void loadProperties() {
        ConfigReader reader = new ConfigReader(dataSetName+"config.txt",propertyNames);
        Integer integerValue;
        Double doubleValue;
        
        integerValue = reader.loadIntegerProperty("rows");
        if (integerValue!=null)
            rows = integerValue;

        integerValue = reader.loadIntegerProperty("cols");
        if (integerValue!=null)
            cols = integerValue;

        integerValue = reader.loadIntegerProperty("numberOfPatterns");
        if (integerValue!=null)
            numberOfPatterns = integerValue;

        integerValue = reader.loadIntegerProperty("numberOfHiddenNeurons");
        if (integerValue!=null)
            numberOfHiddenNeurons = integerValue;

        doubleValue = reader.loadDoubleProperty("errorMargin");
        if (doubleValue!=null)
            Network.errorMargin = doubleValue;

        doubleValue = reader.loadDoubleProperty("learningRatio");
        if (doubleValue!=null)
            Neuron.LEARNING_RATIO = doubleValue;

        doubleValue = reader.loadDoubleProperty("betaRate");
        if (doubleValue!=null)
            Neuron.BETA_RATE = doubleValue;

        numbersOfInputs = rows * cols;
    }
}
