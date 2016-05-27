package com.repitch.worldclasstest;

import android.app.Application;

/**
 * Created by repitch on 25.05.16.
 */
public class App extends Application {

    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        SharedPrefsManager.restoreAuthToken();
    }

    public static App getInstance() {
        return application;
    }
}
