package de.ostfalia.amexer;
/**
 * This class uses a CSV-File 'semester_data.csv' to get the Semester-end-date and Semester-start-date.
 * After the Activity starts, the CVS-File will be loaded. The class gets the current
 * Date and Time, checks if Today is a workDay or Weekend.
 * If it is weekend The Activity shows a red "Geschlossen".
 * If it is a workday, it checks if TODAY is during Semester or Semester-free-time
 * The class gets from the activity_library_xml (from the Table) information about
 * the open-time-range for TODAY and compares it with the current time.
 * If current time is in Time-Range the library is "OFFEN" else "GRESCHLOSSEN"
 *
 * @autor Natasza Szczypien
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import de.ostfalia.amexer.entries.CSVReader;

public class Library extends AppCompatActivity {

    /* Activity-text that says if library is "OFFEN" or "GESCHLOSSEN" */
    private TextView library_text;

    //  private InputStream iS;

    private Calendar semesterStart;
    private Calendar semesterEnde;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        InputStream iS = null;
        // Reads CSV
        try {
            iS = this.getAssets().open(getString(R.string.library_csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDates(iS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        context = this;

        //Puts an Image into the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_library);
            actionBar.setDisplayShowTitleEnabled(false); // Removes text from the Action bar
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }

        library_text = (TextView) findViewById(R.id.library_text);

        //Get current Time and date
        Calendar c = Calendar.getInstance();

        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int dayType = c.get(Calendar.DAY_OF_WEEK);

        setAvailibility(year, month, day, dayType, currentHour, currentMinute);

    }


    /**
     * sets the Availibility text on the screen
     *
     * @param year
     * @param month
     * @param day
     * @param dayType
     * @param currentHour
     * @param currentMinute
     * @return is needed because if it is a weekday he sets "Geschlossen" and doesn't go forward in the code
     */
    private boolean setAvailibility(int year, int month, int day, int dayType, int currentHour, int currentMinute) {

        if (dayType == Calendar.SATURDAY || dayType == Calendar.SUNDAY) {

            library_text.setText(R.string.closed);
            library_text.setTextColor(Color.RED);
            return true;

        }
        TextView dayRow;
        String rowTime = "";

        try {
            if (isSemester(year, month, day)) {
                // Using the time from Semester Table
                switch (dayType) {
                    case Calendar.MONDAY:
                        dayRow = (TextView) findViewById(R.id.Mo_Se);
                        rowTime = dayRow.getText().toString();
                        break;
                    case Calendar.TUESDAY:
                        dayRow = (TextView) findViewById(R.id.Tue_Se);
                        rowTime = dayRow.getText().toString();
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
                }

            } else { // Semester-free-time
                switch (dayType) {

                    case Calendar.MONDAY:
                        dayRow = (TextView) findViewById(R.id.Mo_Vf);
                        rowTime = dayRow.getText().toString();
                        break;
                    case Calendar.TUESDAY:
                        dayRow = (TextView) findViewById(R.id.Tue_Vf);
                        rowTime = dayRow.getText().toString();
                        break;
                    case Calendar.WEDNESDAY:
                        dayRow = (TextView) findViewById(R.id.We_Vf);
                        rowTime = dayRow.getText().toString();
                        break;
                    case Calendar.THURSDAY:
                        dayRow = (TextView) findViewById(R.id.Thu_Vf);
                        rowTime = dayRow.getText().toString();
                        break;
                    case Calendar.FRIDAY:
                        dayRow = (TextView) findViewById(R.id.Fr_Vf);
                        rowTime = dayRow.getText().toString();
                        break;

                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        /* Prepares the current library open-time-range  */
        ArrayList<String> openHours = prepareTime(rowTime); // [openHour, openMinute, closeHour, closeMinute]
        int tempOpenHour = Integer.parseInt(openHours.get(0));
        int tempOpenMinute = Integer.parseInt(openHours.get(1));
        int tempCloseHour = Integer.parseInt(openHours.get(2));
        int tempCloseMinute = Integer.parseInt(openHours.get(3));

        if (isOpen(tempOpenHour, tempOpenMinute, tempCloseHour, tempCloseMinute, currentHour, currentMinute)) {
            library_text.setText(R.string.open);
            library_text.setTextColor(Color.GREEN);
        } else {
            library_text.setText(R.string.closed);
            library_text.setTextColor(Color.RED);
        }
        return true;
    }

    /**
     * Gets the TODAYs open-time-range from the Opening-time-table in
     * activity_library
     *
     * @param rowTime
     * @return list with times in String
     */
    private ArrayList<String> prepareTime(String rowTime) {

        rowTime = rowTime.trim();
        ArrayList<String> tempList = new ArrayList<>(); // [00:00-00:00]
        String[] twoTemps = rowTime.split("-");
        String[] timeOne = twoTemps[0].toString().split(":");
        String[] timeTwo = twoTemps[1].toString().split(":");

        tempList.add(timeOne[0].trim());
        tempList.add(timeOne[1].trim());
        tempList.add(timeTwo[0].trim());
        tempList.add(timeTwo[1].trim());

        return tempList;
    }

    /**
     * Checks if Current time is in the open-library-time-range
     *
     * @param tempOpenHour
     * @param tempOpenMinute
     * @param tempCloseHour
     * @param tempCloseMinute
     * @param currentHour
     * @param currentMinute
     * @return
     */
    private boolean isOpen(int tempOpenHour, int tempOpenMinute, int tempCloseHour, int tempCloseMinute, int currentHour, int currentMinute) {

        if (currentHour == tempOpenHour) {
            if (currentMinute >= tempOpenMinute) {
                return true;
            } else {
                return false;
            }

        } else if (currentHour > tempOpenHour && currentHour <= tempCloseHour) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the the current date is in semester time
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    private boolean isSemester(int year, int month, int day) {
        return (semesterStart.get(Calendar.YEAR) == year) && (month >= semesterStart.get(Calendar.MONTH) && month <= semesterEnde.get(Calendar.MONTH)) && (month >= semesterStart.get(Calendar.DATE) && month <= semesterEnde.get(Calendar.DATE));
    }


    /* Sets all relevant dates */
    private void setDates(InputStream iS) {

        ArrayList<String> datesInString = new ArrayList<>(new CSVReader(iS).getData());
        ArrayList<Calendar> dates = new ArrayList<>();

        /* Fills the array with Date (Calendar) Objects */
        for (String line : datesInString) {
            String[] splitDate = line.split("-");
            int year = Integer.parseInt(splitDate[2]);
            int month = Integer.parseInt(splitDate[1]);
            int day = Integer.parseInt(splitDate[0]);

            Calendar date = new GregorianCalendar(year, month, day);
            dates.add(date);
        }

        semesterStart = dates.get(0);
        semesterEnde = dates.get(1);
    }
}
