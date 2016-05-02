package de.ostfalia.amexer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.ostfalia.amexer.entries.CSVReader;

public class MapListSalzdahlumer extends AppCompatActivity {
    private ListView salzdahlumerListView;
    private ArrayAdapter<String> salzdahlumerlistAdapter;
    private List<String> salzdahlumerList;
    private InputStream inputStream;
    private RelativeLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Gets the csv
        try {
            inputStream = this.getAssets().open("campus_salzdahlumer_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_list_salzdahlumer);

        fillList();
        salzdahlumerListView = (ListView) findViewById(R.id.salzdahlumerListView);

        bg = (RelativeLayout) findViewById(R.id.bg_list_salzdahlumer);

        setActions();

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_maps);
            //Removes the text in action bar
            actionBar.setDisplayShowTitleEnabled(false);
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }
    }

    /**
     * Fills the list with data
     */
    private void fillList() {
        salzdahlumerList = new ArrayList<>(new CSVReader(inputStream).getData());

        salzdahlumerlistAdapter =
                new ArrayAdapter<>(
                        this,                                       // // This activity
                        R.layout.list_item_salzdahlumerlist,        // ID from XML-Layout-Data
                        R.id.item_list_textview_salzdahlumer,       // ID from TextViews
                        salzdahlumerList);                          // Daten in ArrayList

        ListView dataListView = (ListView) this.findViewById(R.id.salzdahlumerListView);
        dataListView.setAdapter(salzdahlumerlistAdapter);
        salzdahlumerlistAdapter.notifyDataSetChanged();
    }

    /**
     * Sets actions for wiping gestures
     */
    private void setActions() {
        //Switches between listview and map
        bg.setOnTouchListener(new Gestures(this) {
            public boolean onSwipeRight() {
                startActivity(new Intent(MapListSalzdahlumer.this, MapSalzdahlumer.class));
                return true;
            }

            public boolean onSwipeLeft() {
                startActivity(new Intent(MapListSalzdahlumer.this, MapSalzdahlumer.class));
                return true;
            }
        });
    }
}
