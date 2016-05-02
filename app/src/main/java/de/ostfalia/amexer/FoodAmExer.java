package de.ostfalia.amexer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class FoodAmExer extends AppCompatActivity {

    /*Constants*/
    final int SOLFERINO_OPEN_HOUR = 8;
    final int SOLFERINO_CLOSE_HOUR = 14;

    final int LIMES_OPEN_HOUR = 7;
    final int LIMES_CLOSE_HOUR = 12;


    private EditText solferino_text;
    private EditText solferino_time;

    private EditText limes_text;
    private EditText limes_time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        // Initialize Objects in View

        solferino_text = (EditText) findViewById(R.id.solferino_text);
        solferino_time = (EditText) findViewById(R.id.solferino_time);

        limes_text = (EditText) findViewById(R.id.limes_text);
        limes_time = (EditText) findViewById(R.id.limes_time);

        //Get Time
        Calendar c = Calendar.getInstance();

        int AM_PM = c.get(Calendar.AM_PM);
        int currentHour = c.get(Calendar.HOUR) + AM_PM;
        int currentMinute = c.get(Calendar.MINUTE);



        if(currentHour >= SOLFERINO_OPEN_HOUR  && currentHour <= SOLFERINO_CLOSE_HOUR){
            solferino_text.setText(R.string.open, TextView.BufferType.EDITABLE);
            solferino_text.setTextColor(Color.GREEN);
        } else {
            solferino_text.setText(R.string.closed, TextView.BufferType.EDITABLE);
            solferino_text.setTextColor(Color.RED);
        }

        if(currentHour >= LIMES_OPEN_HOUR && currentHour <= LIMES_CLOSE_HOUR){
            limes_text.setText(R.string.open, TextView.BufferType.EDITABLE);
            limes_text.setTextColor(Color.GREEN);
        } else {
            limes_text.setText(R.string.closed, TextView.BufferType.EDITABLE);
            limes_text.setTextColor(Color.RED);
        }
    }
}