package com.repitch.worldclasstest.network;

import com.repitch.worldclasstest.network.models.AuthResult;
import com.repitch.worldclasstest.network.models.Customer;
import com.repitch.worldclasstest.network.models.Gym;
import com.repitch.worldclasstest.network.models.ScheduleResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by repitch on 23.05.16.
 */
public interface RestInterface {

    @POST("gyms")
    Call<List<Gym>> getGyms();

//    https://personal.wclass.ru/services/api/schedule/F4D4F538-24DA-467E-89FD-CFB680E81193/2016-05-15/2016-05-30
    @POST("schedule/{gymId}/{start}/{end}")
    Call<ScheduleResult> getGymSchedule(@Path("gymId") String gymId,
                                        @Path("start") String startDate,
                                        @Path("end") String endDate);

    @FormUrlEncoded
    @POST("customer/auth")
    Call<AuthResult> auth(@Field("login") String login,
                          @Field("password") String password,
                          @Field("remember") boolean remember);

    @POST("customer")
    Call<Customer> getCustomer(@Header("Private-Key") String key);


}