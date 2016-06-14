package com.tdw.preferences.surveys;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.tdw.preferences.R;
import com.tdw.preferences.TestUserUnderstanding;
import com.tdw.preferences.UserInformation;
import com.tdw.preferences.models.game;
import com.tdw.preferences.models.gameResult;
import com.tdw.preferences.models.user;
import com.tdw.preferences.utils.CalendarDecorator;
import com.tdw.preferences.utils.DataStore;
import com.tdw.preferences.utils.InstructionsDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SurveyOne extends AppCompatActivity {

    private CalendarPickerView mCalendar;
    private TextView mTVSlider1SoonerDate;
    private TextView mTVSlider1LaterDate;
    private TextView mTVSlider2SoonerDate;
    private TextView mTVSlider2LaterDate;
    private TextView mTVSlider3SoonerDate;
    private TextView mTVSlider3LaterDate;
    private TextView mTVSlider4SoonerDate;
    private TextView mTVSlider4LaterDate;
    private TextView mTVSlider5SoonerDate;
    private TextView mTVSlider5LaterDate;
    private TextView mTVSlider6SoonerDate;
    private TextView mTVSlider6LaterDate;

    private SeekBar mSlider1;
    private TextView mSlider1InitialValue;
    private TextView mSlider1FinalValue;
    private TextView mSlider1ExchangeRateValue;

    private SeekBar mSlider2;
    private TextView mSlider2InitialValue;
    private TextView mSlider2FinalValue;
    private TextView mSlider2ExchangeRateValue;

    private SeekBar mSlider3;
    private TextView mSlider3InitialValue;
    private TextView mSlider3FinalValue;
    private TextView mSlider3ExchangeRateValue;

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

    final int GAME_NUMBER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_one);

        /*showing instructions*/

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getApplicationContext().getString(R.string.instructions_title));
        alertDialog.setMessage(getApplicationContext().getString(R.string.instructions_body));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


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
        mCalendar = (CalendarPickerView) findViewById(R.id.cvGame1CurrentMonth);
        mTVSlider1SoonerDate = (TextView) findViewById(R.id.tvGame1Slider1SoonerDateLabel);
        mTVSlider1LaterDate = (TextView) findViewById(R.id.tvGame1Slider1LaterDateLabel);
        mTVSlider2SoonerDate = (TextView) findViewById(R.id.tvGame1Slider2SoonerDateLabel);
        mTVSlider2LaterDate = (TextView) findViewById(R.id.tvGame1Slider2LaterDateLabel);
        mTVSlider3SoonerDate = (TextView) findViewById(R.id.tvGame1Slider3SoonerDateLabel);
        mTVSlider3LaterDate = (TextView) findViewById(R.id.tvGame1Slider3LaterDateLabel);
        mTVSlider4SoonerDate = (TextView) findViewById(R.id.tvGame1Slider4SoonerDateLabel);
        mTVSlider4LaterDate = (TextView) findViewById(R.id.tvGame1Slider4LaterDateLabel);
        mTVSlider5SoonerDate = (TextView) findViewById(R.id.tvGame1Slider5SoonerDateLabel);
        mTVSlider5LaterDate = (TextView) findViewById(R.id.tvGame1Slider5LaterDateLabel);
        mTVSlider6SoonerDate = (TextView) findViewById(R.id.tvGame1Slider6SoonerDateLabel);
        mTVSlider6LaterDate = (TextView) findViewById(R.id.tvGame1Slider6LaterDateLabel);


        Calendar mDateHolder = Calendar.getInstance();
        Calendar mNextMonth = Calendar.getInstance();
        //Asssuming we won't need to show a calendars of years beyond a year from today's date on phone
        mNextMonth.add(Calendar.MONTH, 1);

        ArrayList<Date> dates = new ArrayList<Date>();
        mDateHolder.add(Calendar.DATE, numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());

        mDateHolder.add(Calendar.DATE, numDaysToLaterDate - numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());
        mCalendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new CalendarDecorator()));
        mCalendar.init(new Date(), mNextMonth.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDates(dates)
                .displayOnly();

        mTVSlider1SoonerDate.setText(Integer.toString(numDaysToSoonerDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider1LaterDate.setText(Integer.toString(numDaysToLaterDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider2SoonerDate.setText(Integer.toString(numDaysToSoonerDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider2LaterDate.setText(Integer.toString(numDaysToLaterDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider3SoonerDate.setText(Integer.toString(numDaysToSoonerDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider3LaterDate.setText(Integer.toString(numDaysToLaterDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider4SoonerDate.setText(Integer.toString(numDaysToSoonerDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider4LaterDate.setText(Integer.toString(numDaysToLaterDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider5SoonerDate.setText(Integer.toString(numDaysToSoonerDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider5LaterDate.setText(Integer.toString(numDaysToLaterDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider6SoonerDate.setText(Integer.toString(numDaysToSoonerDate) + " நாட்களிலான ரீசார்ஜ்");
        mTVSlider6LaterDate.setText(Integer.toString(numDaysToLaterDate) + " நாட்களிலான ரீசார்ஜ்");


        /*SeekBar Computation*/
        mSlider1 = (SeekBar) findViewById(R.id.sbGame1Slider1);
        mSlider1InitialValue = (TextView) findViewById(R.id.tvGame1Slider1Left);
        mSlider1FinalValue = (TextView) findViewById(R.id.tvGame1Slider1Right);
        mSlider1ExchangeRateValue = (TextView) findViewById(R.id.tvGame1Slider1Center);

        mSlider2 = (SeekBar) findViewById(R.id.sbGame1Slider2);
        mSlider2InitialValue = (TextView) findViewById(R.id.tvGame1Slider2Left);
        mSlider2FinalValue = (TextView) findViewById(R.id.tvGame1Slider2Right);
        mSlider2ExchangeRateValue = (TextView) findViewById(R.id.tvGame1Slider2Center);

        mSlider3 = (SeekBar) findViewById(R.id.sbGame1Slider3);
        mSlider3InitialValue = (TextView) findViewById(R.id.tvGame1Slider3Left);
        mSlider3FinalValue = (TextView) findViewById(R.id.tvGame1Slider3Right);
        mSlider3ExchangeRateValue = (TextView) findViewById(R.id.tvGame1Slider3Center);

        mSlider4 = (SeekBar) findViewById(R.id.sbGame1Slider4);
        mSlider4InitialValue = (TextView) findViewById(R.id.tvGame1Slider4Left);
        mSlider4FinalValue = (TextView) findViewById(R.id.tvGame1Slider4Right);

        mSlider5 = (SeekBar) findViewById(R.id.sbGame1Slider5);
        mSlider5InitialValue = (TextView) findViewById(R.id.tvGame1Slider5Left);
        mSlider5FinalValue = (TextView) findViewById(R.id.tvGame1Slider5Right);

        mSlider6 = (SeekBar) findViewById(R.id.sbGame1Slider6);
        mSlider6InitialValue = (TextView) findViewById(R.id.tvGame1Slider6Left);
        mSlider6FinalValue = (TextView) findViewById(R.id.tvGame1Slider6Right);



        int initialSlider1Progress = mSlider1.getProgress();
        double proportion = (double)initialSlider1Progress/(double)100;
        int IV1OnStartup = (int) ((double)fixedamount + ((double)variableamount*((double)1-proportion)));
        int FV1OnStartup = (int)((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateOne));
        mSlider1InitialValue.setText(Integer.toString(IV1OnStartup));
        mSlider1FinalValue.setText(Integer.toString(FV1OnStartup));
        mSlider1ExchangeRateValue.setText("பரிமாற்ற விகிதம்: 1:" + Float.toString(exchangeRateOne));

        int initialSlider2Progress = mSlider2.getProgress();
        proportion = (double)initialSlider2Progress/(double)100;
        int IV2OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV2OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateTwo));
        mSlider2InitialValue.setText(Integer.toString(IV2OnStartup));
        mSlider2FinalValue.setText(Integer.toString(FV2OnStartup));
        mSlider2ExchangeRateValue.setText("பரிமாற்ற விகிதம்: 1:" + Float.toString(exchangeRateTwo));

        int initialSlider3Progress = mSlider3.getProgress();
        proportion = (double)initialSlider3Progress/(double)100;
        int IV3OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
        int FV3OnStartup = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateThree));
        mSlider3InitialValue.setText(Integer.toString(IV3OnStartup));
        mSlider3FinalValue.setText(Integer.toString(FV3OnStartup));
        mSlider3ExchangeRateValue.setText("பரிமாற்ற விகிதம்: 1:" + Float.toString(exchangeRateThree));

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

    public void startNextGame (View view) {
        //gameResult gr1 = new gameResult(mSlider1InitialValue.getText().toString(),mSlider1FinalValue.getText().toString(),mSlider2InitialValue.getText().toString(),mSlider2FinalValue.getText().toString(),mSlider3InitialValue.getText().toString(),mSlider3FinalValue.getText().toString(),mSlider4InitialValue.getText().toString(),mSlider4FinalValue.getText().toString(),mSlider5InitialValue.getText().toString(),mSlider5FinalValue.getText().toString(),mSlider6InitialValue.getText().toString(),mSlider6FinalValue.getText().toString());
        gameResult gr1 = new gameResult(mSlider1InitialValue.getText().toString(),mSlider1FinalValue.getText().toString(),mSlider2InitialValue.getText().toString(),mSlider2FinalValue.getText().toString(),mSlider3InitialValue.getText().toString(),mSlider3FinalValue.getText().toString(),"","","","","","");
        DataStore.setSurveyOneResult(gr1);
        //Just for Beta version
//        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(getApplicationContext().getString(R.string.instructions_title));
//        alertDialog.setMessage(getApplicationContext().getString(R.string.instructions_f_body));
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        //For the Beta version
//                        Intent intent = new Intent(SurveyOne.this,SurveyOneF.class);
//                        startActivity(intent);
//                    }
//                });
//        alertDialog.show();

        //For the normal app
        Intent intent = new Intent(SurveyOne.this,SurveyTwo.class);
        startActivity(intent);

    }

    public void showInstructionsDialog(View view){
        InstructionsDialog.showDialog(this,"E");
    }
}
