package de.ostfalia.amexer;
/**
 * the class generates an action after pressing a button from offices_activity
 * and reference every button to a different website from Ostfalia.de
 *
 *
 * @autor Natasza Szczypien
 */
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

    /* Aktivity objects */
    private Button internationalOffice;
    private Button immatrikulationsbuero;
    private Button careerService;
    private Button serviceBueros;
    private Button studienfoerderung;
    private Button studienberatung;

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
            actionBar.setDisplayShowTitleEnabled(false);
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarEnabled));
        } else {
            Log.i(this.getClass().toString(), String.valueOf(R.string.actionBarDisabled));
        }

        // Initializing the aktivity buttons
         internationalOffice = (Button) this.findViewById(R.id.campusExer);
         immatrikulationsbuero = (Button) this.findViewById(R.id.campusSalzdahlumer);
         careerService = (Button) this.findViewById(R.id.Career_Service);
         serviceBueros = (Button) this.findViewById(R.id.Servicebueros);
         studienfoerderung = (Button) this.findViewById(R.id.Studienfoerderung);
         studienberatung = (Button) this.findViewById(R.id.studienberatung);

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

        immatrikulationsbuero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/ssc/immatrikulation/?nav2=true");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        careerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/career");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        serviceBueros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/ssc/servicebueros/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        studienfoerderung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/stipendien");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        studienberatung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ostfalia.de/cms/de/studienberatung/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}
