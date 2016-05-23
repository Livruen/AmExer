package de.ostfalia.amexer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Activity for afterschool-buttons
 * It show three buttons (bars, clubs, restaurants).
 * By clicking it opens a google-Map in browser or Google-Maps-App
 * @author Lina Tacke
*/
public class AfterSchool extends AppCompatActivity {
    private Button btnPubs;
    private Button btnClubs;
    private Button btnRestaurants;

    private static final String barsLink = "https://www.google.de/maps/search/Wolfenb%C3%BCttel+Bars/@52.1580908,10.5242733,15z/data=!3m1!4b1";
    private static final String clubsLink = "https://www.google.de/maps/place/Blackout/@52.1580333,10.530936,17z/data=!3m1!4b1!4m2!3m1!1s0x47a55e8f6284b3a3:0xd4afe55a16699651";
    private static final String restaurantsLink = "https://www.google.de/maps/search/Wolfenb%C3%BCttel+Restaurants/@52.1580494,10.5243431,15z/data=!3m1!4b1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_school);

        btnPubs = (Button) findViewById(R.id.buttonPubs);
        btnClubs = (Button) findViewById(R.id.buttonClubs);
        btnRestaurants = (Button) findViewById(R.id.buttonRestaurant);

        setActions();

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_after_school);
            // Removes the text in Action bar
            actionBar.setDisplayShowTitleEnabled(false);
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }
    }

    /**
     * Opens Google Maps-App or Google Map in choosen browser with Search-String
     */
    private void setActions() {
        btnPubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(barsLink)));
            }
        });

        btnClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubsLink)));
            }
        });

        btnRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(restaurantsLink)));
            }
        });
    }
}
