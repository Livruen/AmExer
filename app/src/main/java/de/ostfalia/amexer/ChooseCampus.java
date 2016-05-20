package de.ostfalia.amexer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChooseCampus extends AppCompatActivity {

    private Button campusMain;
    private Button campusAmExer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_campus_food);

        campusMain = (Button) findViewById(R.id.Immatrikulationsbuero);
        campusAmExer = (Button) findViewById(R.id.international_office);

        setAction();

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
    }

    /**
     * By clicking: opens the map for the choosen campus
     */
    private void setAction(){
        campusAmExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseCampus.this, FoodAmExer.class));
            }
        });

        campusMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseCampus.this, FoodAmMain.class));
            }
        });
    }
}
