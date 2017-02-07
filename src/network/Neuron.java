package network;

import java.util.ArrayList;

/**
 * Created by marek on 2016-12-27.
 */
public class Neuron {
    public static double LEARNING_RATIO = 0.05;
    public static double BETA_RATE = 1.0;
    private final int weightsCount;

    private ArrayList<Double> weights;
    private double output;

    public Neuron(int weightsCount) {
        this.weightsCount = weightsCount;
        weights = new ArrayList<>();
        initWeights();
    }

    public double calculateWeightedSum(ArrayList<Double> input) {
        output = 0.0;
        for (int i = 0; i< weightsCount; i++) {
            output += input.get(i) * weights.get(i);
        }
        return output;
    }

    public double applyActivationFunction(double weightedSum) {
        return 1/(1+Math.exp(-BETA_RATE *weightedSum));
    }

    public void adjustWeights(ArrayList<Double> input, double error) {
        ArrayList<Double> adjustedWeights = new ArrayList<>();
        for (int i = 0; i< weightsCount; i++) {
            adjustedWeights.add(i,LEARNING_RATIO*error*input.get(i)+weights.get(i));
        }
        weights = adjustedWeights;
    }
    private void initWeights() {
        for (int i = 0; i< weightsCount; i++) {
            weights.add(i, Math.random());
        }
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }
}
