package com.example.biitprojectwaitingqueuesystem.Services;

import com.example.biitprojectwaitingqueuesystem.Supervisor.supervisor_static_data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit=null;
    public static String ipa;
    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit =new Retrofit.Builder()
                .baseUrl(ipa+"/BIITWaitingQueueSystem/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;

    }
}
