package com.blogspot.sam4rth.tictactoenotebook;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class pvp extends AppCompatActivity {

    boolean gameActive = true;
    int a=2,b=1,c=0,d=0;
    public int scoreX = 0;
    public int scoreO = 0;
    Vibrator v;
    MediaPlayer firstSound;
    int activePlayer = 0;
    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    @SuppressLint("SetTextI18n")
    public void playerTap(View view){
        if(!gameActive){
            gameReset(view);
        }
        else {
            c=0;
            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());

            if(gameState[tappedImage] == 2) {//dwsde
                gameState[tappedImage] = activePlayer;
                if (activePlayer == 0) {
                    firstSound = MediaPlayer.create(pvp.this, R.raw.tap);
                    firstSound.start();
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    ImageView status = findViewById(R.id.imageView13);
                    status.setImageResource(R.drawable.o);

                } else {
                    firstSound = MediaPlayer.create(pvp.this, R.raw.tap);
                    firstSound.start();
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    ImageView status = findViewById(R.id.imageView13);
                    status.setImageResource(R.drawable.x);
                }
            }

            // Check if any player has woned
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]]!=2) {

                    a = gameState[winPosition[0]];
                    gameActive = false;

                }
            }
            // Somebody has won! - Find out who!



            if (a == 0) {
                oneforX();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    v.vibrate(VibrationEffect.createOneShot
                            (100,VibrationEffect.DEFAULT_AMPLITUDE));
                }else{
                    v.vibrate(100);
                }
                firstSound = MediaPlayer.create(pvp.this, R.raw.one);
                firstSound.start();
                Toast toastx = Toast.makeText(this, "X Wins!", Toast.LENGTH_SHORT);
                toastx.setGravity(Gravity.CENTER,0,0);
                toastx.show();
                //TextView status = findViewById(R.id.status);
                //status.setText(winnerStr);
                gameActive = false;

            }
            else if (a == 1) {
                oneforO();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    v.vibrate(VibrationEffect.createOneShot
                            (100,VibrationEffect.DEFAULT_AMPLITUDE));
                }else{
                    v.vibrate(100);
                }
                firstSound = MediaPlayer.create(pvp.this, R.raw.one);
                firstSound.start();
                Toast toasto = Toast.makeText(this, "O Wins!!!", Toast.LENGTH_SHORT);
                toasto.setGravity(Gravity.CENTER,0,0);
                toasto.show();
                //TextView status = findViewById(R.id.status);
                //status.setText(winnerStr);
                gameActive = false;
            }
            else if (gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 &&
                    gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 &&
                    gameState[6] != 2  && gameState[7] != 2 && gameState[8] != 2) {
                if (b==1){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        v.vibrate(VibrationEffect.createOneShot
                                (100,VibrationEffect.DEFAULT_AMPLITUDE));
                    }else{
                        v.vibrate(100);
                    }
                    firstSound = MediaPlayer.create(pvp.this, R.raw.one);
                    firstSound.start();
                    Toast toasttie = Toast.makeText
                            (this, "Oops its a Tie...", Toast.LENGTH_SHORT);
                    toasttie.setGravity(Gravity.CENTER,0,0);
                    toasttie.show(); }
                else{
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        v.vibrate(VibrationEffect.createOneShot
                                (100,VibrationEffect.DEFAULT_AMPLITUDE));
                    }else{
                        v.vibrate(100);
                    }
                    firstSound = MediaPlayer.create(pvp.this, R.raw.one);
                    firstSound.start();
                    Toast toasttie2 = Toast.makeText
                            (this, "Man! Its total "+b+" Ties!!!", Toast.LENGTH_SHORT);
                    toasttie2.setGravity(Gravity.CENTER,0,0);
                    toasttie2.show();}
                b++;
                //TextView status = findViewById(R.id.status);
                //status.setText(winnerStr);
                gameActive = false;
            }



            // Update the status bar for winner announcement


        }
    }

    public void eraser(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            v.vibrate(VibrationEffect.createOneShot
                    (100,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            v.vibrate(100);
        }
        Toast toastreset = Toast.makeText
                (this, "Restarting the game...", Toast.LENGTH_SHORT);
        toastreset.setGravity(Gravity.CENTER,0,0);
        toastreset.show();
        c=0;
        gameReset(view);
    }

//status.setImageResource(R.drawable.x);
    @SuppressLint("SetTextI18n")
    public void gameReset(View view) {
        gameActive = true;
        if (d == 1) { activePlayer = 0; ImageView status = findViewById(R.id.imageView13);
            status.setImageResource(R.drawable.x);d=0;}
        else{activePlayer=1;ImageView status = findViewById(R.id.imageView13);
            status.setImageResource(R.drawable.o);d=1;}
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        //TextView status = findViewById(R.id.status);
        //status.setText("X's Turn - Tap to play");
        a = 2;

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void displayForX() {
        TextView scoreView =  findViewById(R.id.xscore);
        scoreView.setText(String.valueOf(scoreX));
    }
    public void oneforX() {
        scoreX = scoreX + 1;
        displayForX();
    }



    public void displayForO() {
        TextView scoreView = findViewById(R.id.oscore);
        scoreView.setText(String.valueOf(scoreO));
    }


    public void oneforO() {
        scoreO = scoreO + 1;
        displayForO();
    }

    public void resetscore(View view){


        if (c==1) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot
                        (100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(100);
            }
            Toast toastresets = Toast.makeText
                    (this, "Resetting Points...", Toast.LENGTH_SHORT);
            toastresets.setGravity(Gravity.CENTER, 0, 0);
            toastresets.show();

            scoreX = 0;
            scoreO = 0;
            displayForX();
            displayForO();
            gameReset(view);
            c =0;
            b=1;
        }
        else{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot
                        (500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(500);
            }
            Toast toastreset1 = Toast.makeText
                    (this, "Tap again to reset point...\nElse Tap on Eraser...",
                            Toast.LENGTH_LONG);
            toastreset1.show();
            c=1;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(R.layout.activity_pvp);
    }
}