import java.util.ArrayList;

/**
 * Created by marek on 2016-12-27.
 */
public class Network {

    public static double errorMargin = 0.1;

    private final int inputCount;
    private final int outputNeuronsCount;
    private final int hiddenNeuronsCount;
    private ArrayList<ArrayList<Double>> patterns;
    private ArrayList<Neuron> outputNeurons;
    private ArrayList<Neuron> hiddenNeurons;

    public Network(int numberOfInputs, int numberOfHiddenNeurons, int numberOfOutputNeurons) {
        inputCount = numberOfInputs;
        hiddenNeuronsCount = numberOfHiddenNeurons;
        outputNeuronsCount = numberOfOutputNeurons;
        outputNeurons = new ArrayList<>();
        hiddenNeurons = new ArrayList<>();
        for (int i = 0; i< outputNeuronsCount; i++) {
            outputNeurons.add(new Neuron(hiddenNeuronsCount));
        }
        for (int i=0; i<hiddenNeuronsCount; i++) {
            hiddenNeurons.add(new Neuron(numberOfInputs));
        }
    }

    public void trainNetwork(ArrayList<ArrayList<Double>> patterns) {
        this.patterns = patterns;

        int epochNumber = 0;
        double error = 0;
        boolean errorFlag = true;
        ArrayList<Double> output = new ArrayList<>();

        while (errorFlag) {
            System.out.println();
            System.out.println("##############################################################");
            System.out.println("epoch:"+epochNumber++);
            errorFlag = false;
            error = 0;

            for (int i = 0; i< patterns.size(); i++) {
                ArrayList<Double> neuronsInput = new ArrayList<>();
                ArrayList<Double> neuronsErrors = new ArrayList<>();
                for (int j=0; j<hiddenNeuronsCount; j++) {
                    double weightedSum = hiddenNeurons.get(j).calculateWeightedSum(patterns.get(i));
                    double result = hiddenNeurons.get(j).applyActivationFunction(weightedSum);
                    System.out.println("Result "+i+j+": "+result);
                    neuronsInput.add(j,result);
                }
                ArrayList<Double> outputs = new ArrayList<>();
                for (int k = 0; k< outputNeuronsCount; k++) {
                    double weightedSum = outputNeurons.get(k).calculateWeightedSum(neuronsInput);
                    double result = outputNeurons.get(k).applyActivationFunction(weightedSum);
                    System.out.println("NEURON result"+k+": "+result);
                    outputs.add(k,result);
                    if (k == i) {
                        error = 1 - result;
                    } else {
                        error = 0 - result;
                    }
                    neuronsErrors.add(k,error);
                    if (Math.abs(error) > errorMargin)
                        errorFlag = true;
                    outputNeurons.get(k).adjustWeights(neuronsInput,error);
                }
                for (int j=0; j<hiddenNeuronsCount; j++) {
                    double errorHN = 0.0;
                    for (int k = 0; k< outputNeuronsCount; k++) {
                        ArrayList<Double> neuronWeights = outputNeurons.get(k).getWeights();
                        errorHN += neuronsErrors.get(k)*neuronWeights.get(j);
                    }
                    hiddenNeurons.get(j).adjustWeights(patterns.get(i),errorHN);
                }
            }

        }
    }

    public ArrayList<Double> getAnswer(ArrayList<Double> input) {
        ArrayList<Double> results = new ArrayList<>();

        ArrayList<Double> neuronsInput = new ArrayList<>();
        for (int i=0; i<hiddenNeuronsCount; i++) {
            double weightedSum = hiddenNeurons.get(i).calculateWeightedSum(input);
            neuronsInput.add(i,hiddenNeurons.get(i).applyActivationFunction(weightedSum));
        }
        for (int i = 0; i< outputNeuronsCount; i++) {
            double weightedSum = outputNeurons.get(i).calculateWeightedSum(neuronsInput);
            results.add(i,outputNeurons.get(i).applyActivationFunction(weightedSum));
        }

        return results;
    }
}
