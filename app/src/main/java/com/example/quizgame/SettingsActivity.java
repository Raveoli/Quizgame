/*
* Code to toggle sound in the app
* Written by Raveena R Hegde 04/25/2017
* */
package com.example.quizgame;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {

    static boolean play;
    private static Bundle bundle = new Bundle();
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toggle = (ToggleButton) findViewById(R.id.soundToggleButton);
        //set font for title
        TextView tx = (TextView)findViewById(R.id.settingsTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
        //Log.d("Clicked","Play button"+play);
    }

    //Get value of toggle button
    public void onToggleBtnClick(View view){
        play = ((ToggleButton) view).isChecked();
        //Log.d("Clicked","After click Play button"+play);
    }

    //Saving state of toggle button
    public void onPause() {
        super.onPause();
        bundle.putBoolean("ToggleButtonState", play);
    }

    @Override
    public void onResume() {
        super.onResume();
        toggle.setChecked(bundle.getBoolean("ToggleButtonState",false));
    }

}
