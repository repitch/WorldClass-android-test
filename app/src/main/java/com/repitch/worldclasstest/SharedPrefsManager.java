package com.repitch.worldclasstest;

import android.content.Context;
import android.content.SharedPreferences;

import com.repitch.worldclasstest.network.models.AuthToken;

/**
 * Created by repitch on 25.05.16.
 */
public class SharedPrefsManager {

    private final String PREFS = "PREFS";

    private static SharedPrefsManager manager;
    private SharedPreferences preferences;

    private SharedPrefsManager(Context context) {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static SharedPrefsManager getInstance() {
        if (manager == null)
            manager = new SharedPrefsManager(App.getInstance());
        return manager;
    }

    /**
     * AuthToken
     */
    private static final String AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY";
    private static final String AUTH_TOKEN_CUSTOMER_ID = "AUTH_TOKEN_CUSTOMER_ID";

    public static void saveAuthToken() {
        getInstance().preferences
                .edit()
                .putString(AUTH_TOKEN_KEY, DataManager.getInstance().mAuthToken.key)
                .putString(AUTH_TOKEN_CUSTOMER_ID, DataManager.getInstance().mAuthToken.customerId)
                .commit();
    }

    public static void restoreAuthToken() {
        String key = getInstance().preferences.getString(AUTH_TOKEN_KEY, null);
        String customerId = getInstance().preferences.getString(AUTH_TOKEN_CUSTOMER_ID, null);
        AuthToken authToken = DataManager.getInstance().mAuthToken;
        if (authToken == null) {
            authToken = new AuthToken();
        }
        authToken.key = key;
        authToken.customerId = customerId;
    }

    public static void removeAuthToken() {
        DataManager.getInstance().mAuthToken = null;
        getInstance().preferences
                .edit()
                .putString(AUTH_TOKEN_KEY, null)
                .putString(AUTH_TOKEN_CUSTOMER_ID, null)
                .commit();
    }

}
