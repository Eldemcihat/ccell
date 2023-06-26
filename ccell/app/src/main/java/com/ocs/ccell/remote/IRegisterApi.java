package com.ocs.ccell.remote;

import com.ocs.ccell.model.RegisterRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface IRegisterApi {
    @POST("subscriber-dto/register")
    Call<RegisterRequest> register(@Body Map<String, Object> registerRequest);
}