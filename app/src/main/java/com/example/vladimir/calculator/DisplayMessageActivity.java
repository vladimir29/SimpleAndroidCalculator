package com.example.vladimir.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.*;

public class DisplayMessageActivity extends AppCompatActivity {

    public static void CreateArrays(String sir, ArrayList<Character> semne, ArrayList<Float> numere) {

        int i = 0, contorInt = -1, contorSemne = -1;
        float nr = 0;

        while (i < sir.length()) {

            if(sir.charAt(i) != '+' && sir.charAt(i) != '-'
                    && sir.charAt(i) != '*' && sir.charAt(i) != '/' && i != sir.length()-1) {

                nr = 10*nr + sir.charAt(i) - '0';
            }
            else {

                contorInt++;
                if (i == sir.length()-1) {

                    nr = 10*nr + sir.charAt(i) - '0';
                    numere.add(contorInt, nr);

                }
                else {

                    numere.add(contorInt, nr);

                }
                nr = 0;

                contorSemne++;
                semne.add(contorSemne, sir.charAt(i));
            }

            i++;

        }

    }

    public static void SolveMultiDiv(ArrayList<Character> semne, ArrayList<Float> numere) {

        int i = 0;

        while (i < semne.size()) {

            char semn = semne.get(i);

            if(semn == '*') {

                float nou = numere.get(i)*numere.get(i+1);
                numere.add(i, nou);
                numere.remove(i+1);
                numere.remove(i+1);
                semne.remove(i);

            }
            else if(semn == '/') {

                float nou = numere.get(i)/numere.get(i+1);
                numere.add(i, nou);
                numere.remove(i+1);
                numere.remove(i+1);
                semne.remove(i);

            }
            else {
                i++;
            }
        }
    }

    public static String Evaluate(String sir) {

        int i, j = 0;

        ArrayList<Character> semne = new ArrayList<Character>();
        ArrayList<Float> numere = new ArrayList<Float>();

        CreateArrays(sir, semne, numere);

        SolveMultiDiv(semne, numere);

        float rezultat = numere.get(0);

        for(i = 1; i < numere.size(); i++) {

            char semn = semne.get(j);
            if(semn == '+') {
                rezultat = rezultat+numere.get(i);
            }

            if(semn == '-') {
                rezultat = rezultat-numere.get(i);
            }

            j++;

        }

        String rezultatString = Float.toString(rezultat);

        return rezultatString;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        String rezultat = Evaluate(message);
        textView.setText(rezultat);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
    }

}

