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
        /*SharedPreferences sharedPrefs = getSharedPreferences("com.example.xyle", MODE_PRIVATE);
        toggle.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", true));*/
        //set font for title
        TextView tx = (TextView)findViewById(R.id.settingsTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Macondo-Regular.ttf");
        tx.setTypeface(custom_font);
        //Log.d("Clicked","Play button"+play);
    }

    public void onToggleBtnClick(View view){
        play = ((ToggleButton) view).isChecked();
        /*if(play){
            SharedPreferences.Editor editor = getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
            editor.putBoolean("NameOfThingToSave", true);
            editor.commit();
        }
        else{
            SharedPreferences.Editor editor = getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
            editor.putBoolean("NameOfThingToSave", false);
            editor.commit();
        }*/
        //Log.d("Clicked","After click Play button"+play);
    }

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
