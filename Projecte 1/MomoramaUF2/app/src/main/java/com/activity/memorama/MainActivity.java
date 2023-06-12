package com.activity.memorama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play, sortir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.botoMainJugar);
        sortir = findViewById(R.id.botoMainSortir);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarJoc();
            }
        });

        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void iniciarJoc(){
        Intent i = new Intent(this, Memorama.class);
        startActivity(i);
    }
}
