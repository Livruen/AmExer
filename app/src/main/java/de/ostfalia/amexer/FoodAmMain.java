package de.ostfalia.amexer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import de.ostfalia.amexer.entries.CSVReader;

public class FoodAmMain extends AppCompatActivity {

    /*Constants tells the line index in the CSV */
    private final int MENSA_OPEN_TIME_CSV = 0;
    private final int MENSA_CLOSE_TIME_CSV = 1;

    private int mensa_open_hour;
    private int mensa_close_hour;

    ArrayList<String> openHours;

    /* Activity Objects */
    private EditText mensa_text;
    private EditText mensa_time;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        InputStream iS = null;
        // Reads CSV
        try {
            iS = this.getAssets().open("mensa_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpenHours(iS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_am_main);
        context = this;

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_food);
            actionBar.setDisplayShowTitleEnabled(false); // entfernt den text von der Action bar
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }

        // Initialize Activity Objects
        mensa_text = (EditText) this.findViewById(R.id.mensa_text);
        mensa_time = (EditText) this.findViewById(R.id.mensa_time);

        //Get Time
        Calendar c = Calendar.getInstance();

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){

            mensa_text.setText("Wochenende");
            mensa_text.setTextColor(Color.YELLOW);
            mensa_time.setText("Wir haben frei");

        }else {

            int currentHour =  c.get(Calendar.HOUR_OF_DAY);

            if(currentHour >= mensa_open_hour && currentHour <= mensa_close_hour){
                mensa_text.setText(R.string.open, TextView.BufferType.EDITABLE);
                mensa_text.setTextColor(Color.GREEN);
                mensa_time.setText("Offen bis " + mensa_close_hour +":00");
            } else {
                mensa_text.setText(R.string.closed, TextView.BufferType.EDITABLE);
                mensa_text.setTextColor(Color.RED);
                mensa_time.setText("Wir sind sehen uns um " + mensa_open_hour +":00");
            }
        }

    }

    private void setOpenHours(InputStream iS) {
        openHours = new ArrayList<>();

        ArrayList<String> hoursInString = new ArrayList<>(new CSVReader(iS).getData());

        mensa_open_hour = Integer.parseInt(hoursInString.get(MENSA_OPEN_TIME_CSV));
        mensa_close_hour = Integer.parseInt(hoursInString.get(MENSA_CLOSE_TIME_CSV));
    }
}
