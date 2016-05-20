package de.ostfalia.amexer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Activity for choosing campus (exer or salzdahlumer)
 */
public class ChooseCampusMaps extends AppCompatActivity {
    private Button campusAmExer;
    private Button campusSalzdahlumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_campus_maps);

<<<<<<< HEAD
        campusAmExer = (Button) findViewById(R.id.international_office);
        campusSalzdahlumer = (Button) findViewById(R.id.Immatrikulationsbuero);
        setAction();
=======
        campusAmExer = (Button) findViewById(R.id.campusExer);
        campusSalzdahlumer = (Button) findViewById(R.id.campusSalzdahlumer);
        setActions();
>>>>>>> 670567217198a7fd6fb018f48d8ac8fac7dfd71b

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_maps);
            // removes the text in action-bar
            actionBar.setDisplayShowTitleEnabled(false);
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }
    }

    /**
     * Sets the action by button-click
     */
    private void setActions(){
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
