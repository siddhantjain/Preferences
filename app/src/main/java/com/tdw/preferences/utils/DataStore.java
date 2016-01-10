package com.tdw.preferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tdw.preferences.R;
import com.tdw.preferences.models.game;
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
    public static SharedPreferences gameDataStore = null;
    private static Context mContext;
    static Type listUsers = new TypeToken<List<user>>(){}.getType();
    static Type listGames = new TypeToken<List<game>>(){}.getType();

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
        if (gameDataStore ==null){
            gameDataStore = mContextArg.getSharedPreferences(mContextArg.getString(R.string.preference_file_key_games_data),Context.MODE_PRIVATE);
        }
        if(getUserList()==null){
            List<user> userList = new ArrayList<user>();
            setUserList(userList);
        }
        if(getGameList()==null){
            List<game> gamesList = new ArrayList<game>();
            setGameList(gamesList);
        }
        initGameDefaults();
    }

    public static void initGameDefaults(){
        int numberOfGames =6;
        List<game> gameList = getGameList();
        float[][] interestRates = new float[][]{
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f}
        };
        int[] soonerDates = {7,7,7,7,7,7};
        int[] laterDates = {14,14,14,14,14,14};
        for(int i=0;i<numberOfGames;i++){
            game tempGame = new game(interestRates[i][0],interestRates[i][1],interestRates[i][2],interestRates[i][3],interestRates[i][4],interestRates[i][5]
                    ,soonerDates[i],laterDates[i]);
            gameList.add(tempGame);
                System.out.println("Entered");
        }
        setGameList(gameList);
    }
    public static SharedPreferences getGameDataStore(Context mContext){
        if (gameDataStore ==null){
            gameDataStore = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key_games_data),Context.MODE_PRIVATE);
        }
        return gameDataStore;
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

    public static List<game> getGameList(){
        Gson gson = new Gson();
        String gameList = gameDataStore.getString(mContext.getString(R.string.games_list), null);
        return gson.fromJson(gameList, listGames);
    }
    public static void setGameList(List<game> games_list){
        Gson gson = new Gson();
        String gamesList = gson.toJson(games_list,listGames);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.games_list),gamesList);
        SPEditor.commit();
    }

}
