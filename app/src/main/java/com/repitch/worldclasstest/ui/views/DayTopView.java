package com.repitch.worldclasstest.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.repitch.worldclasstest.R;

/**
 * Created by repitch on 24.05.16.
 */
public class DayTopView extends LinearLayout {

    private TextView mTxtDay, mTxtDayWeek;

    public DayTopView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DayTopView);
        int day = a.getInteger(R.styleable.DayTopView_day, 0);
        String dayWeek = a.getString(R.styleable.DayTopView_dayWeek);
        boolean isChosen = a.getBoolean(R.styleable.DayTopView_chosen, false);
        boolean isMuted = a.getBoolean(R.styleable.DayTopView_muted, false);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_day_top, this, true);

        mTxtDay = (TextView) findViewById(R.id.txt_day);
        mTxtDayWeek = (TextView) findViewById(R.id.txt_day_week);
        mTxtDay.setText(String.format("%d", day));
        mTxtDayWeek.setText(dayWeek);
        if (isChosen) {
            mTxtDay.setBackgroundResource(R.drawable.round_text);
            mTxtDay.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            mTxtDay.setBackgroundResource(android.R.color.transparent);
            mTxtDay.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        if (isMuted) {
            this.setAlpha(0.3f);
        }
    }

    public DayTopView(Context context) {
        super(context);
    }


}
