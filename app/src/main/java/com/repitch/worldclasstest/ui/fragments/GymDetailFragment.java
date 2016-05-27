package com.repitch.worldclasstest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.network.RFManager;
import com.repitch.worldclasstest.network.models.Gym;
import com.repitch.worldclasstest.network.models.ScheduleResult;
import com.repitch.worldclasstest.ui.activities.BaseActivity;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by repitch on 24.05.16.
 */
public class GymDetailFragment extends BaseFragment {

    public static final String ARG_GYM = "ARG_GYM";
    private Gym mGym;
    private TextView mTxtName, mTxtDivision;

    public GymDetailFragment() {

    }

    public static GymDetailFragment newInstance(Bundle args) {
        GymDetailFragment fragment = new GymDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gym_detail, container, false);
        mGym = (Gym) getArguments().getSerializable(ARG_GYM);
        ((BaseActivity) getActivity()).getSupportActionBar().setTitle(mGym.name);
        findViews(rootView);
        initViews();

        loadSchedule();
        return rootView;
    }

    private void loadSchedule() {
        Date today = Calendar.getInstance().getTime();
        RFManager.getGymSchedule(mGym, today, today, new Callback<ScheduleResult>() {
            @Override
            public void onResponse(Call<ScheduleResult> call, Response<ScheduleResult> response) {
                Log.e("","");
            }

            @Override
            public void onFailure(Call<ScheduleResult> call, Throwable t) {
                Log.e("","");
            }
        });
    }

    private void findViews(View rootView) {
        mTxtName = (TextView) rootView.findViewById(R.id.txt_name);
        mTxtDivision = (TextView) rootView.findViewById(R.id.txt_division);
    }

    private void initViews() {
        mTxtName.setText(mGym.name);
        mTxtDivision.setText(mGym.division);
    }

}
