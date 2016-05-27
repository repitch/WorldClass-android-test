package com.repitch.worldclasstest.ui.activities;

import android.os.Bundle;

import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.ui.fragments.GymsListFragment;
import com.repitch.worldclasstest.ui.fragments.MainMenuFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchFragmentNoBackStack(new MainMenuFragment(), MainMenuFragment.class.getName());
    }
}
