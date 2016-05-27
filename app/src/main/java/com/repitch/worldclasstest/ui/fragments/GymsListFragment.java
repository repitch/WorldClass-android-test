package com.repitch.worldclasstest.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.repitch.worldclasstest.Constants;
import com.repitch.worldclasstest.DataManager;
import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.network.RFManager;
import com.repitch.worldclasstest.network.models.AuthResult;
import com.repitch.worldclasstest.network.models.Gym;
import com.repitch.worldclasstest.ui.activities.BaseActivity;
import com.repitch.worldclasstest.ui.adapters.GymsAdapter;
import com.repitch.worldclasstest.ui.views.DayTopView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by repitch on 24.05.16.
 */
public class GymsListFragment extends BaseFragment implements Callback<List<Gym>> {

    private RecyclerView mRvGyms;
    private GymsAdapter mGymsAdapter;
    private List<Gym> mGyms;
    private ProgressDialog mProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gyms_list, container, false);

        ((BaseActivity) getActivity()).getSupportActionBar().setTitle("Список клубов");
        findViews(rootView);
        initViews();
        mGyms = DataManager.getInstance().mGyms;
        if (mGyms == null || mGyms.isEmpty()) {
            mProgress.show();
            RFManager.getGyms(this);
        } else {
            setGyms(mGyms);
        }
        return rootView;
    }

    private void setGyms(List<Gym> gyms) {
        DataManager.getInstance().mGyms = gyms;
        mGymsAdapter.swap(gyms);
    }

    private void findViews(View rootView) {
        mRvGyms = (RecyclerView) rootView.findViewById(R.id.rv_gyms);
    }

    private void initViews() {
        mProgress = new ProgressDialog(getActivity());
        mProgress.setMessage("Подождите немного");
        mProgress.setCancelable(false);
        mGymsAdapter = new GymsAdapter(null, new GymsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Gym gym) {
                Bundle args = new Bundle();
                args.putSerializable(GymDetailFragment.ARG_GYM, gym);
                ((BaseActivity)getActivity()).launchFragment(GymDetailFragment.newInstance(args), GymDetailFragment.class.getName());
            }
        });
        mRvGyms.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGyms.setAdapter(mGymsAdapter);
    }

    @Override
    public void onResponse(Call<List<Gym>> call, Response<List<Gym>> response) {
        mProgress.hide();
        mGyms = response.body();
        setGyms(mGyms);
    }

    @Override
    public void onFailure(Call<List<Gym>> call, Throwable t) {
        mProgress.hide();
    }
}
