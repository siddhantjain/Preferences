package com.tdw.preferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.tdw.preferences.models.game;
import com.tdw.preferences.models.user;
import com.tdw.preferences.surveys.SurveyOne;
import com.tdw.preferences.utils.CalendarDecorator;
import com.tdw.preferences.utils.DataStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestUserUnderstanding extends AppCompatActivity {

    private CalendarPickerView mCalendar;
    boolean isFlag = false;

    private SeekBar mSlider1;
    private TextView mSlider1InitialValue;
    private TextView mSlider1FinalValue;

    private SeekBar mSlider2;
    private TextView mSlider2InitialValue;
    private TextView mSlider2FinalValue;

    private String surveyorDialogTitleReference;
    private String surveyorDialogBodyReference;

    int fixedamount = 20;
    int variableamount = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_user_understanding);

        int numDaysToSoonerDate = 7;
        int numDaysToLaterDate = 28;
        final float exchangeRateOne = 1.0f;
        final float exchangeRateTwo = 1.25f;


        /*Calendar View Computation*/
        mCalendar = (CalendarPickerView) findViewById(R.id.cvGame0CurrentMonth);

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
        mSlider1 = (SeekBar) findViewById(R.id.sbGame0Slider1);
        mSlider1InitialValue = (TextView) findViewById(R.id.tvGame0Slider1Left);
        mSlider1FinalValue = (TextView) findViewById(R.id.tvGame0Slider1Right);

        mSlider2 = (SeekBar) findViewById(R.id.sbGame0Slider2);
        mSlider2InitialValue = (TextView) findViewById(R.id.tvGame0Slider2Left);
        mSlider2FinalValue = (TextView) findViewById(R.id.tvGame0Slider2Right);



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

    }

    private AlertDialog foDialogBuilder;

    public void showInstructionsDialog(View view)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getApplicationContext().getString(R.string.instructions_title));
        alertDialog.setMessage(getApplicationContext().getString(R.string.instructions_test_body));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Ok",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    public void doUnderstandingCheck (View view) {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getApplicationContext().getString(R.string.E0C_title));
        alertDialog.setMessage(getApplicationContext().getString(R.string.E0C_body));

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //decide on which strings will come in the next dialog;

                        boolean condition1 = Integer.parseInt(mSlider1InitialValue.getText().toString()) >= 40;
                        boolean condition2 = Integer.parseInt(mSlider1FinalValue.getText().toString()) <=
                                1.25 * (Integer.parseInt(mSlider2FinalValue.getText().toString()));
                        if (condition1 && condition2) {
                            surveyorDialogTitleReference = getApplicationContext().getString(R.string.E0D_title);
                            surveyorDialogBodyReference = getApplicationContext().getString(R.string.E0D_body);
                        }

                        if (!condition1 && condition2) {

                            surveyorDialogTitleReference = getApplicationContext().getString(R.string.E0E_title);
                            surveyorDialogBodyReference = getApplicationContext().getString(R.string.E0E_body);
                        }

                        if (condition1 && !condition2) {
                            surveyorDialogTitleReference = getApplicationContext().getString(R.string.E0F_title);
                            surveyorDialogBodyReference = getApplicationContext().getString(R.string.E0F_body);
                        }

                        if (!condition1 && !condition2) {
                            surveyorDialogTitleReference = getApplicationContext().getString(R.string.E0G_title);
                            surveyorDialogBodyReference = getApplicationContext().getString(R.string.E0G_body);
                        }
                        /*old stuff*/
                        dialog.dismiss();
                        showFODialog();
                    }

                });
        alertDialog.show();
    }

    public void showFODialog()
    {

        /*setting up the surveyor instruction dialog*/
        foDialogBuilder = new AlertDialog.Builder(this).create();
        foDialogBuilder.setTitle(surveyorDialogTitleReference);
        foDialogBuilder.setMessage(surveyorDialogBodyReference);
        foDialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(TestUserUnderstanding.this, SurveyOne.class);
                        startActivity(intent);
                    }
                });

        foDialogBuilder.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(TestUserUnderstanding.this, TestUserUnderstanding.class);
                        startActivity(intent);
                    }
                    });


        foDialogBuilder.show();
    }
}

