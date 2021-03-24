package com.mc.cyclevie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    public static final String CYCLEVIEPREFS = "cycle_vie_prefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        if (savedInstanceState != null){
            String valeur = savedInstanceState.getString("clé");
        }
        popUp("onCreate()");
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String valeur = savedInstanceState.getString("clé");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        popUp("onRestart()");
    }


    @Override
    protected void onStart() {
        super.onStart();
        popUp("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume()");
        SharedPreferences settings = getSharedPreferences(CYCLEVIEPREFS, Context.MODE_PRIVATE);
        setTxTValeur(settings.getString("Valeur", ""));

        Intent intent = getIntent();
        String v= "" ;
        if (intent != null)   v=intent.getStringExtra("clé") ;
        setTxTValeur(v);





    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            popUp("onPause, l'utilisateur à demandé la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demandé la fermeture via un finish()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        popUp("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popUp("onDestroy()");
    }



    //###########################################################################

    public void setTxTValeur(String valeur){
        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setText(valeur);
    }

    public void popUp(String message) {
        Toast.makeText(this, "(act2) "+message, Toast.LENGTH_LONG).show();
    }
}