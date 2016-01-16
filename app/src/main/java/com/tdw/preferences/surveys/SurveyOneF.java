package com.tdw.preferences.surveys;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.opencsv.CSVWriter;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.tdw.preferences.R;
import com.tdw.preferences.models.game;
import com.tdw.preferences.models.gameResult;
import com.tdw.preferences.models.user;
import com.tdw.preferences.utils.DataStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SurveyOneF extends AppCompatActivity {

    private CalendarPickerView mCalendar;

    private SeekBar mSlider1;
    private TextView mSlider1InitialValue;
    private TextView mSlider1FinalValue;

    private SeekBar mSlider2;
    private TextView mSlider2InitialValue;
    private TextView mSlider2FinalValue;

    private SeekBar mSlider3;
    private TextView mSlider3InitialValue;
    private TextView mSlider3FinalValue;

    private SeekBar mSlider4;
    private TextView mSlider4InitialValue;
    private TextView mSlider4FinalValue;

    private SeekBar mSlider5;
    private TextView mSlider5InitialValue;
    private TextView mSlider5FinalValue;

    private SeekBar mSlider6;
    private TextView mSlider6InitialValue;
    private TextView mSlider6FinalValue;


    /*HardCoded Variables - TO BE REMOVED*/
    int fixedamount = 2000;
    int variableamount = 10000;

    final int GAME_NUMBER = 1;
    final int GAME_TYPE = 1; //game type for section F

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_one_f);

        /*Getting values from data store*/
        List<game> gameList = DataStore.getGameList();
        game currGame = gameList.get((GAME_TYPE*6) + GAME_NUMBER-1);
        int numDaysToSoonerDate = currGame.getNumberOfDaystoSoonerDate();
        int numDaysToLaterDate = currGame.getNumberOfDaystoLaterDate();
        int numDaysToCashRewardDate = currGame.getNumberOfDaystoCashRewardDate();
        System.out.println(numDaysToCashRewardDate);
        final float exchangeRateOne = currGame.getExchangeRate1();
        final float exchangeRateTwo = currGame.getExchangeRate2();
        final float exchangeRateThree = currGame.getExchangeRate3();
        final float exchangeRateFour = currGame.getExchangeRate4();
        final float exchangeRateFive = currGame.getExchangeRate5();
        final float exchangeRateSix = currGame.getExchangeRate6();


        /*Calendar View Computation*/
        mCalendar = (CalendarPickerView) findViewById(R.id.cvGame1FCurrentMonth);

        Calendar mDateHolder = Calendar.getInstance();
        Calendar mNextMonth = Calendar.getInstance();
        //Asssuming we won't need to show a calendars of years beyond a year from today's date on phone
        mNextMonth.add(Calendar.MONTH,1);

        ArrayList<Date> dates = new ArrayList<Date>();
        mDateHolder.add(Calendar.DATE, numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());
        //temp hack. Look in to why is the delta behaving weirdly.
        mDateHolder.add(Calendar.DATE, numDaysToLaterDate-numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());
        mDateHolder.add(Calendar.DATE, numDaysToCashRewardDate-numDaysToLaterDate);
        dates.add(mDateHolder.getTime());

        mCalendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        mCalendar.init(new Date(), mNextMonth.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDates(dates)
                .displayOnly();




        /*SeekBar Computation*/
        mSlider1 = (SeekBar) findViewById(R.id.sbGame1FSlider1);
        mSlider1InitialValue = (TextView) findViewById(R.id.tvGame1FSlider1Left);
        mSlider1FinalValue = (TextView) findViewById(R.id.tvGame1FSlider1Right);

        mSlider2 = (SeekBar) findViewById(R.id.sbGame1FSlider2);
        mSlider2InitialValue = (TextView) findViewById(R.id.tvGame1FSlider2Left);
        mSlider2FinalValue = (TextView) findViewById(R.id.tvGame1FSlider2Right);

        mSlider3 = (SeekBar) findViewById(R.id.sbGame1FSlider3);
        mSlider3InitialValue = (TextView) findViewById(R.id.tvGame1FSlider3Left);
        mSlider3FinalValue = (TextView) findViewById(R.id.tvGame1FSlider3Right);

        mSlider4 = (SeekBar) findViewById(R.id.sbGame1FSlider4);
        mSlider4InitialValue = (TextView) findViewById(R.id.tvGame1FSlider4Left);
        mSlider4FinalValue = (TextView) findViewById(R.id.tvGame1FSlider4Right);

        mSlider5 = (SeekBar) findViewById(R.id.sbGame1FSlider5);
        mSlider5InitialValue = (TextView) findViewById(R.id.tvGame1FSlider5Left);
        mSlider5FinalValue = (TextView) findViewById(R.id.tvGame1FSlider5Right);

        mSlider6 = (SeekBar) findViewById(R.id.sbGame1FSlider6);
        mSlider6InitialValue = (TextView) findViewById(R.id.tvGame1FSlider6Left);
        mSlider6FinalValue = (TextView) findViewById(R.id.tvGame1FSlider6Right);



        int initialSlider1Progress = mSlider1.getProgress();
        double proportion = (double)initialSlider1Progress/(double)100;
        int IV1OnStartup = (int) ((double)fixedamount + ((double)variableamount*((double)1-proportion)));
        int FV1OnStartup = (int)((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateOne));
        mSlider1InitialValue.setText(Integer.toString(IV1OnStartup));
        mSlider1FinalValue.setText(Integer.toString(FV1OnStartup));

        int initialSlider2Progress = mSlider2.getProgress();
        proportion = (double)initialSlider2Progress/(double)100;
        int IV2OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV2OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateTwo));
        mSlider2InitialValue.setText(Integer.toString(IV2OnStartup));
        mSlider2FinalValue.setText(Integer.toString(FV2OnStartup));

        int initialSlider3Progress = mSlider3.getProgress();
        proportion = (double)initialSlider3Progress/(double)100;
        int IV3OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV3OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateThree));
        mSlider3InitialValue.setText(Integer.toString(IV3OnStartup));
        mSlider3FinalValue.setText(Integer.toString(FV3OnStartup));

        int initialSlider4Progress = mSlider4.getProgress();
        proportion = (double)initialSlider4Progress/(double)100;
        int IV4OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV4OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateFour));
        mSlider4InitialValue.setText(Integer.toString(IV4OnStartup));
        mSlider4FinalValue.setText(Integer.toString(FV4OnStartup));

        int initialSlider5Progress = mSlider5.getProgress();
        proportion = (double)initialSlider5Progress/(double)100;
        int IV5OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV5OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateFive));
        mSlider5InitialValue.setText(Integer.toString(IV5OnStartup));
        mSlider5FinalValue.setText(Integer.toString(FV5OnStartup));

        int initialSlider6Progress = mSlider6.getProgress();
        proportion = (double)initialSlider6Progress/(double)100;
        int IV6OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV6OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateSix));
        mSlider6InitialValue.setText(Integer.toString(IV6OnStartup));
        mSlider6FinalValue.setText(Integer.toString(FV6OnStartup));


        //Setting Slider Listeners
        mSlider1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                int IV = (int) ((double)fixedamount + ((double)variableamount*(1-proportion)));
                int FV = (int) ((double)fixedamount + ((double)variableamount*proportion*(double)exchangeRateOne));
                mSlider1InitialValue.setText(Integer.toString(IV));
                mSlider1FinalValue.setText(Integer.toString(FV));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSlider2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                int IV = (int) ((double)fixedamount + ((double)variableamount*(1-proportion)));
                int FV = (int) ((double)fixedamount + ((double)variableamount*proportion*(double)exchangeRateTwo));
                mSlider2InitialValue.setText(Integer.toString(IV));
                mSlider2FinalValue.setText(Integer.toString(FV));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSlider3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                int IV = (int) ((double)fixedamount + ((double)variableamount*(1-proportion)));
                int FV = (int) ((double)fixedamount + ((double)variableamount*proportion*(double)exchangeRateThree));
                mSlider3InitialValue.setText(Integer.toString(IV));
                mSlider3FinalValue.setText(Integer.toString(FV));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSlider4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                int IV = (int) ((double)fixedamount + ((double)variableamount*(1-proportion)));
                int FV = (int) ((double)fixedamount + ((double)variableamount*proportion*(double)exchangeRateFour));
                mSlider4InitialValue.setText(Integer.toString(IV));
                mSlider4FinalValue.setText(Integer.toString(FV));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSlider5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                int IV = (int) ((double)fixedamount + ((double)variableamount*(1-proportion)));
                int FV = (int) ((double)fixedamount + ((double)variableamount*proportion*(double)exchangeRateFive));
                mSlider5InitialValue.setText(Integer.toString(IV));
                mSlider5FinalValue.setText(Integer.toString(FV));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSlider6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                int IV = (int) ((double)fixedamount + ((double)variableamount*(1-proportion)));
                int FV = (int) ((double)fixedamount + ((double)variableamount*proportion*(double)exchangeRateSix));
                mSlider6InitialValue.setText(Integer.toString(IV));
                mSlider6FinalValue.setText(Integer.toString(FV));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void startNextGame (View view) throws IOException {
        WriteResultsToFile();
    }
    private void WriteResultsToFile() throws IOException {
        gameResult gr1 = DataStore.getSurveyOneResult();
        gameResult gr2 = DataStore.getSurveyTwoResult();
        gameResult gr3 = DataStore.getSurveyThreeResult();
        gameResult gr4 = DataStore.getSurveyFourResult();
        gameResult gr5 = DataStore.getSurveyFiveResult();
        gameResult gr6 = DataStore.getSurveyFiveResult();

        List<user> users = DataStore.getUserList();
        user u = users.get(users.size() - 1);

        List<game> games = DataStore.getGameList();

        //String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "SurveyResults.csv";
        //String filePath = baseDir + File.separator + fileName;
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);
        Log.v("FILE",f.getAbsolutePath());
        CSVWriter writer;
        // File exist
        if(f.exists() && !f.isDirectory()){
            FileWriter mFileWriter = new FileWriter(f,true);
            writer = new CSVWriter(mFileWriter);
            String [] headers = {"timestamp",
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
            };
            writer.writeNext(headers);
        }
        else {
            writer = new CSVWriter(new FileWriter(f));
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
        };
        writer.writeNext(data);
        writer.close();
    }
}
