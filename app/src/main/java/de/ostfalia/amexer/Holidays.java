package de.ostfalia.amexer;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.ostfalia.amexer.entries.CSVReader;

public class Holidays extends AppCompatActivity {
    private ListView holidaysListView;
    private ArrayAdapter<String> holidayslistAdapter;
    private List<String> holidaysList;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Gets the CSV
        try {
            inputStream = this.getAssets().open("holidays_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holidays);

        fillList();
        holidaysListView = (ListView) findViewById(R.id.holidayListView);

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_holidays);
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
        holidaysList = new ArrayList<>(new CSVReader(inputStream).getData());

        holidayslistAdapter =
                new ArrayAdapter<>(
                        this,                               // This activity
                        R.layout.list_item_holidayslist,    // ID from XML-Layout-Data
                        R.id.item_list_textview_holidays,   // ID from TextViews
                        holidaysList);                      // Data in ArrayList

        ListView holidayslistListView = (ListView) this.findViewById(R.id.holidayListView);
        holidayslistListView.setAdapter(holidayslistAdapter);
        holidayslistAdapter.notifyDataSetChanged();
    }
}
