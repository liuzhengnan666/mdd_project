package com.example.administrator.llc_p.service;

import android.util.Log;

import com.example.administrator.llc_p.api.UtilInterFace;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUserFactory {
    public String URL="http://api.yybcoin.cn/";
    private static RetrofitUserFactory mFactory;
    private static final String TAG = "RetrofitFactory";
    private Retrofit mRetrofit;

    private RetrofitUserFactory() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("this my Interceptor","msg:"+message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
//        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.d("this my Interceptor","msg:"+message);
//            }
//        }));
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10,TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        //超时重连
        builder.retryOnConnectionFailure(true);

        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder().baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUserFactory getFactory() {
        if (mFactory != null) return mFactory;
        synchronized (TAG) {
            if (mFactory != null) return mFactory;
            mFactory = new RetrofitUserFactory();
        }
        return mFactory;
    }

    public UtilInterFace createService(){
        return mRetrofit.create(UtilInterFace.class);
    }


}
