package com.ocs.ccell.remote;

import com.ocs.ccell.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILoginApi {

    @GET("/subscriber-dto/login")
    Call<LoginRequest> login(@Query("msisdn") String msisdn, @Query("password") String password);

}
