package com.repitch.worldclasstest.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.repitch.worldclasstest.ui.fragments.GymsListFragment;

/**
 * Created by repitch on 25.05.16.
 */
public class GymsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchFragmentNoBackStack(new GymsListFragment(), GymsListFragment.class.getName());
    }
}
