import java.util.ArrayList;

/**
 * Created by marek on 2016-12-27.
 */
public class Network {

    private int neuronsCount;
    private int hiddenNeuronsCount = 5;
    private ArrayList<Neuron> neurons;
    private ArrayList<Neuron> hiddenNeurons;

    public Network(int size) {
        neuronsCount = size;
        neurons = new ArrayList<>();
        hiddenNeurons = new ArrayList<>();
        for (int i=0; i<neuronsCount; i++) {
            neurons.add(new Neuron(5));
        }
        for (int i=0; i<hiddenNeuronsCount; i++) {
            hiddenNeurons.add(new Neuron(9));
        }
    }

    public void trainNetwork() {
        ArrayList<Double> xSign = PatternFileReader.readFromFile("xSign.txt");     //100
        ArrayList<Double> oSign = PatternFileReader.readFromFile("oSign.txt");     //010
        ArrayList<Double> minusSign = PatternFileReader.readFromFile("-Sign.txt"); //001
        ArrayList<ArrayList<Double>> input= new ArrayList<>();
        input.add(0,xSign);
        input.add(1,oSign);
        input.add(2,minusSign);

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

            for (int i=0; i<input.size(); i++) {
                ArrayList<Double> neuronsInput = new ArrayList<>();
                ArrayList<Double> neuronsErrors = new ArrayList<>();
                for (int j=0; j<hiddenNeuronsCount; j++) {
                    double weightedSum = hiddenNeurons.get(j).calculateWeightedSum(input.get(i));
                    double result = hiddenNeurons.get(j).applyActivationFunction(weightedSum);
                    System.out.println("Result "+i+j+": "+result);
                    neuronsInput.add(j,result);
                }
                ArrayList<Double> outputs = new ArrayList<>();
                for (int k=0; k<neuronsCount; k++) {
                    double weightedSum = neurons.get(k).calculateWeightedSum(neuronsInput);
                    double result = neurons.get(k).applyActivationFunction(weightedSum);
                    System.out.println("NEURON result"+k+": "+result);
                    outputs.add(k,result);
                    if (k == i) {
                        error = 1 - result;
                    } else {
                        error = 0 - result;
                    }
                    neuronsErrors.add(k,error);
                    if (Math.abs(error) >= 0.1)
                        errorFlag = true;
                    neurons.get(k).adjustWeights(neuronsInput,error);
                }
                for (int j=0; j<hiddenNeuronsCount; j++) {
                    double errorHN = 0.0;
                    for (int k=0; k<neuronsCount; k++) {
                        ArrayList<Double> neuronWeights = neurons.get(k).getWeights();
                        errorHN += neuronsErrors.get(k)*neuronWeights.get(j);
                    }
                    hiddenNeurons.get(j).adjustWeights(input.get(i),errorHN);
                }
            }

//            for (int i=0; i<neuronsCount; i++) {
//                for (int j=0; j<input.size();j++) {
//                    double weightedSum = neurons.get(i).calculateWeightedSum(input.get(j));
//                    System.out.println("weightesum:"+weightedSum);
//                    double result = neurons.get(i).applyActivationFunction(weightedSum);
//                    System.out.println("result:"+result);
//                    if (i==j)
//                        error = 1 - result;
//                    else
//                        error = 0 - result;
//                    System.out.println("Error:"+error);
//                    if (error!=0)
//                        errorFlag = true;
//                    neurons.get(i).adjustWeights(input.get(j),error);
//                }
//            }
        }
    }

    public ArrayList<Double> getAnswer(ArrayList<Double> input) {
        ArrayList<Double> output = new ArrayList<>();

        double[] results = new double[neuronsCount];
        ArrayList<Double> neuronsInput = new ArrayList<>();
        for (int i=0; i<hiddenNeuronsCount; i++) {
            double weightedSum = hiddenNeurons.get(i).calculateWeightedSum(input);
            neuronsInput.add(i,hiddenNeurons.get(i).applyActivationFunction(weightedSum));
        }
        for (int i=0; i<neuronsCount; i++) {
            double weightedSum = neurons.get(i).calculateWeightedSum(neuronsInput);
            results[i] = neurons.get(i).applyActivationFunction(weightedSum);
            System.out.println("ANSWER: "+results[i]);
        }
        int maxIndex = 0;
        for (int i=1; i<neuronsCount; i++) {
            if (results[i] > results[maxIndex])
                maxIndex = i;
        }
        if (maxIndex == 0)
            output = PatternFileReader.readFromFile("xSign.txt");
        else if(maxIndex == 1)
            output = PatternFileReader.readFromFile("oSign.txt");
        else if(maxIndex == 2)
            output = PatternFileReader.readFromFile("-Sign.txt");
        else
            for (int i=0; i<input.size(); i++)
                output.add(i,0.0);

//        double[] results = new double[3];
//        for (int i=0; i<3; i++) {
//            double weightedSum = neurons.get(i).calculateWeightedSum(input);
//            results[i] = neurons.get(i).applyActivationFunction(weightedSum);
//        }
//        System.out.println("Results: "+results[0]+" "+ results[1]+" "+results[2]);
//        if (results[0] == 1){
//            output = PatternFileReader.readFromFile("xSign.txt");
//        } else if (results[1] == 1) {
//            output = PatternFileReader.readFromFile("oSign.txt");
//        } else if (results[2] == 1) {
//            output = PatternFileReader.readFromFile("-Sign.txt");
//        } else {
//            for (int i=0; i<input.size(); i++) {
//                output.add(i,0.0);
//            }
//        }
        return output;
    }
}
