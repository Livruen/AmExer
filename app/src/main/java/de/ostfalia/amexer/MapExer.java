package de.ostfalia.amexer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

public class MapExer extends AppCompatActivity {

    private RelativeLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_exer);

        bg = (RelativeLayout) findViewById(R.id.bg_map_am_exer);

        setActions();

        //Puts an Image to the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_maps);
            actionBar.setDisplayShowTitleEnabled(false); // deletes the text from action bar
            Log.i(this.getClass().toString(), " action bar");
        } else {
            Log.i(this.getClass().toString(), "no action bar");
        }
    }

    private void setActions() {
        //Switch between listview and map
        bg.setOnTouchListener(new Gestures(this) {
            public boolean onSwipeRight() {
                startActivity(new Intent(MapExer.this, MapListExer.class));
                return true;
            }

            public boolean onSwipeLeft() {
                startActivity(new Intent(MapExer.this, MapListExer.class));
                return true;
            }
        });
    }
}
