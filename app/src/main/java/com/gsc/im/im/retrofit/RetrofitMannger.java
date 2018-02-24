package com.gsc.im.im.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 使用单列模式，封装Retrofit网络请求
 */

public class RetrofitMannger {

    private static final String BASEURL = "http://www.bwstudent.com/";
    private static RetrofitMannger retrofitMannager;
    private static Retrofit mretrofit;


    public static RetrofitMannger newInstance() {

        if (retrofitMannager == null) {
            synchronized (RetrofitMannger.class) {
                if (retrofitMannager == null) {
                    return retrofitMannager = new RetrofitMannger();
                }
            }
        }
        return retrofitMannager;


    }

    //构造器
    private RetrofitMannger() {
        mretrofit = getretrofit();
    }


    //构建Okhttp
    private static OkHttpClient getokhttpclient() {
        return new OkHttpClient.Builder()
                //.addInterceptor(new InterCepter())
                .connectTimeout(0, TimeUnit.MILLISECONDS)
                .build();
    }

    //构建Retrofit
    private static Retrofit getretrofit() {
        return new Retrofit.Builder()
                .client(getokhttpclient())
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    //返回Retrofit，通过动态代理的模式生成相应的Http请求
    public <T> T mcreat(Class<T> t) {
        return mretrofit.create(t);
    }
}
