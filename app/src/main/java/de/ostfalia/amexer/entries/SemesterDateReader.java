package de.ostfalia.amexer.entries;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by livruen on 27.04.16.
 */
public class SemesterDateReader {

    private ArrayList<Calendar> semesterDates = new ArrayList<Calendar>();

    public SemesterDateReader(InputStream iS) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(".");
                int year = Integer.parseInt(split[2]);
                int month = Integer.parseInt(split[1]);
                int day = Integer.parseInt(split[0]);

                Calendar date = new GregorianCalendar(year,month,day);
                semesterDates.add(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Calendar> getSemesterDates(){
        return semesterDates;
    }
}
