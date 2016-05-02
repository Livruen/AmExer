package de.ostfalia.amexer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.ostfalia.amexer.entries.CSVReader;

public class Sport extends AppCompatActivity {
    private ListView sportsListView;
    private ArrayAdapter<String> sportslistAdapter;
    private List<String> sportsList;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Gets the csv
        try {
            inputStream = this.getAssets().open("sports_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        fillList();
        sportsListView = (ListView) findViewById(R.id.sportListView);
        setActions();

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_sport);
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
        sportsList = new ArrayList<>(new CSVReader(inputStream).getData());

        sportslistAdapter =
                new ArrayAdapter<>(
                        this,                           // This Activity)
                        R.layout.list_item_sportslist,  // ID from XML-Layout-Data
                        R.id.item_list_textview,        // ID from TextViews
                        sportsList);                    // Daten from ArrayList

        ListView sportslistListView = (ListView) this.findViewById(R.id.sportListView);
        sportslistListView.setAdapter(sportslistAdapter);
        sportslistAdapter.notifyDataSetChanged();
    }

    /**
     * Creates the Link by clicking on list-item(Sport-course)
     */
    private void setActions() {
        sportsListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                //Hier wird aus dem Sportnamen ein Link erstellt //TODO wird hier sportsLink in uri eingefügt, wird _ als %20 angezeigt
                /*String sportsLink = sportsList.get(position);
                sportsLink.replace(" ", "_");
                sportsLink.replace("ä", "ae");
                sportsLink.replace("ö", "oe");
                sportsLink.replace("ü", "ue");
                sportsLink.replace("ß", "ss");
                sportsLink.replace("/", "_");
                sportsLink.replace("(", "_");
                sportsLink.replace(")", "_");*/

                Uri uri = Uri.parse("https://www.hochschulsport.ostfalia.de/angebote/aktueller_zeitraum/_" + sportsList.get(position).replace(" ", "_").replace("ä", "ae").replace("ö", "oe").replace("ü", "ue").replace("ß", "ss").replace("/", "_").replace("(", "_").replace(")", "_") + ".html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
