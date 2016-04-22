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

import de.ostfalia.amexer.entries.CSVReaderHolidays;

public class Holidays extends AppCompatActivity {
    private ListView holidaysListView;
    private ArrayAdapter<String> holidayslistAdapter;
    private List<String> holidaysList;
    private InputStream iS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Holt sich die csv-Datei
        try {
            iS = this.getAssets().open("holidays.csv");
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
            actionBar.setDisplayShowTitleEnabled(false); // entfernt den text von der Action bar
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }
    }

    /**
     * Initialisiert und füllt die Liste
     */
    private void fillList() {
        holidaysList = new ArrayList<>(new CSVReaderHolidays(iS).getHolidayNames());

        holidayslistAdapter =
                new ArrayAdapter<>(
                        this,                           // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_holidayslist,// ID der XML-Layout Datei
                        R.id.item_list_textview_holidays,// ID des TextViews
                        holidaysList);                  // Daten in einer ArrayList

        ListView holidayslistListView = (ListView) this.findViewById(R.id.holidayListView);
        holidayslistListView.setAdapter(holidayslistAdapter);
        holidayslistAdapter.notifyDataSetChanged();
    }
}
