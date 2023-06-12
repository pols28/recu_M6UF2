package com.activity.memorama;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class Memorama extends Activity {

    Animation hyperspaceJumpAnimation;
    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15;
    ImageButton[] grid = new ImageButton[16];
    Button botoReiniciar;
    Button botoSortir;
    TextView textPunts;

    int f;
    int v;
    int[] img;
    int fondo;

    ArrayList<Integer> arrayMix;
    ImageButton first;
    int numFirst, numSecond;
    boolean block = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joc);
        init();
        hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.flip);

    }

    private void gridStart(){
        imb00 = findViewById(R.id.boto00);
        imb01 = findViewById(R.id.boto01);
        imb02 = findViewById(R.id.boto02);
        imb03 = findViewById(R.id.boto03);
        imb04 = findViewById(R.id.boto04);
        imb05 = findViewById(R.id.boto05);
        imb06 = findViewById(R.id.boto06);
        imb07 = findViewById(R.id.boto07);
        imb08 = findViewById(R.id.boto08);
        imb09 = findViewById(R.id.boto09);
        imb10 = findViewById(R.id.boto10);
        imb11 = findViewById(R.id.boto11);
        imb12 = findViewById(R.id.boto12);
        imb13 = findViewById(R.id.boto13);
        imb14 = findViewById(R.id.boto14);
        imb15 = findViewById(R.id.boto15);
        grid[0] = imb00;
        grid[1] = imb01;
        grid[2] = imb02;
        grid[3] = imb03;
        grid[4] = imb04;
        grid[5] = imb05;
        grid[6] = imb06;
        grid[7] = imb07;
        grid[8] = imb08;
        grid[9] = imb09;
        grid[10] = imb10;
        grid[11] = imb11;
        grid[12] = imb12;
        grid[13] = imb13;
        grid[14] = imb14;
        grid[15] = imb15;
    }

    private void TestStart(){
        textPunts = findViewById(R.id.texto_puntuacion);
        f = 0;
        v = 0;
        textPunts.setText("Punts: " + f);
    }

    private void ButtonStart(){
        botoReiniciar = findViewById(R.id.botonJuegoReiniciar);
        botoSortir = findViewById(R.id.botonJuegoSalir);
        botoReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        botoSortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ImageStart(){
        img = new int[]{
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4,
                R.drawable.la5,
                R.drawable.la6,
                R.drawable.la7
        };
        fondo = R.drawable.fondo;
    }

    private ArrayList<Integer> barreja(int a){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<a*2; i++){
            result.add(i % a);
        }
        Collections.shuffle(result);
        return result;
    }


    private void check(int i, final ImageButton imgb){
        if(first == null){
            first = imgb;
            first.setScaleType(ImageView.ScaleType.CENTER_CROP);
            first.setImageResource(img[arrayMix.get(i)]);
            first.setEnabled(false);
            numFirst = arrayMix.get(i);
        } else {
            block = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(img[arrayMix.get(i)]);
            imgb.setEnabled(false);
            numSecond = arrayMix.get(i);
            if(numFirst == numSecond){
                first = null;
                block = false;
                v++;
                v++;
                f++;
                textPunts.setText("Punts: " + f);
            } else {
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       first.setScaleType(ImageView.ScaleType.CENTER_CROP);
                       first.setImageResource(fondo);
                       first.setEnabled(true);
                       imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                       imgb.setImageResource(fondo);
                       imgb.setEnabled(true);
                       block = false;
                       first = null;
                       f--;
                       textPunts.setText("Punts: " + f);
                   }
               }, 500);
            }
        }
    }

    private void init(){
        gridStart();
        ButtonStart();
        TestStart();
        ImageStart();
        arrayMix = barreja(img.length);
        for(int i = 0; i< grid.length; i++){
            grid[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            grid[i].setImageResource(img[arrayMix.get(i)]);

        }

       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               for(int i = 0; i< grid.length; i++){
                   grid[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                   grid[i].setImageResource(fondo);

               }
           }
       }, 500);
        for(int i = 0; i< grid.length; i++) {
            final int j = i;
            grid[i].setEnabled(true);
            grid[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(hyperspaceJumpAnimation);
                    if(!block) {
                        check(j, grid[j]);
                    }
                }
            });
        }

    }

}
