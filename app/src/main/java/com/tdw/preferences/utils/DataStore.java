package com.tdw.preferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tdw.preferences.R;
import com.tdw.preferences.models.user;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddhaja on 1/10/2016.
 */
public class DataStore {
    public static SharedPreferences userInformationStore = null;
    public static SharedPreferences surveyDataStore = null;
    private static Context mContext;
    static Type listUsers = new TypeToken<List<user>>(){}.getType();

    private DataStore() {} //Prevent Instantiation

    public static SharedPreferences getUserInformationStore(Context mContext){
        if (userInformationStore ==null){
            userInformationStore = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key_user_information),Context.MODE_PRIVATE);
        }
        return userInformationStore;
    }

    public static void init(Context mContextArg){
        if (userInformationStore ==null){
            userInformationStore = mContextArg.getSharedPreferences(mContextArg.getString(R.string.preference_file_key_user_information),Context.MODE_PRIVATE);
            mContext = mContextArg;
        }
        if (surveyDataStore ==null){
            surveyDataStore = mContextArg.getSharedPreferences(mContextArg.getString(R.string.preference_file_key_survey_data),Context.MODE_PRIVATE);
        }
        if(getUserList()==null){
            List<user> userList = new ArrayList<user>();
            setUserList(userList);
        }

    }

    public static SharedPreferences getProgramDataStore(Context mContext){
        if (surveyDataStore ==null){
            surveyDataStore = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key_survey_data),Context.MODE_PRIVATE);
        }
        return surveyDataStore;
    }


    public static List<user> getUserList(){
        Gson gson = new Gson();
        String UserList = userInformationStore.getString(mContext.getString(R.string.users_list),null);
        return gson.fromJson(UserList, listUsers);
    }
    public static void setUserList(List<user> users_list){
        Gson gson = new Gson();
        String UserList = gson.toJson(users_list,listUsers);
        SharedPreferences.Editor SPEditor = userInformationStore.edit();
        SPEditor.putString(mContext.getString(R.string.users_list),UserList);
        SPEditor.commit();
    }

}
