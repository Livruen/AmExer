package de.ostfalia.amexer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Lina on 22.04.2016.
 */
public class ChooseCampusMaps extends AppCompatActivity {
    private Button campusAmExer;
    private Button campusSalzdahlumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_campus_maps);

        campusAmExer = (Button) findViewById(R.id.international_office);
        campusSalzdahlumer = (Button) findViewById(R.id.Immatrikulationsbuero);
        setAction();

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_maps);
            actionBar.setDisplayShowTitleEnabled(false); // entfernt den text von der Action bar
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }
    }

    private void setAction(){

        campusAmExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseCampusMaps.this, MapExer.class));
            }
        });

        campusSalzdahlumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseCampusMaps.this, MapSalzdahlumer.class));
            }
        });
    }
}
