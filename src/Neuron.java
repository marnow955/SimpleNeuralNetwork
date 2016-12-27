import java.util.ArrayList;

/**
 * Created by marek on 2016-12-27.
 */
public class Neuron {
    private final double LEARNING_RATIO;
    private final int size;

    private ArrayList<Double> weights;
    private double output;

    public Neuron(int size, double learningRatio) {
        this.size = size;
        LEARNING_RATIO = learningRatio;
        weights = new ArrayList<>();
        initWeights();
    }

    public double calculateWeightedSum(ArrayList<Integer> input) {
        output = 0.0;
        for (int i=0; i<size; i++) {
            output += input.get(i) * weights.get(i);
        }
        return output;
    }

    public int applyActivationFunction(double weightedSum) {
        int result = 0;
        if (weightedSum>1)
            result = 1;
        return result;
    }

    public void adjustWeights(ArrayList<Integer> input, double error) {
        ArrayList<Double> adjustedWeights = new ArrayList<>();
        for (int i=0; i<size; i++) {
            adjustedWeights.add(i,LEARNING_RATIO*error*input.get(i)+weights.get(i));
        }
        weights = adjustedWeights;
    }
    private void initWeights() {
        for (int i=0; i<size; i++) {
            weights.add(i, Math.random());
        }
    }
}
