package com.tdw.preferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tdw.preferences.surveys.SurveyOneF;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        if(getIntent().getBooleanExtra("StartF",false)){
            Intent intent = new Intent(WelcomeScreen.this,SurveyOneF.class);
            startActivity(intent);
            WelcomeScreen.this.finish();
        }
    }

    public void getUserInformation (View view) {
        Intent intent = new Intent(WelcomeScreen.this,UserInformation.class);
        startActivity(intent);
    }
}
