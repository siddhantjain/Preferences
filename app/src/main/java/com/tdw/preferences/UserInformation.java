package com.tdw.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tdw.preferences.models.game;
import com.tdw.preferences.models.user;
import com.tdw.preferences.surveys.SurveyZero;
import com.tdw.preferences.utils.DataStore;

import java.util.List;

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
        userLocation = etUserName.getText().toString();

        /*
            siddhant: Confirm with Aki on how Datastore works
            Creating a new user object.
            Getting list of existing users from datastore
            adding to that list
            setting list of users in Datastore
        */

        List<user> userList = DataStore.getUserList();

        long numberOfUsers = userList.size();
        user currentUser = new user();

        currentUser.setId(numberOfUsers+1);

        if(userName!=null){
            currentUser.setName(userName);
        }
        else{
            //TODO: show error message
        }

        if(userLocation!=null){
            currentUser.setLocation(userLocation);
        }
        else{
            //TODO: show error message
        }

        userList.add(currentUser);
        DataStore.setUserList(userList);

        /* Testing datastore logic
        userList = DataStore.getUserList();
        for(int i=0;i<userList.size();i++){
            System.out.println(userList.get(i).getName());
        }

        List<game> gamesList = DataStore.getGameList();
        System.out.println(gamesList.size());
        for(int i=0;i<gamesList.size();i++){
            System.out.println(gamesList.get(i).getExchangeRate1());
        }
        */
        Intent intent = new Intent(UserInformation.this,SurveyZero.class);
        startActivity(intent);
    }
}
