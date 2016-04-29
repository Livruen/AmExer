package de.ostfalia.amexer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Space;

public class MapExer extends AppCompatActivity {
    private Space gebaeude1;
    private Space gebaeude2;
    private Space gebaeude3;
    private Space gebaeude4;
    private Space gebaeude5;
    private Space gebaeude6;
    private Space gebaeude7;
    private Space gebaeude8;
    private Space gebaeude9;
    private Space gebaeude10;
    private Space gebaeude11;
    private Space gebaeude12;
    private Space gebaeude23;
    private Space gebaeude45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_exer);

        gebaeude1 = (Space) findViewById(R.id.gebaeude1);
        gebaeude2 = (Space) findViewById(R.id.gebaeude2);
        gebaeude3 = (Space) findViewById(R.id.gebaeude3);
        gebaeude4 = (Space) findViewById(R.id.gebaeude4);
        gebaeude5 = (Space) findViewById(R.id.gebaeude5);
        gebaeude6 = (Space) findViewById(R.id.gebaeude6);
        gebaeude7 = (Space) findViewById(R.id.gebaeude7);
        gebaeude8 = (Space) findViewById(R.id.gebaeude8);
        gebaeude9 = (Space) findViewById(R.id.gebaeude9);
        gebaeude10 = (Space) findViewById(R.id.gebaeude10);
        gebaeude11 = (Space) findViewById(R.id.gebaeude11);
        gebaeude12 = (Space) findViewById(R.id.gebaeude12);
        gebaeude23 = (Space) findViewById(R.id.gebaeude23);
        gebaeude45 = (Space) findViewById(R.id.gebaeude45);


        setActions();

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

    private void setActions() {
        gebaeude1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, R.string.gebaeude1str, Toast.LENGTH_LONG).show();    //TODO Toast ausgeben
            }
        });
    }
}
