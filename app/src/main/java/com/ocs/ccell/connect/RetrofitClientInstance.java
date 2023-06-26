package com.ocs.ccell.connect;

import com.ocs.ccell.remote.ILoginApi;
import com.ocs.ccell.remote.IPackageApi;
import com.ocs.ccell.remote.IPackageBalanceRemaining;
import com.ocs.ccell.remote.IPackageInfo;
import com.ocs.ccell.remote.IRegisterApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.30:8080/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }
    public static IRegisterApi getRegisterService(){

        IRegisterApi iRegisterApi = getRetrofit().create(IRegisterApi.class);
        return iRegisterApi;
    }
    public static IPackageApi getPackage(){

        IPackageApi iPackageApi = getRetrofit().create(IPackageApi.class);

        return iPackageApi;
    }
    public static ILoginApi getUserService(){

        ILoginApi iLoginApi = getRetrofit().create(ILoginApi.class);

        return iLoginApi;
    }
    public static IPackageInfo getPackageInfo(){

        IPackageInfo iPackageInfo = getRetrofit().create(IPackageInfo.class);

        return  iPackageInfo;
    }
    public static IPackageBalanceRemaining getBalanceRemaining(){
        IPackageBalanceRemaining iPackageBalanceRemaining = getRetrofit().create(IPackageBalanceRemaining.class);

        return iPackageBalanceRemaining;
    }
}
