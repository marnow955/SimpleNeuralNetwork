import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marek on 2016-12-23.
 */
public class MMain {

    private ArrayList<Integer> weights;
    public MMain(){
        weights = new ArrayList<>();
        learnWeights();
    }

    private void learnWeights() {
        ArrayList<Integer> np1;
        ArrayList<Integer> np2;
        ArrayList<Integer> np3;
        try {
            np1 = readMatrix("xSign.txt");
            np2 = readMatrix("oSign.txt");
            np3 = readMatrix("-Sign.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

    }

    private ArrayList<Integer> readMatrix(String filename) throws FileNotFoundException {
        ArrayList<Integer> list = new ArrayList<>();

        File file = new File(filename);
        Scanner in = new Scanner(file);

        String line = in.nextLine();
        String[] values = line.split(" ");
        for (int i=0; i<values.length; i++) {
            list.add(i, Integer.parseInt(values[i]));
        }
        return list;
    }
}
