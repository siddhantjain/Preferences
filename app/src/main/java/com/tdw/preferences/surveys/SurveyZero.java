package com.tdw.preferences.surveys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SeekBar;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.tdw.preferences.R;
import com.tdw.preferences.models.game;
import com.tdw.preferences.utils.DataStore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SurveyZero extends AppCompatActivity {

    private CalendarPickerView mCalendar;

    private SeekBar mSlider1;
    private EditText mSlider1InitialValue;
    private EditText mSlider1FinalValue;

    private SeekBar mSlider2;
    private EditText mSlider2InitialValue;
    private EditText mSlider2FinalValue;

    /*HardCoded Variables - TO BE REMOVED*/
    int fixedamount = 20;
    int variableamount = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_zero);

        /*Getting values from data store*/
        List<game> gameList = DataStore.getGameList();
        game currGame = gameList.get(0);
        int numDaysToSoonerDate = currGame.getNumberOfDaystoSoonerDate();
        int numDaysToLaterDate = currGame.getNumberOfDaystoLaterDate();
        float interestRateOne = currGame.getExchangeRate1();
        float interestRateTwo = currGame.getExchangeRate2();


        /*Calendar View Computation*/
        mCalendar = (CalendarPickerView) findViewById(R.id.cvCurrentMonth);

        Calendar mDateHolder = Calendar.getInstance();
        Calendar mNextMonth = Calendar.getInstance();
        //Asssuming we won't need to show a calendars of years beyond a year from today's date on phone
        mNextMonth.add(Calendar.MONTH,1);

        ArrayList<Date> dates = new ArrayList<Date>();
        mDateHolder.add(Calendar.DATE, numDaysToSoonerDate);
        dates.add(mDateHolder.getTime());
        mDateHolder.add(Calendar.DATE, numDaysToLaterDate);
        dates.add(mDateHolder.getTime());
        mCalendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        mCalendar.init(new Date(), mNextMonth.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDates(dates)
                .displayOnly();


        /*
        mPresentMonth.setMinDate(System.currentTimeMillis());
        mPresentMonth.setDate(soonerDateTimeInMillis);
        mPresentMonth.setMaxDate(laterDateTimeInMillis);

        mPresentMonth.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                mPresentMonth.setDate(soonerDateTimeInMillis);
            }
        });;
        */
        /*SeekBar Computation*/
        mSlider1 = (SeekBar) findViewById(R.id.sbSlider1);
        mSlider1InitialValue = (EditText) findViewById(R.id.etSlider1Initial);
        mSlider1FinalValue = (EditText) findViewById(R.id.etSlider1Final);

        mSlider2 = (SeekBar) findViewById(R.id.sbSlider2);
        mSlider2InitialValue = (EditText) findViewById(R.id.etSlider2Initial);
        mSlider2FinalValue = (EditText) findViewById(R.id.etSlider2Final);

        int initialSlider1Progress = mSlider1.getProgress();
        int IV1OnStartup = fixedamount + (variableamount*initialSlider1Progress/100);
        int FV1OnStartup = fixedamount + (variableamount*(100-initialSlider1Progress)/100);
        mSlider1InitialValue.setText(Integer.toString(IV1OnStartup));
        mSlider1FinalValue.setText(Integer.toString(FV1OnStartup));

        int initialSlider2Progress = mSlider2.getProgress();
        int IV2OnStartup = fixedamount + (variableamount*initialSlider2Progress/100);
        int FV2OnStartup = fixedamount + (variableamount*(100-initialSlider2Progress)/100);
        mSlider2InitialValue.setText(Integer.toString(IV2OnStartup));
        mSlider2FinalValue.setText(Integer.toString(FV2OnStartup));

        mSlider1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                System.out.print(progressValue);
                int IV = fixedamount + (variableamount*progressValue/100);
                int FV = fixedamount + (variableamount*(100-progressValue)/100);
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
                System.out.print(progressValue);
                int IV = fixedamount + (variableamount*progressValue/100);
                int FV = fixedamount + (variableamount*(100-progressValue)/100);
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


    }
}
