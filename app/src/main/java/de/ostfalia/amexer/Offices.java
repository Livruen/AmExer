package de.ostfalia.amexer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.ostfalia.amexer.entries.CSVReader;

public class Offices extends AppCompatActivity {
    private ListView officesListView;
    private ArrayAdapter<String> officeslistAdapter;
    private List<String> officesList;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*// Gets the csv
        try {
            inputStream = this.getAssets().open("offices_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offices);

        /*fillList();
        officesListView = (ListView) findViewById(R.id.sportListView);*/

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_offices);
            actionBar.setDisplayShowTitleEnabled(false); // entfernt den text von der Action bar
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }
    }

    /**
     * Fills the list with data
     */
    private void fillList() {
        officesList = new ArrayList<>(new CSVReader(inputStream).getData());

        officeslistAdapter =
                new ArrayAdapter<>(
                        this,                             // This Activity)
                        R.layout.list_item_officeslist,   // ID from XML-Layout-Data
                        R.id.linearLayout_offices,        // ID from LinearLayout
                        officesList);                     // Daten from ArrayList

        ListView sportslistListView = (ListView) this.findViewById(R.id.sportListView);
        sportslistListView.setAdapter(officeslistAdapter);
        officeslistAdapter.notifyDataSetChanged();
    }
}
