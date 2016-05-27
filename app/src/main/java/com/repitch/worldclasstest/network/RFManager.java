package com.repitch.worldclasstest.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.repitch.worldclasstest.Constants;
import com.repitch.worldclasstest.network.models.AuthResult;
import com.repitch.worldclasstest.network.models.AuthToken;
import com.repitch.worldclasstest.network.models.Customer;
import com.repitch.worldclasstest.network.models.Gym;
import com.repitch.worldclasstest.network.models.ScheduleResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by repitch on 23.05.16.
 */
public class RFManager {

    private static RFManager sRFManager;
    private Retrofit mRetrofit;
    private RestInterface mRestInterface;

    private Gson mGson, mGson2;

    public RFManager() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        mGson2 = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm")
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addConverterFactory(GsonConverterFactory.create(mGson2))
                .build();
        mRestInterface = mRetrofit.create(RestInterface.class);
    }

    public static RFManager getInstance() {
        if (sRFManager == null) {
            sRFManager = new RFManager();
        }
        return sRFManager;
    }

    public static void getGyms(Callback<List<Gym>> callback) {
        Call<List<Gym>> call = getInstance().mRestInterface.getGyms();
        call.enqueue(callback);
    }

    public static void auth(String login, String password, Callback<AuthResult> callback) {
        Call<AuthResult> call = getInstance().mRestInterface.auth(login, password, true);
        call.enqueue(callback);
    }

    public static void getCustomer(String key, Callback<Customer> callback) {
        Call<Customer> call = getInstance().mRestInterface.getCustomer(key);
        call.enqueue(callback);
    }

    public static void getGymSchedule(Gym gym, Date start, Date end, Callback<ScheduleResult> callBack) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_SCHEDULE);
        String startStr = sdf.format(start);
        String endStr = sdf.format(end);
        Call<ScheduleResult> call = getInstance().mRestInterface.getGymSchedule(gym.id, startStr, endStr);
        call.enqueue(callBack);
    }

}
