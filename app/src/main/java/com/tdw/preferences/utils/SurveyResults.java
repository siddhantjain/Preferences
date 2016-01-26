package com.tdw.preferences.utils;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVWriter;
import com.tdw.preferences.models.game;
import com.tdw.preferences.models.gameResult;
import com.tdw.preferences.models.user;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by akash.jatangi on 1/26/16.
 */
public class SurveyResults {
    public static void writeToFile() throws IOException {
        gameResult gr1 = DataStore.getSurveyOneResult();
        gameResult gr2 = DataStore.getSurveyTwoResult();
        gameResult gr3 = DataStore.getSurveyThreeResult();
        gameResult gr4 = DataStore.getSurveyFourResult();
        gameResult gr5 = DataStore.getSurveyFiveResult();
        gameResult gr6 = DataStore.getSurveySixResult();
        gameResult gr1f = DataStore.getSurveyOneFResult();

        List<user> users = DataStore.getUserList();
        user u = users.get(users.size() - 1);

        List<game> games = DataStore.getGameList();

        //String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "SurveyResults.csv";
        //String filePath = baseDir + File.separator + fileName;
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);
        Log.v("FILEWRITE", f.getAbsolutePath());
        CSVWriter writer;
        // File exist
        if(f.exists() && !f.isDirectory()){
            Log.v("FILEWRITE","File_exists");
            FileWriter mFileWriter = new FileWriter(f,true);
            writer = new CSVWriter(mFileWriter);
        }
        else {
            Log.v("FILEWRITE","New file created");
            writer = new CSVWriter(new FileWriter(f));
            String[] headers = {"timestamp",
                    "user_id","user_name","user_location",
                    "e1_days_to_sooner_date","e1_days_to_later_date",
                    "e1_exchange_rate1","e1_exchange_rate2","e1_exchange_rate3","e1_exchange_rate4","e1_exchange_rate5","e1_exchange_rate6",
                    "e1_slider1_iv","e1_slider1_fv",
                    "e1_slider2_iv","e1_slider2_fv",
                    "e1_slider3_iv","e1_slider3_fv",
                    "e1_slider4_iv","e1_slider4_fv",
                    "e1_slider5_iv","e1_slider5_fv",
                    "e1_slider6_iv","e1_slider6_fv",
                    "e2_days_to_sooner_date","e2_days_to_later_date",
                    "e2_exchange_rate1","e2_exchange_rate2","e2_exchange_rate3","e2_exchange_rate4","e2_exchange_rate5","e2_exchange_rate6",
                    "e2_slider1_iv","e2_slider1_fv",
                    "e2_slider2_iv","e2_slider2_fv",
                    "e2_slider3_iv","e2_slider3_fv",
                    "e2_slider4_iv","e2_slider4_fv",
                    "e2_slider5_iv","e2_slider5_fv",
                    "e2_slider6_iv","e2_slider6_fv",
                    "e3_days_to_sooner_date","e3_days_to_later_date",
                    "e3_exchange_rate1","e3_exchange_rate2","e3_exchange_rate3","e3_exchange_rate4","e3_exchange_rate5","e3_exchange_rate6",
                    "e3_slider1_iv","e3_slider1_fv",
                    "e3_slider2_iv","e3_slider2_fv",
                    "e3_slider3_iv","e3_slider3_fv",
                    "e3_slider4_iv","e3_slider4_fv",
                    "e3_slider5_iv","e3_slider5_fv",
                    "e3_slider6_iv","e3_slider6_fv",
                    "e4_days_to_sooner_date","e4_days_to_later_date",
                    "e4_exchange_rate1","e4_exchange_rate2","e4_exchange_rate3","e4_exchange_rate4","e4_exchange_rate5","e4_exchange_rate6",
                    "e4_slider1_iv","e4_slider1_fv",
                    "e4_slider2_iv","e4_slider2_fv",
                    "e4_slider3_iv","e4_slider3_fv",
                    "e4_slider4_iv","e4_slider4_fv",
                    "e4_slider5_iv","e4_slider5_fv",
                    "e4_slider6_iv","e4_slider6_fv",
                    "e5_days_to_sooner_date","e5_days_to_later_date",
                    "e5_exchange_rate1","e5_exchange_rate2","e5_exchange_rate3","e5_exchange_rate4","e5_exchange_rate5","e5_exchange_rate6",
                    "e5_slider1_iv","e5_slider1_fv",
                    "e5_slider2_iv","e5_slider2_fv",
                    "e5_slider3_iv","e5_slider3_fv",
                    "e5_slider4_iv","e5_slider4_fv",
                    "e5_slider5_iv","e5_slider5_fv",
                    "e5_slider6_iv","e5_slider6_fv",
                    "e6_days_to_sooner_date","e6_days_to_later_date",
                    "e6_exchange_rate1","e6_exchange_rate2","e6_exchange_rate3","e6_exchange_rate4","e6_exchange_rate5","e6_exchange_rate6",
                    "e6_slider1_iv","e6_slider1_fv",
                    "e6_slider2_iv","e6_slider2_fv",
                    "e6_slider3_iv","e6_slider3_fv",
                    "e6_slider4_iv","e6_slider4_fv",
                    "e6_slider5_iv","e6_slider5_fv",
                    "e6_slider6_iv","e6_slider6_fv",
                    "f1_days_to_sooner_date","f1_days_to_later_date","f1_days_to_cashrewards_date",
                    "f1_exchange_rate1","f1_exchange_rate2","f1_exchange_rate3","f1_exchange_rate4","f1_exchange_rate5","f1_exchange_rate6",
                    "f1_slider1_iv","f1_slider1_fv",
                    "f1_slider2_iv","f1_slider2_fv",
                    "f1_slider3_iv","f1_slider3_fv",
                    "f1_slider4_iv","f1_slider4_fv",
                    "f1_slider5_iv","f1_slider5_fv",
                    "f1_slider6_iv","f1_slider6_fv",
            };
            writer.writeNext(headers);
            Log.v("FILEWRITE","Wrote headers");
        }
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = s.format(new Date());
        String[] data = {String.valueOf(datestr),
                String.valueOf(u.getId()),String.valueOf(u.getName()),String.valueOf(u.getLocation()),
                String.valueOf(games.get(0).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(0).getNumberOfDaystoLaterDate()),
                String.valueOf(games.get(0).getExchangeRate1()),String.valueOf(games.get(0).getExchangeRate2()),String.valueOf(games.get(0).getExchangeRate3()),String.valueOf(games.get(0).getExchangeRate4()),String.valueOf(games.get(0).getExchangeRate5()),String.valueOf(games.get(0).getExchangeRate6()),
                String.valueOf(gr1.getSlider1Iv()),String.valueOf(gr1.getSlider1Fv()),
                String.valueOf(gr1.getSlider2Iv()),String.valueOf(gr1.getSlider2Fv()),
                String.valueOf(gr1.getSlider3Iv()),String.valueOf(gr1.getSlider3Fv()),
                String.valueOf(gr1.getSlider4Iv()),String.valueOf(gr1.getSlider4Fv()),
                String.valueOf(gr1.getSlider5Iv()),String.valueOf(gr1.getSlider5Fv()),
                String.valueOf(gr1.getSlider6Iv()),String.valueOf(gr1.getSlider6Fv()),
                String.valueOf(games.get(1).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(1).getNumberOfDaystoLaterDate()),
                String.valueOf(games.get(1).getExchangeRate1()),String.valueOf(games.get(1).getExchangeRate2()),String.valueOf(games.get(1).getExchangeRate3()),String.valueOf(games.get(1).getExchangeRate4()),String.valueOf(games.get(1).getExchangeRate5()),String.valueOf(games.get(1).getExchangeRate6()),
                String.valueOf(gr2.getSlider1Iv()),String.valueOf(gr2.getSlider1Fv()),
                String.valueOf(gr2.getSlider2Iv()),String.valueOf(gr2.getSlider2Fv()),
                String.valueOf(gr2.getSlider3Iv()),String.valueOf(gr2.getSlider3Fv()),
                String.valueOf(gr2.getSlider4Iv()),String.valueOf(gr2.getSlider4Fv()),
                String.valueOf(gr2.getSlider5Iv()),String.valueOf(gr2.getSlider5Fv()),
                String.valueOf(gr2.getSlider6Iv()),String.valueOf(gr2.getSlider6Fv()),
                String.valueOf(games.get(2).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(2).getNumberOfDaystoLaterDate()),
                String.valueOf(games.get(2).getExchangeRate1()),String.valueOf(games.get(2).getExchangeRate2()),String.valueOf(games.get(2).getExchangeRate3()),String.valueOf(games.get(2).getExchangeRate4()),String.valueOf(games.get(2).getExchangeRate5()),String.valueOf(games.get(2).getExchangeRate6()),
                String.valueOf(gr3.getSlider1Iv()),String.valueOf(gr3.getSlider1Fv()),
                String.valueOf(gr3.getSlider2Iv()),String.valueOf(gr3.getSlider2Fv()),
                String.valueOf(gr3.getSlider3Iv()),String.valueOf(gr3.getSlider3Fv()),
                String.valueOf(gr3.getSlider4Iv()),String.valueOf(gr3.getSlider4Fv()),
                String.valueOf(gr3.getSlider5Iv()),String.valueOf(gr3.getSlider5Fv()),
                String.valueOf(gr3.getSlider6Iv()),String.valueOf(gr3.getSlider6Fv()),
                String.valueOf(games.get(3).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(3).getNumberOfDaystoLaterDate()),
                String.valueOf(games.get(3).getExchangeRate1()),String.valueOf(games.get(3).getExchangeRate2()),String.valueOf(games.get(3).getExchangeRate3()),String.valueOf(games.get(3).getExchangeRate4()),String.valueOf(games.get(3).getExchangeRate5()),String.valueOf(games.get(3).getExchangeRate6()),
                String.valueOf(gr4.getSlider1Iv()),String.valueOf(gr4.getSlider1Fv()),
                String.valueOf(gr4.getSlider2Iv()),String.valueOf(gr4.getSlider2Fv()),
                String.valueOf(gr4.getSlider3Iv()),String.valueOf(gr4.getSlider3Fv()),
                String.valueOf(gr4.getSlider4Iv()),String.valueOf(gr4.getSlider4Fv()),
                String.valueOf(gr4.getSlider5Iv()),String.valueOf(gr4.getSlider5Fv()),
                String.valueOf(gr4.getSlider6Iv()),String.valueOf(gr4.getSlider6Fv()),
                String.valueOf(games.get(4).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(4).getNumberOfDaystoLaterDate()),
                String.valueOf(games.get(4).getExchangeRate1()),String.valueOf(games.get(4).getExchangeRate2()),String.valueOf(games.get(4).getExchangeRate3()),String.valueOf(games.get(4).getExchangeRate4()),String.valueOf(games.get(4).getExchangeRate5()),String.valueOf(games.get(4).getExchangeRate6()),
                String.valueOf(gr5.getSlider1Iv()),String.valueOf(gr5.getSlider1Fv()),
                String.valueOf(gr5.getSlider2Iv()),String.valueOf(gr5.getSlider2Fv()),
                String.valueOf(gr5.getSlider3Iv()),String.valueOf(gr5.getSlider3Fv()),
                String.valueOf(gr5.getSlider4Iv()),String.valueOf(gr5.getSlider4Fv()),
                String.valueOf(gr5.getSlider5Iv()),String.valueOf(gr5.getSlider5Fv()),
                String.valueOf(gr5.getSlider6Iv()),String.valueOf(gr5.getSlider6Fv()),
                String.valueOf(games.get(5).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(5).getNumberOfDaystoLaterDate()),
                String.valueOf(games.get(5).getExchangeRate1()),String.valueOf(games.get(5).getExchangeRate2()),String.valueOf(games.get(5).getExchangeRate3()),String.valueOf(games.get(5).getExchangeRate4()),String.valueOf(games.get(5).getExchangeRate5()),String.valueOf(games.get(5).getExchangeRate6()),
                String.valueOf(gr6.getSlider1Iv()),String.valueOf(gr6.getSlider1Fv()),
                String.valueOf(gr6.getSlider2Iv()),String.valueOf(gr6.getSlider2Fv()),
                String.valueOf(gr6.getSlider3Iv()),String.valueOf(gr6.getSlider3Fv()),
                String.valueOf(gr6.getSlider4Iv()),String.valueOf(gr6.getSlider4Fv()),
                String.valueOf(gr6.getSlider5Iv()),String.valueOf(gr6.getSlider5Fv()),
                String.valueOf(gr6.getSlider6Iv()),String.valueOf(gr6.getSlider6Fv()),
                String.valueOf(games.get(6).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(6).getNumberOfDaystoLaterDate()),String.valueOf(games.get(6).getNumberOfDaystoCashRewardDate()),
                String.valueOf(games.get(6).getExchangeRate1()),String.valueOf(games.get(6).getExchangeRate2()),String.valueOf(games.get(6).getExchangeRate3()),String.valueOf(games.get(6).getExchangeRate4()),String.valueOf(games.get(6).getExchangeRate5()),String.valueOf(games.get(6).getExchangeRate6()),
                String.valueOf(gr1f.getSlider1Iv()),String.valueOf(gr1f.getSlider1Fv()),
                String.valueOf(gr1f.getSlider2Iv()),String.valueOf(gr1f.getSlider2Fv()),
                String.valueOf(gr1f.getSlider3Iv()),String.valueOf(gr1f.getSlider3Fv()),
                String.valueOf(gr1f.getSlider4Iv()),String.valueOf(gr1f.getSlider4Fv()),
                String.valueOf(gr1f.getSlider5Iv()),String.valueOf(gr1f.getSlider5Fv()),
                String.valueOf(gr1f.getSlider6Iv()),String.valueOf(gr1f.getSlider6Fv()),
        };
        writer.writeNext(data);
        writer.close();
    }
}
