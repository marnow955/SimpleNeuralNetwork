import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marek on 2016-12-27.
 */
public class PatternFileReader {

    public static ArrayList<Integer> readFromFile(String filename) {
        ArrayList<Integer> list = new ArrayList<>();

        File file = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        String line = in.nextLine();
        String[] values = line.split(" ");
        for (int i=0; i<values.length; i++) {
            list.add(i, Integer.parseInt(values[i]));
        }
        return list;
    }
}