package com.tdw.preferences.surveys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

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
    private TextView mSlider1InitialValue;
    private TextView mSlider1FinalValue;

    private SeekBar mSlider2;
    private TextView mSlider2InitialValue;
    private TextView mSlider2FinalValue;

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
        final float exchangeRateOne = currGame.getExchangeRate1();
        final float exchangeRateTwo = currGame.getExchangeRate2();


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


        /*SeekBar Computation*/
        mSlider1 = (SeekBar) findViewById(R.id.sbSlider1);
        mSlider1InitialValue = (TextView) findViewById(R.id.tvSlider1Left);
        mSlider1FinalValue = (TextView) findViewById(R.id.tvSlider1Right);

        mSlider2 = (SeekBar) findViewById(R.id.sbSlider2);
        mSlider2InitialValue = (TextView) findViewById(R.id.tvSlider2Left);
        mSlider2FinalValue = (TextView) findViewById(R.id.tvSlider2Right);



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

        mSlider1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser){
                double proportion = (double)progressValue/(double)100;
                System.out.println(proportion);
                System.out.println(exchangeRateOne);
                int IV = (int) ((double)fixedamount + ((double)variableamount*(double)proportion));
                int FV = (int) ((double)fixedamount + ((double)variableamount*(double)proportion*(double)exchangeRateOne));
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
                double proportion = progressValue/100;
                int IV = (int) (fixedamount + (variableamount*proportion));
                int FV = (int) (fixedamount + (variableamount*proportion*exchangeRateTwo));
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
