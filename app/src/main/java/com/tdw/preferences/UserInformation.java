package com.tdw.preferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tdw.preferences.models.game;
import com.tdw.preferences.models.user;
import com.tdw.preferences.surveys.SurveyOne;
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
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getApplicationContext().getString(R.string.instructions_title));
        alertDialog.setMessage(getApplicationContext().getString(R.string.instructions_test_body));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        etUserName = (EditText) findViewById(R.id.etUserName);
                        etUserLocation = (EditText) findViewById(R.id.etUserLocation);

                        String userName, userLocation;
                        userName = etUserName.getText().toString();
                        userLocation = etUserLocation.getText().toString();

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

                        // Testing datastore logic
                        userList = DataStore.getUserList();
                        for(int i=0;i<userList.size();i++){
                            System.out.println(userList.get(i).getName());
                            System.out.println(userList.get(i).getLocation());
                        }

                        List<game> gamesList = DataStore.getGameList();
                        System.out.println(gamesList.size());
                        for(int i=0;i<gamesList.size();i++){
                            System.out.println(gamesList.get(i).getExchangeRate1());
                        }
                        dialog.dismiss();
                        Intent intent = new Intent(UserInformation.this, TestUserUnderstanding.class);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
}
