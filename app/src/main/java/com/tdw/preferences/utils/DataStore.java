package com.tdw.preferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tdw.preferences.R;
import com.tdw.preferences.models.game;
import com.tdw.preferences.models.gameResult;
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
    final static int GAME_TYPE_E = 0;
    final static int GAME_TYPE_F = 1;

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
            initGameDefaults();
        }

    }

    public static void initGameDefaults(){
        int numberOfGames =6;
        List<game> gameList = getGameList();
        float[][] interestRatesSectionE = new float[][]{
                {1.0f, 1.1f, 1.25f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f}
        };
        int[] soonerDatesSectionE = {7,7,7,7,7,7};
        int[] laterDatesSectionE = {14,14,14,14,14,14};

        float[][] interestRatesSectionF = new float[][]{
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f}
        };
        int[] soonerDatesSectionF = {7,7,7,7,7,7};
        int[] laterDatesSectionF = {14,14,14,14,14,14};
        int[] cashRewardDatesSectionF = {28,28,28,28,28,28};
        for(int i=0;i<numberOfGames;i++){
            game tempGameE = new game(interestRatesSectionE[i][0],interestRatesSectionE[i][1],interestRatesSectionE[i][2],interestRatesSectionE[i][3],interestRatesSectionE[i][4],interestRatesSectionE[i][5]
                    ,soonerDatesSectionE[i],laterDatesSectionE[i], GAME_TYPE_E);
            gameList.add(tempGameE);
        }

        for(int i=0;i<numberOfGames;i++){
            game tempGameF = new game(interestRatesSectionF[i][0],interestRatesSectionF[i][1],interestRatesSectionF[i][2],interestRatesSectionF[i][3],interestRatesSectionF[i][4],interestRatesSectionF[i][5]
                    ,soonerDatesSectionF[i],laterDatesSectionF[i], GAME_TYPE_F);
            tempGameF.setNumberOfDaystoCashRewardDate(cashRewardDatesSectionF[i]);
            gameList.add(tempGameF);
        }


        setGameList(gameList);

        //Initialising game results based on game defaults here

    }
    public static SharedPreferences getGameDataStore(Context mContext){
        if (gameDataStore ==null){
            gameDataStore = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key_games_data),Context.MODE_PRIVATE);
        }
        return gameDataStore;
    }


    public static List<user> getUserList(){
        Gson gson = new Gson();
        String UserList = userInformationStore.getString(mContext.getString(R.string.users_list), null);
        return gson.fromJson(UserList, listUsers);
    }
    public static void setUserList(List<user> users_list){
        Gson gson = new Gson();
        String UserList = gson.toJson(users_list, listUsers);
        SharedPreferences.Editor SPEditor = userInformationStore.edit();
        SPEditor.putString(mContext.getString(R.string.users_list),UserList);
        SPEditor.commit();
    }

    public static List<game> getGameList(){
        Gson gson = new Gson();
        String gameList = gameDataStore.getString(mContext.getString(R.string.games_list), null);
        return gson.fromJson(gameList, listGames);
    }
    public static void setGameList(List<game> games_list) {
        Gson gson = new Gson();
        String gamesList = gson.toJson(games_list, listGames);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.games_list), gamesList);
        SPEditor.commit();
    }
    public static gameResult getSurveyOneResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game1_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game1_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveyOneResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game1_result), gr1str);
        SPEditor.commit();
    }
    public static gameResult getSurveyTwoResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game2_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game2_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveyTwoResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game2_result), gr1str);
        SPEditor.commit();
    }
    public static gameResult getSurveyThreeResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game3_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game3_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveyThreeResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game3_result), gr1str);
        SPEditor.commit();
    }
    public static gameResult getSurveyFourResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game4_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game4_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveyFourResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game4_result), gr1str);
        SPEditor.commit();
    }
    public static gameResult getSurveyFiveResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game5_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game5_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveyFiveResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game5_result), gr1str);
        SPEditor.commit();
    }
    public static gameResult getSurveySixResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game6_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game6_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveySixResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game6_result), gr1str);
        SPEditor.commit();
    }
    public static gameResult getSurveyOneFResult(){
        if(!gameDataStore.contains(mContext.getString(R.string.game1f_result))){
            gameResult gr = new gameResult();
            gr.initToNull();
            return gr;
        }
        Gson gson = new Gson();
        String grstr = gameDataStore.getString(mContext.getString(R.string.game1f_result), null);
        return gson.fromJson(grstr,gameResult.class);
    }
    public static void setSurveyOneFResult(gameResult gr){
        Gson gson = new Gson();
        String gr1str = gson.toJson(gr);
        SharedPreferences.Editor SPEditor = gameDataStore.edit();
        SPEditor.putString(mContext.getString(R.string.game1f_result), gr1str);
        SPEditor.commit();
    }
}
