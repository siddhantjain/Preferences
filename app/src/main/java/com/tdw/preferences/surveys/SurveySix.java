package com.tdw.preferences.surveys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.opencsv.CSVWriter;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.tdw.preferences.R;
import com.tdw.preferences.WelcomeScreen;
import com.tdw.preferences.models.game;
import com.tdw.preferences.models.gameResult;
import com.tdw.preferences.models.user;
import com.tdw.preferences.utils.CalendarDecorator;
import com.tdw.preferences.utils.DataStore;
import com.tdw.preferences.surveys.SurveyOneF;
import com.tdw.preferences.utils.InstructionsDialog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SurveySix extends AppCompatActivity {

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
    int fixedamount = 20;
    int variableamount = 100;

    final int GAME_NUMBER = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_six);

        /*Getting values from data store*/
        List<game> gameList = DataStore.getGameList();
        game currGame = gameList.get(GAME_NUMBER-1);
        int numDaysToSoonerDate = currGame.getNumberOfDaystoSoonerDate();
        int numDaysToLaterDate = currGame.getNumberOfDaystoLaterDate();
        final float exchangeRateOne = currGame.getExchangeRate1();
        final float exchangeRateTwo = currGame.getExchangeRate2();
        final float exchangeRateThree = currGame.getExchangeRate3();
        final float exchangeRateFour = currGame.getExchangeRate4();
        final float exchangeRateFive = currGame.getExchangeRate5();
        final float exchangeRateSix = currGame.getExchangeRate6();


        /*Calendar View Computation*/
        mCalendar = (CalendarPickerView) findViewById(R.id.cvGame6CurrentMonth);

        Calendar mDateHolder = Calendar.getInstance();
        Calendar mNextMonth = Calendar.getInstance();
        //Asssuming we won't need to show a calendars of years beyond a year from today's date on phone
        mNextMonth.add(Calendar.MONTH,1);

        ArrayList<Date> dates = new ArrayList<Date>();
        mDateHolder.add(Calendar.DATE, numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());
        mDateHolder.add(Calendar.DATE, numDaysToLaterDate-numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());
        mCalendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new CalendarDecorator()));
        mCalendar.init(new Date(), mNextMonth.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDates(dates)
                .displayOnly();


        /*SeekBar Computation*/
        mSlider1 = (SeekBar) findViewById(R.id.sbGame6Slider1);
        mSlider1InitialValue = (TextView) findViewById(R.id.tvGame6Slider1Left);
        mSlider1FinalValue = (TextView) findViewById(R.id.tvGame6Slider1Right);

        mSlider2 = (SeekBar) findViewById(R.id.sbGame6Slider2);
        mSlider2InitialValue = (TextView) findViewById(R.id.tvGame6Slider2Left);
        mSlider2FinalValue = (TextView) findViewById(R.id.tvGame6Slider2Right);

        mSlider3 = (SeekBar) findViewById(R.id.sbGame6Slider3);
        mSlider3InitialValue = (TextView) findViewById(R.id.tvGame6Slider3Left);
        mSlider3FinalValue = (TextView) findViewById(R.id.tvGame6Slider3Right);

        mSlider4 = (SeekBar) findViewById(R.id.sbGame6Slider4);
        mSlider4InitialValue = (TextView) findViewById(R.id.tvGame6Slider4Left);
        mSlider4FinalValue = (TextView) findViewById(R.id.tvGame6Slider4Right);

        mSlider5 = (SeekBar) findViewById(R.id.sbGame6Slider5);
        mSlider5InitialValue = (TextView) findViewById(R.id.tvGame6Slider5Left);
        mSlider5FinalValue = (TextView) findViewById(R.id.tvGame6Slider5Right);

        mSlider6 = (SeekBar) findViewById(R.id.sbGame6Slider6);
        mSlider6InitialValue = (TextView) findViewById(R.id.tvGame6Slider6Left);
        mSlider6FinalValue = (TextView) findViewById(R.id.tvGame6Slider6Right);



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
        gameResult gr = new gameResult(mSlider1InitialValue.getText().toString(),mSlider1FinalValue.getText().toString(),mSlider2InitialValue.getText().toString(),mSlider2FinalValue.getText().toString(),mSlider3InitialValue.getText().toString(),mSlider3FinalValue.getText().toString(),mSlider4InitialValue.getText().toString(),mSlider4FinalValue.getText().toString(),mSlider5InitialValue.getText().toString(),mSlider5FinalValue.getText().toString(),mSlider6InitialValue.getText().toString(),mSlider6FinalValue.getText().toString());
        DataStore.setSurveySixResult(gr);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        //alertDialog.setTitle(getApplicationContext().getString(R.string.instructions_title_f));
        alertDialog.setTitle("BLAH");
        alertDialog.setMessage(getApplicationContext().getString(R.string.instructions_f_body));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        //For the Beta version
                        Intent intent = new Intent(SurveySix.this,WelcomeScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("StartF",true);
                        startActivity(intent);
                    }
                });
        alertDialog.show();
    }
    public void showInstructionsDialog(View view){
        InstructionsDialog.showDialog(this, "E");
    }
}
