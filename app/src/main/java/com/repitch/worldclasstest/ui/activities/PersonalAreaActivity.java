package com.repitch.worldclasstest.ui.activities;

import android.os.Bundle;

import com.repitch.worldclasstest.ui.fragments.GymsListFragment;
import com.repitch.worldclasstest.ui.fragments.PersonalAreaFragment;

/**
 * Created by repitch on 25.05.16.
 */
public class PersonalAreaActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchFragmentNoBackStack(new PersonalAreaFragment(), PersonalAreaFragment.class.getName());
    }
}
