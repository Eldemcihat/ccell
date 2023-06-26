package com.ocs.ccell.remote;

import com.ocs.ccell.model.PackageBalanceRemaining;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPackageBalanceRemaining {

    @GET("/subscriber/{msisdn}/balance")
    Call<PackageBalanceRemaining> getPackageBalanceRemaining(@Path("msisdn") String msisdn);

}
