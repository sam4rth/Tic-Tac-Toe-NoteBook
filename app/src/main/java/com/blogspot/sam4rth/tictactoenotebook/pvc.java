package com.blogspot.sam4rth.tictactoenotebook;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class pvc extends AppCompatActivity {
    int count =0;
    //best movie
    int bestMove;
    //gamestate
    boolean gameActive = true;
    //Diff
    int diff = 3;RadioButton dif;// easy 1, medium 2, hard 4 impossible !
    //players
    int player = 0; int opponent = 1;
    //somethings
    int a=2,b=1,c=0,d=1;
    //score
    public int scoreX = 0;
    public int scoreO = 0;
    //vibrator
    Vibrator v;
    //active player
    int activePlayer = 1;
    //board
    int[] board = {2,2,2,2,2,2,2,2,2};
    //win position
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    //MediaPlayer
    MediaPlayer firstSound;
    //is the player X or O

    int last = 0;
    //Game starts here!!!
    public void startGame() {

        if (gameActive) {
            if (activePlayer == 0) {
                int move = findbestPosition();

                board[move]=player;
                if(SettingsActivity.se){
                firstSound = MediaPlayer.create(pvc.this, R.raw.tap);

                firstSound.start();}
                ImageView img = findViewById(0);
                if (move == 0) {
                    img=findViewById(R.id.imageView0);
                }
                if (move == 1) {
                    img=findViewById(R.id.imageView1);
                }
                if (move == 2) {
                    img=findViewById(R.id.imageView2);
                }
                if (move == 3) {
                    img=findViewById(R.id.imageView3);
                }
                if (move == 4) {
                    img=findViewById(R.id.imageView4);
                }
                if (move == 5) {
                    img=findViewById(R.id.imageView5);
                }
                if (move == 6) {
                    img=findViewById(R.id.imageView6);
                }
                if (move == 7) {
                    img=findViewById(R.id.imageView7);
                }
                if (move == 8) {
                    img=findViewById(R.id.imageView8);
                }
                    img.setImageResource(R.drawable.o);

                activePlayer = 1;
                count =0;
            }
            checkWin();
        }
        else{
            gameReset();
        }

    }
    //finding move for the CPU starts here
    int findbestPosition(){
        int bestVal = -1000;
        if (diff==3)
            bestVal = 1000;
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 9; i++)
        {// Check if cell is empty

                if (board[i] == 2)
                {
                    // Make the move
                    board[i] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(0,false);

                    // Undo the move
                    board[i] = 2;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if(moveVal<bestVal&& diff==3){
                        bestMove = i;
                        bestVal = moveVal;
                    }
                    else if (moveVal > bestVal)
                    {
                        bestMove = i;
                        bestVal = moveVal;
                    }
                }
            }
        return bestMove;
    }
    int minimax(int depth,boolean isMax){



        int score = evaluate();

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft())
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 9; i++)
            {
                    // Check if cell is empty
                    if (board[i]==2)
                    {
                        // Make the move
                        board[i] = player;

                        // Call minimax recursively and choose
                        // the maximum value

                        int mmresult = minimax(depth + 1, false)-depth;
                        best = Math.max(best,mmresult);


                        // Undo the move
                        board[i] = 2;
                        if(depth>=diff){
                            return best;
                        }
//                        alpha = Math.max(alpha,mmresult);
//                        if (beta <= alpha){
//                            return best;
//                        }
                    }

            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;
            int mmresult;

            // Traverse all cells
            for (int i = 0; i < 9; i++)
            {
                    // Check if cell is empty
                    if (board[i] == 2)
                    {
                        // Make the move
                        board[i] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value

                        mmresult =minimax(depth + 1,true)+depth;
                        best = Math.min(best,mmresult);
                        board[i] = 2;
                        if(depth>=diff){

                            return best;
                        }


                        // Undo the move


                    }

            }
            return best;
        }

    }
    int evaluate() {
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (board[winPosition[0]] == board[winPosition[1]] &&
                    board[winPosition[1]] == board[winPosition[2]] &&
                    board[winPosition[0]] != 2) {

                a = board[winPosition[0]];

            }
        }
        // Somebody has won! - Find out who!
        if (a == 0) {a=2; return 10;  }
        else if (a == 1) { a=2;return -10; }
        a=2;
        return 0;
    }
    Boolean isMovesLeft() {
        for (int i = 0; i < 9; i++)
                if (board[i] == 2)
                    return true;
        return false;
    }
    //finding move for the CPU ends here

    public void playerTap(View view) {
        if (!gameActive) {
            gameReset();
        } else {
            if(activePlayer==1){
            c = 0;
            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());
                if(SettingsActivity.se){
                firstSound = MediaPlayer.create(pvc.this, R.raw.tap);
                firstSound.start();}
            if (board[tappedImage] == 2) {
                board[tappedImage] = activePlayer;
                    img.setImageResource(R.drawable.x);
                    activePlayer = 0;
            }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkWin();
                        startGame();
                    }
                },500);


        }}
    }
    
    void checkWin() {

        // Check if any player has woned
        for (int[] winPosition : winPositions) {

            if (board[winPosition[0]] == board[winPosition[1]] &&
                    board[winPosition[1]] == board[winPosition[2]] &&
                    board[winPosition[0]] != 2) {


                a = board[winPosition[0]];
                last++;
                gameActive = false;


            }
        }
        // Somebody has won! - Find out who!


        if (a == 0) {
            oneforO();
            if(SettingsActivity.vi)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot
                        (100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                v.vibrate(100);
                }
            if(SettingsActivity.se){
            firstSound = MediaPlayer.create(pvc.this, R.raw.one);
            firstSound.start();}
            Toast toastx = Toast.makeText(this, "O Wins!", Toast.LENGTH_SHORT);
            toastx.setGravity(Gravity.CENTER, 0, 0);
            toastx.show();
            //TextView status = findViewById(R.id.status);
            //status.setText(winnerStr);
            gameActive = false;

        } else if (a == 1) {
            oneforX();
            if(SettingsActivity.vi)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot
                        (100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(100);
            }
            if(SettingsActivity.se){
            firstSound = MediaPlayer.create(pvc.this, R.raw.one);
            firstSound.start();}
            Toast toasto = Toast.makeText(this, "X Wins!!!", Toast.LENGTH_SHORT);
            toasto.setGravity(Gravity.CENTER, 0, 0);
            toasto.show();
            //TextView status = findViewById(R.id.status);
            //status.setText(winnerStr);
            gameActive = false;
        } else if (board[0] != 2 && board[1] != 2 && board[2] != 2 &&
                board[3] != 2 && board[4] != 2 && board[5] != 2 &&
                board[6] != 2 && board[7] != 2 && board[8] != 2) {

            if (b == 1) {
                if(SettingsActivity.vi)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot
                            (100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(100);
                }
                if(SettingsActivity.se){
                firstSound = MediaPlayer.create(pvc.this, R.raw.one);
                firstSound.start();}
                Toast toasttie = Toast.makeText
                        (this, "Oops its a Tie...", Toast.LENGTH_SHORT);
                toasttie.setGravity(Gravity.CENTER, 0, 0);
                toasttie.show();
            } else {
                if(SettingsActivity.vi)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot
                            (100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(100);
                }
                if(SettingsActivity.se){
                firstSound = MediaPlayer.create(pvc.this, R.raw.one);
                firstSound.start();}
                Toast toasttie2 = Toast.makeText
                        (this, "Man! Its total " + b + " Ties!!!", Toast.LENGTH_SHORT);
                toasttie2.setGravity(Gravity.CENTER, 0, 0);
                toasttie2.show();
            }
            b++;
            //TextView status = findViewById(R.id.status);
            //status.setText(winnerStr);
            gameActive = false;

        }
    }
    
    //OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(R.layout.activity_pvc);

        dif = findViewById(R.id.easy);
        dif.setChecked(true);
        diff =3;
        startGame();
    }
    
    //When Taped on The eraser does this
    public void eraser(View view){
        if(SettingsActivity.vi)
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
        gameReset();
    }


    
    //Foe window focus
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

    //Displays the score
    public void displayForO() {
        TextView scoreView =  findViewById(R.id.oscore);
        scoreView.setText(String.valueOf(scoreO));
    }
    public void oneforX() {
        scoreX = scoreX + 1;
        displayForX();
    }
    public void displayForX() {
        TextView scoreView = findViewById(R.id.xscore);
        scoreView.setText(String.valueOf(scoreX));
    }
    public void oneforO() {
        scoreO = scoreO + 1;
        displayForO();
    }

    //reset Scores and even restarts the game
    public void resetscore(View view){


        if (c==1) {
            if(SettingsActivity.vi)
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
            gameReset();
            c =0;
            b=1;
        }
        else{
            if(SettingsActivity.vi)
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton)view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.easy:
                if (checked)
                    diff=3;
                if(last%5==0)
                    diff=100;
                break;
            case R.id.medium:
                if (checked)
                    diff=2;
                if(last%5==0)
                    diff=3;
                else if (last%3==0)
                    diff = 4;
                break;
            case R.id.hard:
                if (checked)
                    diff=5;
                if(last%5==0)
                    diff = 2;
                if (last%3==0)
                    diff = 4;
                break;
            case R.id.impssible:
                if (checked)
                    diff =10000;
                break;

        }

        // Check which radio button was clicked
        dif=(RadioButton) view;
        gameReset();

    }

    public void setDiff() {
        // Is the button now checked?
        boolean checked = dif.isChecked();
        // Check which radio button was clicked
        switch(dif.getId()) {
            case R.id.easy:
                if (checked)

                        diff=3;
                    if(last%5==0)
                        diff=100;
                    break;
            case R.id.medium:
                if (checked)
                        diff=2;
                    if(last%5==0&&last!=0)
                        diff=3;
                    else if (last%3==0&&last!=0)
                        diff = 4;
                break;
            case R.id.hard:
                if (checked)
                        diff=5;
                    if(last%5==0&&last!=0)
                        diff = 2;
                    if (last%3==0&&last!=0)
                        diff = 4;
                break;
            case R.id.impssible:
                if (checked)
                    diff =10000;
                if(last%13==0&&last!=0){
                    diff = 2;
                    Toast.makeText(this, "Its the right time!Shoot!!!", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    @SuppressLint("SetTextI18n")
    public void gameReset() {
        gameActive = true;
        if(d == 1){ activePlayer = 0;d=0;}else{activePlayer=1;d=1;}

        Arrays.fill(board, 2);
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
        setDiff();
        startGame();

    }
//    public void onRadioButton(View view) {
//        // Is the button now checked?
//        RadioButton r = findViewById(R.id.radioButton2);
//        RadioButton r2 = findViewById(R.id.radioButton);
//
//
//        boolean checked = ((RadioButton) view).isChecked();
//        if(xoro==0){
//            ((RadioButton) view).setChecked(true);
//        }
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radioButton:
//                if (checked)
//                    xoro=1;
//                r.setChecked(false);
//                gameReset();
//                break;
//            case R.id.radioButton2:
//                if (checked)
//                    xoro=0;
//                r2.setChecked(false);
//                gameReset();
//                // Ninjas rule
//                break;
//
//        }
//    }
}