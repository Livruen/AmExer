package de.ostfalia.amexer;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.ostfalia.amexer.entries.SemesterDateReader;

public class Library extends AppCompatActivity {

    /* Text that says if library is OPEN od closed */
    private TextView library_text;


    private InputStream iS;

    private Calendar semesterStart ;
    private Calendar semesterEnde ;
    private Calendar vorlesungsfreiEnde ;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        // Reads CSV
        try {
            iS = this.getAssets().open("semester_daten.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDates();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        context = this;

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_library);
            actionBar.setDisplayShowTitleEnabled(false); // entfernt den text von der Action bar
            Log.i("FoodAmMain", " action bar");
        } else {
            Log.i("FoodAmMain", "no action bar");
        }

        library_text = (TextView) findViewById(R.id.library_text);

        //Get current Time and date
        Calendar c = Calendar.getInstance();

        int AM_PM = c.get(Calendar.AM_PM);
        int currentHour = c.get(Calendar.HOUR) + AM_PM;
        int currentMinute = c.get(Calendar.MINUTE);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int dayType = c.get(Calendar.DAY_OF_WEEK);

        library_text.setText(getAvailibility(year, month, day, dayType,currentHour, currentMinute));

    }

    private String getAvailibility(int year, int month, int day, int dayType, int currentHour, int currentMinute) {
        String availibility = "lina";
        TextView dayRow;
        String rowTime = "";

        if (isSemester(year, month, day)) {
            // Using the time from Semester Table
            switch(dayType){
                case Calendar.MONDAY:
                    dayRow = (TextView) findViewById(R.id.Mo_Se);
                    rowTime = dayRow.getText().toString();
                    break;
                case Calendar.TUESDAY:
                    dayRow = (TextView) findViewById(R.id.Tue_Se);
                    rowTime= dayRow.getText().toString();
                    break;
                case Calendar.WEDNESDAY:
                    dayRow = (TextView) findViewById(R.id.We_Se);
                    rowTime = dayRow.getText().toString();
                    break;
                case Calendar.THURSDAY:
                    dayRow = (TextView) findViewById(R.id.Thu_Se);
                    rowTime = dayRow.getText().toString();
                    break;
                case Calendar.FRIDAY:
                    dayRow = (TextView) findViewById(R.id.Fr_Se);
                    rowTime = dayRow.getText().toString();
                    break;
                case Calendar.SATURDAY:
                    availibility = "Closed";
                    break;
                case Calendar.SUNDAY:
                    availibility = "Closed";
                    break;
            }

        } else {
            switch(dayType){

                case Calendar.MONDAY:
                    break;
                case Calendar.TUESDAY:
                    break;
                case Calendar.WEDNESDAY:
                    break;
                case Calendar.THURSDAY:
                    break;
                case Calendar.FRIDAY:
                    break;
                case Calendar.SATURDAY:
                    break;
                case Calendar.SUNDAY:
                    break;
            }

        }

       /* String[] openHours = prepareTime(rowTime);
        int tempOpenHour = getOpenHour(openHours);
        int tempOpenMinute= getOpenMinute(openHours);
        int tempCloseHour = getCloseHour(openHours);
        int tempCloseMinute= getCloseMinute(openHours);

        if(isOpen(tempOpenHour, tempOpenMinute, tempCloseHour, tempCloseMinute, currentHour, currentMinute)){
            availibility = "OFFEN";
        }else {
            availibility = "GESCHLOSSEN";
        }*/
        return availibility;
    }

    private String[] prepareTime(String rowTime) {

        rowTime = rowTime.trim();
        String[] times = rowTime.split("-").toString().split(":").toString().split(":");

        return times;
    }

    /*------------------------------------------------------------
   *  GETTERS
   * -----------------------------------------------------------
   * */
    private int getCloseMinute(String[] rowTime) {
        String temp = rowTime[7] + rowTime[8];
        return Integer.parseInt(temp);
    }

    private int getCloseHour(String[] rowTime) {
        String temp = rowTime[5] + rowTime[6];
        return Integer.parseInt(temp);
    }

    private int getOpenMinute(String[] rowTime) {
        String temp = rowTime[3] + rowTime[4];
        return Integer.parseInt(temp);
    }

    private int getOpenHour(String[] rowTime) {
        String temp = rowTime[0] + rowTime[1];
        return Integer.parseInt(temp);
    }

    /*------------------------------------------------------------
    *  QUESTIONS
    * -----------------------------------------------------------
    * */
    private boolean isOpen(int tempOpenHour, int tempOpenMinute, int tempCloseHour, int tempCloseMinute, int currentHour, int currentMinute) {

        return (currentHour >= tempOpenHour) && (currentMinute >= tempOpenMinute) && (currentHour < tempCloseHour) && (currentMinute < tempCloseMinute);
    }


    private boolean isSemester(int year, int month, int day) {
        return (semesterStart.get(Calendar.YEAR) == year) && (month >= semesterStart.get(Calendar.MONTH) && month <= semesterEnde.get(Calendar.MONTH)) && (month >= semesterStart.get(Calendar.DATE) && month <= semesterEnde.get(Calendar.DATE)) ;
    }


    /* Sets all relevant dates */
    private void setDates() {
        List<Calendar> dates = new ArrayList<Calendar>(new SemesterDateReader(iS).getSemesterDates());
        semesterStart = dates.get(0);
        semesterEnde = dates.get(1);
        vorlesungsfreiEnde = dates.get(2);
    }
}
