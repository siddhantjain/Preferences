package com.tdw.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tdw.preferences.surveys.SurveyZero;
import com.tdw.preferences.utils.DataStore;

public class UserInformation extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataStore.init(getApplicationContext());
        setContentView(R.layout.activity_user_information);
    }

    public void startGames (View view) {

        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserLocation = (EditText) findViewById(R.id.etUserLocation);

        String userName, userLocation;
        userName = etUserName.getText().toString();
        userLocation = etUserName.getPrivateImeOptions().toString();



        if(userName!=null){
            DataStore.setUserName(userName);
        }
        else{
            //TODO: show error message
        }

        if(userLocation!=null){
            DataStore.setUserLocation(userLocation);
        }
        else{
            //TODO: show error message
        }
        Intent intent = new Intent(UserInformation.this,SurveyZero.class);
        startActivity(intent);
    }
}
