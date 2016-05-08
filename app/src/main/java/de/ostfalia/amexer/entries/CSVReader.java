package de.ostfalia.amexer.entries;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Lina on 02.05.2016.
 */
public class CSVReader {
    private ArrayList<String> data = new ArrayList<>();

    public CSVReader(InputStream iS) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getData(){
        return data;
    }
}
