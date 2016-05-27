package com.repitch.worldclasstest;

import com.repitch.worldclasstest.network.models.AuthToken;
import com.repitch.worldclasstest.network.models.Customer;
import com.repitch.worldclasstest.network.models.Gym;

import java.util.List;

/**
 * Created by repitch on 25.05.16.
 */
public class DataManager {
    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    public AuthToken mAuthToken;
    public Customer mCustomer;
    public List<Gym> mGyms;
}
