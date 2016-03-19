package com.tdw.preferences.utils;

import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVWriter;
import com.tdw.preferences.models.TestUserResult;
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
    public void writeToFile() throws IOException {
        TestUserResult tur1 = DataStore.getTestUserResultOne();
        TestUserResult tur2 = DataStore.getTestUserResultTwo();
        gameResult gr1 = DataStore.getSurveyOneResult();
        gameResult gr2 = DataStore.getSurveyTwoResult();
        gameResult gr3 = DataStore.getSurveyThreeResult();
        gameResult gr4 = DataStore.getSurveyFourResult();
        gameResult gr5 = DataStore.getSurveyFiveResult();
        gameResult gr6 = DataStore.getSurveySixResult();
        gameResult gr1f = DataStore.getSurveyOneFResult();
        gameResult gr2f = DataStore.getSurveyTwoFResult();
        gameResult gr3f = DataStore.getSurveyThreeFResult();
        gameResult gr4f = DataStore.getSurveyFourFResult();
        gameResult gr5f = DataStore.getSurveyFiveFResult();
        gameResult gr6f = DataStore.getSurveySixFResult();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat fnft = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = s.format(new Date());
        String fNameDateStr = fnft.format(new Date());

        List<user> users = DataStore.getUserList();
        user u = users.get(users.size() - 1);

        List<game> games = DataStore.getGameList();

        //String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = String.valueOf(DataStore.getAndroidId())+"_"+fNameDateStr+"_SurveyResults.csv";
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
                    "e0r1_days_to_sooner_date","e0r1_days_to_later_date",
                    "e0r1_exchange_rate1","e0r1_exchange_rate2",
                    "e0r1_slider1_iv","e0r1_slider1_fv",
                    "e0r1_slider2_iv","e0r1_slider2_fv",
                    "e0r2_days_to_sooner_date","e0r2_days_to_later_date",
                    "e0r2_exchange_rate1","e0r2_exchange_rate2",
                    "e0r2_slider1_iv","e0r2_slider1_fv",
                    "e0r2_slider2_iv","e0r2_slider2_fv",
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
                    "f2_days_to_sooner_date","f2_days_to_later_date","f2_days_to_cashrewards_date",
                    "f2_exchange_rate1","f2_exchange_rate2","f2_exchange_rate3","f2_exchange_rate4","f2_exchange_rate5","f2_exchange_rate6",
                    "f2_slider1_iv","f2_slider1_fv",
                    "f2_slider2_iv","f2_slider2_fv",
                    "f2_slider3_iv","f2_slider3_fv",
                    "f2_slider4_iv","f2_slider4_fv",
                    "f2_slider5_iv","f2_slider5_fv",
                    "f2_slider6_iv","f2_slider6_fv",
                    "f3_days_to_sooner_date","f3_days_to_later_date","f3_days_to_cashrewards_date",
                    "f3_exchange_rate1","f3_exchange_rate2","f3_exchange_rate3","f3_exchange_rate4","f3_exchange_rate5","f3_exchange_rate6",
                    "f3_slider1_iv","f3_slider1_fv",
                    "f3_slider2_iv","f3_slider2_fv",
                    "f3_slider3_iv","f3_slider3_fv",
                    "f3_slider4_iv","f3_slider4_fv",
                    "f3_slider5_iv","f3_slider5_fv",
                    "f3_slider6_iv","f3_slider6_fv",
                    "f4_days_to_sooner_date","f4_days_to_later_date","f4_days_to_cashrewards_date",
                    "f4_exchange_rate1","f4_exchange_rate2","f4_exchange_rate3","f4_exchange_rate4","f4_exchange_rate5","f4_exchange_rate6",
                    "f4_slider1_iv","f4_slider1_fv",
                    "f4_slider2_iv","f4_slider2_fv",
                    "f4_slider3_iv","f4_slider3_fv",
                    "f4_slider4_iv","f4_slider4_fv",
                    "f4_slider5_iv","f4_slider5_fv",
                    "f4_slider6_iv","f4_slider6_fv",
                    "f5_days_to_sooner_date","f5_days_to_later_date","f5_days_to_cashrewards_date",
                    "f5_exchange_rate1","f5_exchange_rate2","f5_exchange_rate3","f5_exchange_rate4","f5_exchange_rate5","f5_exchange_rate6",
                    "f5_slider1_iv","f5_slider1_fv",
                    "f5_slider2_iv","f5_slider2_fv",
                    "f5_slider3_iv","f5_slider3_fv",
                    "f5_slider4_iv","f5_slider4_fv",
                    "f5_slider5_iv","f5_slider5_fv",
                    "f5_slider6_iv","f5_slider6_fv",
                    "f6_days_to_sooner_date","f6_days_to_later_date","f6_days_to_cashrewards_date",
                    "f6_exchange_rate1","f6_exchange_rate2","f6_exchange_rate3","f6_exchange_rate4","f6_exchange_rate5","f6_exchange_rate6",
                    "f6_slider1_iv","f6_slider1_fv",
                    "f6_slider2_iv","f6_slider2_fv",
                    "f6_slider3_iv","f6_slider3_fv",
                    "f6_slider4_iv","f6_slider4_fv",
                    "f6_slider5_iv","f6_slider5_fv",
                    "f6_slider6_iv","f6_slider6_fv"
            };
            writer.writeNext(headers);
            Log.v("FILEWRITE","Wrote headers");
        }
        String[] data = {String.valueOf(datestr),
                String.valueOf(u.getId()),String.valueOf(u.getName()),String.valueOf(u.getLocation()),
                "7","28",
                "1.0","1.25",
                String.valueOf(tur1.getSlider1Iv()),String.valueOf(tur1.getSlider1Fv()),
                String.valueOf(tur1.getSlider2Iv()),String.valueOf(tur1.getSlider2Fv()),
                "7","28",
                "1.0","1.25",
                String.valueOf(tur2.getSlider1Iv()),String.valueOf(tur2.getSlider1Fv()),
                String.valueOf(tur2.getSlider2Iv()),String.valueOf(tur2.getSlider2Fv()),
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
                String.valueOf(games.get(7).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(7).getNumberOfDaystoLaterDate()),String.valueOf(games.get(7).getNumberOfDaystoCashRewardDate()),
                String.valueOf(games.get(7).getExchangeRate1()),String.valueOf(games.get(7).getExchangeRate2()),String.valueOf(games.get(7).getExchangeRate3()),String.valueOf(games.get(7).getExchangeRate4()),String.valueOf(games.get(7).getExchangeRate5()),String.valueOf(games.get(7).getExchangeRate6()),
                String.valueOf(gr2f.getSlider1Iv()),String.valueOf(gr2f.getSlider1Fv()),
                String.valueOf(gr2f.getSlider2Iv()),String.valueOf(gr2f.getSlider2Fv()),
                String.valueOf(gr2f.getSlider3Iv()),String.valueOf(gr2f.getSlider3Fv()),
                String.valueOf(gr2f.getSlider4Iv()),String.valueOf(gr2f.getSlider4Fv()),
                String.valueOf(gr2f.getSlider5Iv()),String.valueOf(gr2f.getSlider5Fv()),
                String.valueOf(gr2f.getSlider6Iv()),String.valueOf(gr2f.getSlider6Fv()),
                String.valueOf(games.get(8).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(8).getNumberOfDaystoLaterDate()),String.valueOf(games.get(8).getNumberOfDaystoCashRewardDate()),
                String.valueOf(games.get(8).getExchangeRate1()),String.valueOf(games.get(8).getExchangeRate2()),String.valueOf(games.get(8).getExchangeRate3()),String.valueOf(games.get(8).getExchangeRate4()),String.valueOf(games.get(8).getExchangeRate5()),String.valueOf(games.get(8).getExchangeRate6()),
                String.valueOf(gr3f.getSlider1Iv()),String.valueOf(gr3f.getSlider1Fv()),
                String.valueOf(gr3f.getSlider2Iv()),String.valueOf(gr3f.getSlider2Fv()),
                String.valueOf(gr3f.getSlider3Iv()),String.valueOf(gr3f.getSlider3Fv()),
                String.valueOf(gr3f.getSlider4Iv()),String.valueOf(gr3f.getSlider4Fv()),
                String.valueOf(gr3f.getSlider5Iv()),String.valueOf(gr3f.getSlider5Fv()),
                String.valueOf(gr3f.getSlider6Iv()),String.valueOf(gr3f.getSlider6Fv()),
                String.valueOf(games.get(9).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(9).getNumberOfDaystoLaterDate()),String.valueOf(games.get(9).getNumberOfDaystoCashRewardDate()),
                String.valueOf(games.get(9).getExchangeRate1()),String.valueOf(games.get(9).getExchangeRate2()),String.valueOf(games.get(9).getExchangeRate3()),String.valueOf(games.get(9).getExchangeRate4()),String.valueOf(games.get(9).getExchangeRate5()),String.valueOf(games.get(9).getExchangeRate6()),
                String.valueOf(gr4f.getSlider1Iv()),String.valueOf(gr4f.getSlider1Fv()),
                String.valueOf(gr4f.getSlider2Iv()),String.valueOf(gr4f.getSlider2Fv()),
                String.valueOf(gr4f.getSlider3Iv()),String.valueOf(gr4f.getSlider3Fv()),
                String.valueOf(gr4f.getSlider4Iv()),String.valueOf(gr4f.getSlider4Fv()),
                String.valueOf(gr4f.getSlider5Iv()),String.valueOf(gr4f.getSlider5Fv()),
                String.valueOf(gr4f.getSlider6Iv()),String.valueOf(gr4f.getSlider6Fv()),
                String.valueOf(games.get(10).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(10).getNumberOfDaystoLaterDate()),String.valueOf(games.get(10).getNumberOfDaystoCashRewardDate()),
                String.valueOf(games.get(10).getExchangeRate1()),String.valueOf(games.get(10).getExchangeRate2()),String.valueOf(games.get(10).getExchangeRate3()),String.valueOf(games.get(10).getExchangeRate4()),String.valueOf(games.get(10).getExchangeRate5()),String.valueOf(games.get(10).getExchangeRate6()),
                String.valueOf(gr5f.getSlider1Iv()),String.valueOf(gr5f.getSlider1Fv()),
                String.valueOf(gr5f.getSlider2Iv()),String.valueOf(gr5f.getSlider2Fv()),
                String.valueOf(gr5f.getSlider3Iv()),String.valueOf(gr5f.getSlider3Fv()),
                String.valueOf(gr5f.getSlider4Iv()),String.valueOf(gr5f.getSlider4Fv()),
                String.valueOf(gr5f.getSlider5Iv()),String.valueOf(gr5f.getSlider5Fv()),
                String.valueOf(gr5f.getSlider6Iv()),String.valueOf(gr5f.getSlider6Fv()),
                String.valueOf(games.get(11).getNumberOfDaystoSoonerDate()),String.valueOf(games.get(11).getNumberOfDaystoLaterDate()),String.valueOf(games.get(11).getNumberOfDaystoCashRewardDate()),
                String.valueOf(games.get(11).getExchangeRate1()),String.valueOf(games.get(11).getExchangeRate2()),String.valueOf(games.get(11).getExchangeRate3()),String.valueOf(games.get(11).getExchangeRate4()),String.valueOf(games.get(11).getExchangeRate5()),String.valueOf(games.get(11).getExchangeRate6()),
                String.valueOf(gr6f.getSlider1Iv()),String.valueOf(gr6f.getSlider1Fv()),
                String.valueOf(gr6f.getSlider2Iv()),String.valueOf(gr6f.getSlider2Fv()),
                String.valueOf(gr6f.getSlider3Iv()),String.valueOf(gr6f.getSlider3Fv()),
                String.valueOf(gr6f.getSlider4Iv()),String.valueOf(gr6f.getSlider4Fv()),
                String.valueOf(gr6f.getSlider5Iv()),String.valueOf(gr6f.getSlider5Fv()),
                String.valueOf(gr6f.getSlider6Iv()),String.valueOf(gr6f.getSlider6Fv()),
        };
        writer.writeNext(data);
        writer.close();
    }
}
