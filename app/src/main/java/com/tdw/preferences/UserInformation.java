package com.tdw.preferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
    }

    public void startGames (View view) {
        Intent intent = new Intent(UserInformation.this,SurveyZero.class);
        startActivity(intent);
    }
}
