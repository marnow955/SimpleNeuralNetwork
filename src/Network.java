import java.util.ArrayList;

/**
 * Created by marek on 2016-12-27.
 */
public class Network {
    private static final double LEARNING_RATIO = 0.05;

    private int neuronsCount;
    private ArrayList<Neuron> neurons;

    public Network(int size) {
        neuronsCount = size;
        neurons = new ArrayList<>();
        for (int i=0; i<neuronsCount; i++) {
            neurons.add(new Neuron(9,LEARNING_RATIO));
        }
    }

    public void trainNetwork() {
        ArrayList<Integer> xSign = PatternFileReader.readFromFile("xSign.txt");     //100
        ArrayList<Integer> oSign = PatternFileReader.readFromFile("oSign.txt");     //010
        ArrayList<Integer> minusSign = PatternFileReader.readFromFile("-Sign.txt"); //001
        ArrayList<ArrayList<Integer>> input= new ArrayList<>();
        input.add(0,xSign);
        input.add(1,oSign);
        input.add(2,minusSign);

        int epochNumber = 0;
        double error = 0;
        boolean errorFlag = true;
        ArrayList<Double> output = new ArrayList<>();

        while (errorFlag) {
            System.out.println("epoch:"+epochNumber++);
            errorFlag = false;
            error = 0;

            for (int i=0; i<neuronsCount; i++) {
                for (int j=0; j<input.size();j++) {
                    double weightedSum = neurons.get(i).calculateWeightedSum(input.get(j));
                    System.out.println("weightesum:"+weightedSum);
                    int result = neurons.get(i).applyActivationFunction(weightedSum);
                    System.out.println("result:"+result);
                    if (i==j)
                        error = 1 - result;
                    else
                        error = 0 - result;
                    System.out.println("Error:"+error);
                    if (error!=0)
                        errorFlag = true;
                    neurons.get(i).adjustWeights(input.get(j),error);
                }
            }
        }
    }

    public ArrayList<Integer> getAnswer(ArrayList<Integer> input) {
        ArrayList<Integer> output = new ArrayList<>();
        int[] results = new int[3];
        for (int i=0; i<3; i++) {
            double weightedSum = neurons.get(i).calculateWeightedSum(input);
            results[i] = neurons.get(i).applyActivationFunction(weightedSum);
        }
        System.out.println("Results: "+results[0]+" "+ results[1]+" "+results[2]);
        if (results[0] == 1){
            output = PatternFileReader.readFromFile("xSign.txt");
        } else if (results[1] == 1) {
            output = PatternFileReader.readFromFile("oSign.txt");
        } else if (results[2] == 1)
            output = PatternFileReader.readFromFile("-Sign.txt");
        return output;
    }
}
