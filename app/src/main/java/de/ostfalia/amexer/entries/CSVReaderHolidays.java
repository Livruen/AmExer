package de.ostfalia.amexer.entries;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Lina on 14.04.2016.
 */
public class CSVReaderHolidays {
    private ArrayList<String> holidayNames = new ArrayList<>();
    private ArrayList<String> holidayDates = new ArrayList<>();

    public CSVReaderHolidays(InputStream iS) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                holidayNames.add(split[0]);
                holidayDates.add(split[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getHolidayNames(){
        return holidayNames;
    }

    public ArrayList<String> getHolidayDates(){
        return holidayDates;
    }
}
