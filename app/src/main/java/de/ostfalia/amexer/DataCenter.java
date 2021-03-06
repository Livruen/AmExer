package de.ostfalia.amexer;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import de.ostfalia.amexer.entries.TableHelper;
/**
 * The class gets the current
 * Date and Time, checks if Today is a workDay or Weekend.
 * If it is weekend The Activity shows a red "Geschlossen".
 * The class gets from the activity_data_center_xml (from the Table) information about
 * the open-time-range for TODAY and compares it with the current time.
 * If current time is in Time-Range the library is "OFFEN" else "GRESCHLOSSEN"
 *
 * @author Natasza Szczypien
 */
public class DataCenter extends AppCompatActivity {

    /* Activity-text that says if library is "OFFEN" or "GESCHLOSSEN" */
    private TextView dataCenterText;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_center);
        context = this;

        setImageActionBar();
        initActivityObjects();
        setAvailibility();
    }

    /**
     *  Puts an Image to the Action Bar
     */
    private void setImageActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_data_center);
            actionBar.setDisplayShowTitleEnabled(false); // Removes text from the Action bar
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }
    }

    /**
     * Initialize Activity Objects
     */
    private void initActivityObjects() {
        dataCenterText = (TextView) findViewById(R.id.data_center_text);
    }

    /**
     * sets the Availibility text on the screen
     *
     * @return is needed because if it is a weekday he sets "Geschlossen" and doesn't go forward in the code
     */
    private boolean setAvailibility() {

        //Get current Time and date
        Calendar c = Calendar.getInstance();

        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);
        int dayType = c.get(Calendar.DAY_OF_WEEK);

        if (dayType == Calendar.SATURDAY || dayType == Calendar.SUNDAY) {

            dataCenterText.setText(R.string.closed);
            dataCenterText.setTextColor(Color.RED);
            return true;

        } else {

            // Row in table, in first column
            TextView dayRow1;
            String rowTime1 = "";
            // Row in table, in second column
            TextView dayRow2;
            String rowTime2 = "";
            TableHelper tableHelper = new TableHelper();
            try {
                // Gets the time from data center Table
                switch (dayType) {
                    case Calendar.MONDAY:
                        dayRow1 = (TextView) findViewById(R.id.Mo_Re1);
                        rowTime1 = dayRow1.getText().toString();
                        dayRow2 = (TextView) findViewById(R.id.Mo_Re2);
                        rowTime2 = dayRow2.getText().toString();
                        break;
                    case Calendar.TUESDAY:
                        dayRow1 = (TextView) findViewById(R.id.Tue_Re1);
                        rowTime1 = dayRow1.getText().toString();
                        dayRow2 = (TextView) findViewById(R.id.Tue_Re2);
                        rowTime2 = dayRow2.getText().toString();
                        break;
                    case Calendar.WEDNESDAY:
                        dayRow1 = (TextView) findViewById(R.id.We_Re1);
                        rowTime1 = dayRow1.getText().toString();
                        dayRow2 = (TextView) findViewById(R.id.We_Re2);
                        rowTime2 = dayRow2.getText().toString();
                        break;
                    case Calendar.THURSDAY:
                        dayRow1 = (TextView) findViewById(R.id.Thu_Re1);
                        rowTime1 = dayRow1.getText().toString();
                        dayRow2 = (TextView) findViewById(R.id.Thu_Re2);
                        rowTime2 = dayRow2.getText().toString();
                        break;
                    case Calendar.FRIDAY:
                        dayRow1 = (TextView) findViewById(R.id.Fr_Re1);
                        rowTime1 = dayRow1.getText().toString();
                        dayRow2 = (TextView) findViewById(R.id.Fr_Re2);
                        rowTime2 = dayRow2.getText().toString();
                        break;
                }


            } catch (NullPointerException e) {
                e.printStackTrace();
            }
             /* Prepares the current library open-time-range  */
            ArrayList<Integer> openHours1 = tableHelper.prepareTime(rowTime1); // [openHour, openMinute, closeHour, closeMinute]
            ArrayList<Integer> openHours2 = tableHelper.prepareTime(rowTime2); // [openHour, openMinute, closeHour, closeMinute]

            //First column from row
            int tempOpenHour1 = openHours1.get(0);
            int tempOpenMinute1 = openHours1.get(1);
            int tempCloseHour1 = openHours1.get(2);
            int tempCloseMinute1 = openHours1.get(3);

            //Second column from row
            int tempOpenHour2 = openHours2.get(0);
            int tempOpenMinute2 = openHours2.get(1);
            int tempCloseHour2 = openHours2.get(2);
            int tempCloseMinute2 = openHours2.get(3);

            if (tableHelper.isOpen(tempOpenHour1, tempOpenMinute1, tempCloseHour1, tempCloseMinute1, currentHour, currentMinute) || tableHelper.isOpen(tempOpenHour2, tempOpenMinute2, tempCloseHour2, tempCloseMinute2, currentHour, currentMinute)) {
                dataCenterText.setText(R.string.open);
                dataCenterText.setTextColor(Color.GREEN);
            } else {
                dataCenterText.setText(R.string.closed);
                dataCenterText.setTextColor(Color.RED);
            }
        }

        return true;
    }


}
