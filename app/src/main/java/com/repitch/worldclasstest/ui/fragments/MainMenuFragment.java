package com.repitch.worldclasstest.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.ui.activities.BaseActivity;
import com.repitch.worldclasstest.ui.activities.GymsActivity;
import com.repitch.worldclasstest.ui.activities.PersonalAreaActivity;

/**
 * Created by repitch on 25.05.16.
 */
public class MainMenuFragment extends BaseFragment {

    private LinearLayout mLlPersonalArea, mLlFitnessClubs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        findViews(rootView);
        initViews();

        return rootView;
    }

    private void initViews() {
        mLlPersonalArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PersonalAreaActivity.class));
            }
        });
        mLlFitnessClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GymsActivity.class));
            }
        });
    }

    private void findViews(View rootView) {
        mLlPersonalArea = (LinearLayout) rootView.findViewById(R.id.ll_personalarea);
        mLlFitnessClubs = (LinearLayout) rootView.findViewById(R.id.ll_fitnessclubs);
    }
}
