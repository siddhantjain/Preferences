package com.tdw.preferences.surveys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SeekBar;

import com.tdw.preferences.R;

import java.util.Calendar;

public class SurveyZero extends AppCompatActivity {

    private CalendarView mPresentMonth;

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

        /*Calendar View Computation*/
        mPresentMonth = (CalendarView) findViewById(R.id.cvPresentMonth);

        Calendar presentMonth = Calendar.getInstance();
        Calendar soonerDate = Calendar.getInstance();
        Calendar laterDate = Calendar.getInstance();

        soonerDate.add(Calendar.WEEK_OF_YEAR,1);
        final long soonerDateTimeInMillis = soonerDate.getTimeInMillis();

        laterDate.add(Calendar.WEEK_OF_YEAR, 2);
        long laterDateTimeInMillis = laterDate.getTimeInMillis();

        mPresentMonth.setMinDate(System.currentTimeMillis());
        mPresentMonth.setDate(soonerDateTimeInMillis);
        mPresentMonth.setMaxDate(laterDateTimeInMillis);

        mPresentMonth.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                mPresentMonth.setDate(soonerDateTimeInMillis);
            }
        });;

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
