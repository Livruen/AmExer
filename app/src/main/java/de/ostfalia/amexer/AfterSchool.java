package de.ostfalia.amexer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
            actionBar.setDisplayShowTitleEnabled(false); // entfernt den text von der Action bar
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }
    }

    /**
     * Opens Google Maps-App or Google Map in Browser with Search-String
     */
    private void setActions() {
        btnPubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(barsLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(clubsLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(restaurantsLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
