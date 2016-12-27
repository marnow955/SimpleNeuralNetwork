import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class CMain {

    private static VMain window;
    private static MMain calc;
    private static Network network;

    public static final void main(String[] args){
        window = new VMain(3);
        calc = new MMain();
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

        ArrayList<Integer> input = window.getButtonsValues();
        Matrix inputM = new Matrix(9,1);
        for (int i=0; i<input.size(); i++) {
            inputM.add(i,0,input.get(i));
        }
        Matrix result = calc.getAnswer(inputM);
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int i=0; i<result.getNumberOfRows(); i++){
            for (int j=0; j<result.getNumberOfColumns(); j++){
                output.add(i*result.getNumberOfColumns()+j,result.get(i,j));
            }
        }
        window.showResult(output);

        window.IS_COMPLETE = false;
        listenSubmitButton();
    }

    public static void calculateAnswer() {
        ArrayList<Integer> input = window.getButtonsValues();
        ArrayList<Integer> output = network.getAnswer(input);
        window.showResult(output);
    }
}
