package de.ostfalia.amexer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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


    private int solferinoOpenHour;
    private int solferinoCloseHour;

    private int limesOpenHour;
    private int limesCloseHour;


    /* Activity Objects */
    private ImageButton solferinoButton;
    private ImageButton limesButton;

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
            actionBar.setDisplayShowTitleEnabled(false); // Removes text from the Action bar
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }

        // Initialize Activity Objects

        EditText solferinoBigText = (EditText) findViewById(R.id.solferino_text);
        EditText solferinoSmallText = (EditText) findViewById(R.id.mensa_time);
        solferinoButton = (ImageButton) findViewById(R.id.mensa_button);

        EditText limesBigText = (EditText) findViewById(R.id.limes_text);
        EditText limesSmallText = (EditText) findViewById(R.id.limes_time);
        limesButton = (ImageButton) findViewById(R.id.limes_button);

        setButtonAction();

        //Gets the current Time
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){

            solferinoBigText.setText(R.string.wochenende);
            solferinoBigText.setTextColor(Color.YELLOW);
            solferinoSmallText.setText(R.string.frei);
            limesBigText.setText(R.string.wochenende);
            limesBigText.setTextColor(Color.YELLOW);
            limesSmallText.setText(R.string.frei);

        }else {

            int currentHour =  c.get(Calendar.HOUR_OF_DAY);

            if(currentHour >= solferinoOpenHour && currentHour < solferinoCloseHour){
                solferinoBigText.setText(R.string.open, TextView.BufferType.EDITABLE);
                solferinoBigText.setTextColor(Color.GREEN);
                solferinoSmallText.setText(getString(R.string.offen_bis) + solferinoCloseHour + getString(R.string.zero_minute));
            } else {
                solferinoBigText.setText(R.string.closed, TextView.BufferType.EDITABLE);
                solferinoBigText.setTextColor(Color.RED);
                solferinoSmallText.setText(getString(R.string.wir_sehen_uns) + solferinoOpenHour + getString(R.string.zero_minute));
            }

            if(currentHour >= limesOpenHour && currentHour < limesCloseHour){
                limesBigText.setText(R.string.open, TextView.BufferType.EDITABLE);
                limesBigText.setTextColor(Color.GREEN);
                limesSmallText.setText(getString(R.string.offen_bis) + limesCloseHour + getString(R.string.zero_minute));
            } else {
                limesBigText.setText(R.string.closed, TextView.BufferType.EDITABLE);
                limesBigText.setTextColor(Color.RED);
                limesSmallText.setText(getString(R.string.wir_sehen_uns) + limesOpenHour + getString(R.string.zero_minute));
            }
        }

    }

    /**
     *  When the Image will be clicked, than a Browser with the Restaurant menu will be openned.
     */
    private void setButtonAction() {

        solferinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.kv.drk-kv-wf.de/fileadmin/user_upload/aktuelles/speisepl%C3%A4ne/menu-solferino.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        limesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://limes.dragosardo.de");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    /**
     * Gets the openning and closing times from a CSV File and assings it to
     * private variables.
     * @param iS
     */
    private void setOpenHours(InputStream iS) {

        ArrayList<String> hoursInString = new ArrayList<>(new CSVReader(iS).getData());

        solferinoOpenHour = Integer.parseInt(hoursInString.get(SOLFERINO_OPEN_TIME_CSV));
        solferinoCloseHour = Integer.parseInt(hoursInString.get(SOLFERINO_CLOSE_TIME_CSV));
        limesOpenHour = Integer.parseInt(hoursInString.get(LIMES_OPEN_TIME_CSV));
        limesCloseHour = Integer.parseInt(hoursInString.get(LIMES_CLOSE_TIME_CSV));
    }
}