import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marek on 2016-12-23.
 */
public class MMain {

    private ArrayList<Integer> weights;
    private Matrix weightsM;
    public MMain(){
        weights = new ArrayList<>();
        learnWeights();
    }

    private void learnWeights() {
        ArrayList<Integer> np1;
        ArrayList<Integer> np2;
        ArrayList<Integer> np3;
        np1 = PatternFileReader.readFromFile("xSign.txt");
        np2 = PatternFileReader.readFromFile("oSign.txt");
        np3 = PatternFileReader.readFromFile("-Sign.txt");
        Matrix p = new Matrix(9,3);
        for (int i=0; i<9; i++) {
            p.add(i,0,np1.get(i));
            p.add(i,1,np2.get(i));
            p.add(i,2,np3.get(i));
        }
        Matrix pTransposed = p.transpose();
        weightsM = Matrix.multiply(p,pTransposed);
    }

    public Matrix getAnswer(Matrix input) {
        return Matrix.multiply(weightsM,input);
    }

}
