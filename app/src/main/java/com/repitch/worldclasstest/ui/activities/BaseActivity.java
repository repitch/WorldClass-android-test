package com.repitch.worldclasstest.ui.activities;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.repitch.worldclasstest.R;

import java.util.List;

/**
 * Created by repitch on 24.05.16.
 */
public class BaseActivity extends AppCompatActivity {

    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActionBar = getSupportActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        shouldDisplayHomeUp();
    }

    public void launchFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void launchFragmentNoBackStack(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, tag)
                .commit();
    }

    /**
     * Является ли текущее активити последним в стеке
     * @return true, если активити последнее в стеке
     */
    public boolean isLastActivityInStack() {
        ActivityManager mngr = (ActivityManager) getSystemService( ACTIVITY_SERVICE );

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if(taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shouldDisplayHomeUp() {
        boolean canBack;
        if (isLastActivityInStack()) {
            // последний активити в стеке
            canBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        } else {
            canBack = true;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
        }
        /*if (mToolbar != null && canBack) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                    shouldDisplayHomeUp();
                }
            });
        }*/
    }
}
