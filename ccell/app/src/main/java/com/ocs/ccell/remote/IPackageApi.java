package com.ocs.ccell.remote;

import com.ocs.ccell.model.PackageRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPackageApi {
    @GET("package/packageIdName")
    Call<List<PackageRequest>> getPackageNameId();
}
