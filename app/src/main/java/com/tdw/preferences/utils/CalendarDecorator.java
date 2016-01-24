package com.tdw.preferences.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.tdw.preferences.R;

import java.util.Date;

/**
 * Created by siddhaja on 1/24/2016.
 */

public class CalendarDecorator implements CalendarCellDecorator {
    static int selectedDateCount =0;
    @Override
    public void decorate(CalendarCellView cellView,Date date){
        String dateString = Integer.toString(date.getDate());


        if(cellView.isSooner()){
            SpannableString string = new SpannableString(dateString + "\nSoon");
            string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            cellView.setText(string);
                cellView.setBackgroundColor(Color.BLUE);
        }
        if(cellView.isLater()) {
            SpannableString string = new SpannableString(dateString + "\nLate");
            string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            cellView.setText(string);
                cellView.setBackgroundColor(Color.RED);
        }
        if(cellView.isCashRewards()) {
            SpannableString string = new SpannableString(dateString + "\nCash");
            string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            cellView.setText(string);
                cellView.setBackgroundColor(Color.GREEN);
        }

    }
}
