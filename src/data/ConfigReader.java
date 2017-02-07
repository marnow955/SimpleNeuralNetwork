package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by marek on 2016-12-29.
 */
public class ConfigReader {

    private String filename;
    private HashMap<String,String> properties;

    public ConfigReader (String filename, ArrayList<String> propertyNames) {
        this.filename = filename;
        properties = new HashMap<>();
        readProperties(propertyNames);
    }

    private void readProperties(ArrayList<String> propertyNames) {
        File file = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.startsWith("#"))
                continue;
            String[] splited = line.split("=");
            if (splited.length!=2 || !propertyNames.contains(splited[0]))
                continue;
            properties.put(splited[0],splited[1]);
        }
    }

    public Integer loadIntegerProperty(String key) {
        if (properties.containsKey(key))
            return Integer.parseInt(properties.get(key));
        else
            return null;
    }

    public Double loadDoubleProperty(String key) {
        if (properties.containsKey(key))
            return Double.parseDouble(properties.get(key));
        else
            return null;
    }
}
