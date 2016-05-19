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

public class FoodAmExer extends AppCompatActivity {

    /*Constants tells the line index in the CSV */
    private final int SOLFERINO_OPEN_TIME_CSV = 0;
    private final int SOLFERINO_CLOSE_TIME_CSV = 1;
    private final int LIMES_OPEN_TIME_CSV = 2;
    private final int LIMES_CLOSE_TIME_CSV = 3;

    private final int SOLFERINO = 0;
    private final int LIMES = 1;

    private int solferino_open_hour;
    private int solferino_close_hour;

    private int limes_open_hour;
    private int limes_close_hour;

    ArrayList<String> openHours;

    /* Activity Objects */
    private EditText solferino_text;
    private EditText solferino_time;

    private EditText limes_text;
    private EditText limes_time;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        InputStream iS = null;
        // Reads CSV
        try {
            iS = this.getAssets().open("solferino_limes_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpenHours(iS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_am_exer);
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
        solferino_text = (EditText) findViewById(R.id.solferino_text);
        solferino_time = (EditText) findViewById(R.id.mensa_time);
        limes_text = (EditText) findViewById(R.id.limes_text);
        limes_time = (EditText) findViewById(R.id.limes_time);

        //Get Time
        Calendar c = Calendar.getInstance();

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){

            solferino_text.setText("Wochenende");
            solferino_text.setTextColor(Color.YELLOW);
            solferino_time.setText("Wir haben frei");
            limes_text.setText("Wochenende");
            limes_text.setTextColor(Color.YELLOW);
            limes_time.setText("Wir haben frei");

        }else {

            int currentHour =  c.get(Calendar.HOUR_OF_DAY);

            if(currentHour >= solferino_open_hour && currentHour < solferino_close_hour){
                solferino_text.setText(R.string.open, TextView.BufferType.EDITABLE);
                solferino_text.setTextColor(Color.GREEN);
                solferino_time.setText("Offen bis " + solferino_close_hour +":00");
            } else {
                solferino_text.setText(R.string.closed, TextView.BufferType.EDITABLE);
                solferino_text.setTextColor(Color.RED);
                solferino_time.setText("Wir sind sehen uns um " + solferino_open_hour +":00");
            }

            if(currentHour >= limes_open_hour && currentHour < limes_close_hour){
                limes_text.setText(R.string.open, TextView.BufferType.EDITABLE);
                limes_text.setTextColor(Color.GREEN);
                limes_time.setText("Offen bis " + limes_close_hour +":00");
            } else {
                limes_text.setText(R.string.closed, TextView.BufferType.EDITABLE);
                limes_text.setTextColor(Color.RED);
                limes_time.setText("Wir sind sehen uns um " + limes_open_hour +":00");
            }
        }

    }

    private void setOpenHours(InputStream iS) {
        openHours = new ArrayList<>();

        ArrayList<String> hoursInString = new ArrayList<>(new CSVReader(iS).getData());

        solferino_open_hour = Integer.parseInt(hoursInString.get(SOLFERINO_OPEN_TIME_CSV));
        solferino_close_hour = Integer.parseInt(hoursInString.get(SOLFERINO_CLOSE_TIME_CSV));
        limes_open_hour = Integer.parseInt(hoursInString.get(LIMES_OPEN_TIME_CSV));
        limes_close_hour = Integer.parseInt(hoursInString.get(LIMES_CLOSE_TIME_CSV));
    }


}