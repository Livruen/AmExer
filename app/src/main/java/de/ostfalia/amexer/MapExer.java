package de.ostfalia.amexer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MapExer extends AppCompatActivity {
    private Button gebaeude1;
    private Button gebaeude2;
    private Button gebaeude3;
    private Button gebaeude4;
    private Button gebaeude5;
    private Button gebaeude6;
    private Button gebaeude7;
    private Button gebaeude8;
    private Button gebaeude9;
    private Button gebaeude10;
    private Button gebaeude11;
    private Button gebaeude12;
    private Button gebaeude23;
    private Button gebaeude45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_exer);

        gebaeude1 = (Button) findViewById(R.id.gebaeude1);
        gebaeude2 = (Button) findViewById(R.id.gebaeude2);
        gebaeude3 = (Button) findViewById(R.id.gebaeude3);
        gebaeude4 = (Button) findViewById(R.id.gebaeude4);
        gebaeude5 = (Button) findViewById(R.id.gebaeude5);
        gebaeude6 = (Button) findViewById(R.id.gebaeude6);
        gebaeude7 = (Button) findViewById(R.id.gebaeude7);
        gebaeude8 = (Button) findViewById(R.id.gebaeude8);
        gebaeude9 = (Button) findViewById(R.id.gebaeude9);
        gebaeude10 = (Button) findViewById(R.id.gebaeude10);
        gebaeude11 = (Button) findViewById(R.id.gebaeude11);
        gebaeude12 = (Button) findViewById(R.id.gebaeude12);
        gebaeude23 = (Button) findViewById(R.id.gebaeude23);
        gebaeude45 = (Button) findViewById(R.id.gebaeude45);


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
        gebaeude2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
                System.out.println("Blaaaaa");
            }
        });
    }

    public void action(){
        Toast.makeText(this, R.string.gebaeude1str, Toast.LENGTH_LONG).show();
    }
}
