package com.blogspot.sam4rth.tictactoenotebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static boolean se = true,vi = true;

//o = 0 x = 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Switch switch1 = findViewById(R.id.switch3);
        Switch switch2 = findViewById(R.id.switch4);
        if(vi){
            switch2.setChecked(true);
        }
        if(se){
            switch1.setChecked(true);
        }

    }
    public void soundeffects(View view){
        Switch switch1 = (Switch) view;

        se =switch1.isChecked();
    }
    public void vib(View view){
        Switch switch2 = (Switch) view;

        vi =switch2.isChecked();
    }
    public void blog(View v){

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sam4rth.blogspot.com")));

    }
    public void rate(View view){
        Toast.makeText(this, "This does nothing for now!!!\nSorryyyyyyyyy!!!", Toast.LENGTH_SHORT).show();
    }
    public void email(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:sam4rth@gmail.com")); // only email apps should handle this
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
    }
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


}