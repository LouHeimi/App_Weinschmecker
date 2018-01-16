package com.example.louis.weinschmeckeroffenburg.Util;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.example.louis.weinschmeckeroffenburg.Fragments.FavouriteFrag;
import com.example.louis.weinschmeckeroffenburg.Fragments.ScanFrag;
import com.example.louis.weinschmeckeroffenburg.Fragments.SearchFrag;
import com.example.louis.weinschmeckeroffenburg.Fragments_old.SucheFrag;
import com.example.louis.weinschmeckeroffenburg.Fragments.WinestyleFrag;
import com.example.louis.weinschmeckeroffenburg.R;
import com.idescout.sql.SqlScoutServer;

import static com.example.louis.weinschmeckeroffenburg.Fragments.WebViewFragment.mWebView;


public class MainActivity extends AppCompatActivity {


    final Context context = this;
    private ImageButton button;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                        = new BottomNavigationView.OnNavigationItemSelectedListener() {
                    //Öffnen der einzelnen Fragments bei Klick auf Navigation-Icons
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.navigation_suche:
                                SearchFrag searchFrag = new SearchFrag();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, searchFrag, searchFrag.getTag()).commit();
                                return true;

                            case R.id.navigation_scan:
                                ScanFrag scanFrag = new ScanFrag();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content, scanFrag, scanFrag.getTag()).commit();
                                return true;

                case R.id.navigation_weinstyle:
                    WinestyleFrag winestyleFrag = new WinestyleFrag();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, winestyleFrag, winestyleFrag.getTag()).commit();
                    return true;

                case R.id.navigation_weinregal:
                    FavouriteFrag favouriteFrag = new FavouriteFrag();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, favouriteFrag, favouriteFrag.getTag()).commit();

                    return true;
            }
            return false;
        }
    };

    //Zurück kommen aus Unterseiten der Webview
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView!=null && mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SqlScoutServer.create(this, getPackageName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        SucheFrag sucheFrag = new SucheFrag();

        transaction.add(R.id.content, sucheFrag);
        transaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Overlay für Settings-Button
        button = (ImageButton) findViewById(R.id.btnSettings);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

         /*   @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Einstellungen");

                // set dialog message
                alertDialogBuilder

                        .setMessage("Hier noch Text rein zum Impressum")
                        .setCancelable(false)

                        .setNegativeButton("Schließen",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }*/

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.overlay_settings,null);

                builder.setView(dialogView);

                Button one = (Button) dialogView.findViewById(R.id.button1);



                final AlertDialog dialog = builder.create();


                one.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

dialog.cancel();
                    }
                });


                // Display the custom alert dialog on interface
                dialog.show();
            }

        });




        //Slide-Effekt weg
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);}

        //Dialog für Setting öffnen



    }


















