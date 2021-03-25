package com.mc.listedesplanetes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Button btn;
    PlaneteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setEnabled(false);
        listview = findViewById(R.id.listView);

        Data data = new Data();

        adapter = new PlaneteAdapter(this, data);
        listview.setAdapter(adapter);

        btn.setOnClickListener(v -> {
            int score = 0;

            for (int i=0; i<data.taillePlanetes.length; i++){
                View vw = listview.getChildAt(i);
                Spinner spinner = vw.findViewById(R.id.spinner);
                String tailleChoisie = spinner.getSelectedItem().toString();
                if(tailleChoisie.equals(data.taillePlanetes[i])){
                    score += 1;
                }
            }

            Toast.makeText(MainActivity.this, "Score : "+score+"/"+data.taillePlanetes.length, Toast.LENGTH_LONG).show();
        });






    }

}