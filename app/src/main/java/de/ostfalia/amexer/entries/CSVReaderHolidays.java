package de.ostfalia.amexer.entries;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Lina on 14.04.2016.
 */
public class CSVReaderHolidays {
    private ArrayList<String> holidays = new ArrayList<>();

    public CSVReaderHolidays(InputStream iS) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                holidays.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getHolidays(){
        return holidays;
    }
}
