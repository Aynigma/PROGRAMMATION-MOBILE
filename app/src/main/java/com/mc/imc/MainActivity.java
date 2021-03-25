package com.mc.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button envoyer = null;
    Button reset = null;
    EditText taille = null;
    EditText poids = null;
    CheckBox commentaire = null;
    RadioGroup group = null;
    TextView result = null;

    private String texteInit = getString(R.string.txt_init);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        envoyer = (Button)findViewById(R.id.calcul);
        reset = (Button)findViewById(R.id.reset);
        taille = (EditText)findViewById(R.id.taille);
        poids = (EditText)findViewById(R.id.poids);
        commentaire = (CheckBox)findViewById(R.id.commentaire);
        group = (RadioGroup)findViewById(R.id.group);
        result = (TextView)findViewById(R.id.result);

        envoyer.setOnClickListener(envoyerListener);
        reset.setOnClickListener(resetListener);
        commentaire.setOnClickListener(checkedListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);

    }


    private String interpreteIMC(float imc){
        if(imc <= 16.5) return  getString(R.string.imc_1);
        if(imc <= 18.5) return getString(R.string.imc_2);
        if(imc <= 25) return getString(R.string.imc_3);
        if(imc <= 30) return getString(R.string.imc_4);
        if(imc <= 35) return getString(R.string.imc_5);
        if(imc <= 40) return getString(R.string.imc_6);
        return getString(R.string.imc_7);
    }


    // Listeners
    private View.OnClickListener envoyerListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //  on récupère la taille
            String t = taille.getText().toString();
            // On récupère le poids
            String p = poids.getText().toString();
            float tValue = Float.valueOf(t);

            // Puis on vérifie que la taille est cohérente
            if(tValue <= 0)
                Toast.makeText(MainActivity.this, getString(R.string.err_neg_size), Toast.LENGTH_SHORT).show();
            else {
                float pValue = Float.valueOf(p);
                if(pValue <= 0)
                    Toast.makeText(MainActivity.this, getString(R.string.err_neg_weight), Toast.LENGTH_SHORT).show();
                else {
                    // Si l'utilisateur a indiqué que la taille était en centimètres
                    // On vérifie que la Checkbox sélectionnée est la deuxième à l'aide de son identifiant
                    if (group.getCheckedRadioButtonId() == R.id.radio_centimetre || t.contains(".")) tValue = tValue / 100;
                    float imc = pValue / (tValue * tValue);
                    String resultat = getString(R.string.imc_is) + imc + " . ";

                    if(commentaire.isChecked()) resultat += interpreteIMC(imc);

                    result.setText(resultat);
                }
            }
        }
    };


    private View.OnClickListener resetListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(texteInit);
        }
    };

    private View.OnClickListener checkedListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()) {
                result.setText(texteInit);
            }
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(texteInit);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}
    };

}