import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class CMain {

    private static VMain window;
    private static Network network;

    public static final void main(String[] args){
        window = new VMain(3);
        network = new Network(3);
        network.trainNetwork();
        listenSubmitButton();
    }

    public static void listenSubmitButton() {
        while (!window.IS_COMPLETE) {
            if (!window.isActive())
                return;
        }

        calculateAnswer();
        window.IS_COMPLETE = false;
        listenSubmitButton();
    }

    public static void calculateAnswer() {
        ArrayList<Integer> input = window.getButtonsValues();
        ArrayList<Double> inputD = new ArrayList<>();
        for (int i=0; i<input.size();i++) {
            inputD.add(i, Double.valueOf(input.get(i)));
        }
        ArrayList<Double> outputD = network.getAnswer(inputD);
        ArrayList<Integer> output = new ArrayList<>();
        for (int i=0; i<outputD.size(); i++) {
            output.add(i,outputD.get(i).intValue());
        }
        window.showResult(output);
    }
}
