package de.ostfalia.amexer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Offices extends AppCompatActivity {

    private Button internationalOffice;
    private Button Immatrikulationsbuero;
    private Button Career_Service;
    private Button Servicebueros;
    private Button Studienfoerderung;
    private Button Studienberatung;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offices);
        context = this;

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


         internationalOffice = (Button) this.findViewById(R.id.campusExer);
         Immatrikulationsbuero = (Button) this.findViewById(R.id.campusSalzdahlumer);
         Career_Service = (Button) this.findViewById(R.id.Career_Service);
         Servicebueros = (Button) this.findViewById(R.id.Servicebueros);
         Studienfoerderung = (Button) this.findViewById(R.id.Studienfoerderung);
         Studienberatung = (Button) this.findViewById(R.id.studienberatung);

      setButtonAction();

    }

    private void setButtonAction() {
        internationalOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/international/internationales_Buero_-_student_office");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Immatrikulationsbuero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/ssc/immatrikulation/?nav2=true");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Career_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/career");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Servicebueros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/ssc/servicebueros/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Studienfoerderung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/stipendien");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Studienberatung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/studienberatung/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}
