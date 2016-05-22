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

public class FoodAmMain extends AppCompatActivity {

    /*Constants tells the line index in the CSV */
    private final int MENSA_OPEN_TIME_CSV = 0;
    private final int MENSA_CLOSE_TIME_CSV = 1;

    private int mensaOpenHour;
    private int mensaCloseHour;

    /* Activity Objects */
    private EditText mensaBigText;
    private EditText mensaSmallText;
    private ImageButton mensaButton;

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
            actionBar.setDisplayShowTitleEnabled(false); // Removes text from the Action bar
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }

        // Initialize Activity Objects
        mensaBigText = (EditText) this.findViewById(R.id.mensa_text);
        mensaSmallText = (EditText) this.findViewById(R.id.mensa_time);
        mensaButton = (ImageButton) this.findViewById(R.id.mensa_button);

        setButtonAction();

        //Get Time
        Calendar c = Calendar.getInstance();

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){

            mensaBigText.setText(R.string.wochenende);
            mensaBigText.setTextColor(Color.BLUE);
            mensaSmallText.setText(R.string.frei);

        }else {

            int currentHour =  c.get(Calendar.HOUR_OF_DAY);

            if(currentHour >= mensaOpenHour && currentHour <= mensaCloseHour){
                mensaBigText.setText( String.valueOf(R.string.open), TextView.BufferType.EDITABLE);
                mensaBigText.setTextColor(Color.GREEN);
                mensaSmallText.setText(R.string.offen_bis + mensaCloseHour + R.string.zero_minute);
            } else {
                mensaBigText.setText(R.string.closed, TextView.BufferType.EDITABLE);
                mensaBigText.setTextColor(Color.RED);
                mensaSmallText.setText(R.string.wir_sehen_uns + mensaOpenHour + R.string.zero_minute);
            }
        }

    }

    /**
     *  When the Image will be clicked, than a Browser with the Restaurant menu will be openned.
     */
    private void setButtonAction() {
        mensaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.stw-on.de/wolfenbuettel/essen/menus/mensa-ostfalia");
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

        mensaOpenHour = Integer.parseInt(hoursInString.get(MENSA_OPEN_TIME_CSV));
        mensaCloseHour = Integer.parseInt(hoursInString.get(MENSA_CLOSE_TIME_CSV));
    }
}