package com.tdw.preferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tdw.preferences.R;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by siddhaja on 1/10/2016.
 */
public class DataStore {
    public static SharedPreferences userInformationStore = null;
    public static SharedPreferences surveyDataStore = null;
    private static Context mContext;

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
    }

    public static SharedPreferences getProgramDataStore(Context mContext){
        if (surveyDataStore ==null){
            surveyDataStore = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key_survey_data),Context.MODE_PRIVATE);
        }
        return surveyDataStore;
    }


    public static String getUserId(){
        return userInformationStore.getString(mContext.getString(R.string.user_id),null);
    }
    public static void setUserId(String user_id){
        SharedPreferences.Editor SPEditor = userInformationStore.edit();
        SPEditor.putString(mContext.getString(R.string.user_id),user_id);
        SPEditor.commit();
    }

    public static String getUserName(){
        return userInformationStore.getString(mContext.getString(R.string.user_name),null);
    }
    public static void setUserName(String user_name){
        SharedPreferences.Editor SPEditor = userInformationStore.edit();
        SPEditor.putString(mContext.getString(R.string.user_name),user_name);
        SPEditor.commit();
    }

    public static String getUserLocation(){
        return userInformationStore.getString(mContext.getString(R.string.user_id),null);
    }
    public static void setUserLocation(String user_location){
        SharedPreferences.Editor SPEditor = userInformationStore.edit();
        SPEditor.putString(mContext.getString(R.string.user_location),user_location);
        SPEditor.commit();
    }

}
