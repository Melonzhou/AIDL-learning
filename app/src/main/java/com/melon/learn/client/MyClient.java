package com.melon.learn.client;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.melon.learn.aidllearning.MainActivity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 去调起Service端的代码
 * Created by Melon on 2017/8/20.
 * http://blog.csdn.net/carson_ho/article/details/73732076
 *
 * 步骤1：添加Retrofit库的依赖
 * 步骤2：创建 接收服务器返回数据 的类
 * 步骤3：创建 用于描述网络请求的接口,要用到注解
 * 步骤4：创建 Retrofit 实例
 * 步骤5：创建 网络请求接口实例 并 配置网络请求参数
 * 步骤6：发送网络请求（采用最常用的异步方式）
 * 步骤7：处理服务器返回的数据
 */

public  class MyClient {
    Retrofit retrofit;
    RequestInterface request;
    private MainActivity mActivity;
    OkHttpClient okHttpClient;
    volatile static int age;
     String name;


    public MyClient(MainActivity activity) {
        mActivity = activity;
        // 步骤4：创建 Retrofit 实例，builder模式、工厂模式
        retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // step5:创建 网络请求接口的实例，用到的是外观模式与代理模式
        request = retrofit.create(RequestInterface.class);

    }

    public void testHashMap(){
        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.put(1, "a1");
        hashMap.put(2, "b2");
        hashMap.put(3, "c3");
        hashMap.put(4, "d4");
        hashMap.put(5, "e5");
        hashMap.put(6, "f6");
        hashMap.put(7, "g7");
        hashMap.put(8, "h8");
        hashMap.put(9, "i9");
        hashMap.put(10, "j10");
        hashMap.put(11, "k11");
        hashMap.put(12, "l12");
        hashMap.put(13, "m13");
        hashMap.put(14, "n14");
        hashMap.put(15, "o15");
        hashMap.put(16, "p16");
        hashMap.put(17, "q17");
        hashMap.put(18, "r18");
        hashMap.put(19, "s19");
        hashMap.put(20, "t20");

        ConcurrentHashMap<Integer , String> conHash = new ConcurrentHashMap<>() ;
        conHash.put(1,"111");
        Hashtable<String, String> table = new Hashtable<>();
        table.put("","");
        table.get("");

        TestFanxing<String , HashMap , Long> t = new TestFanxing("hello", hashMap);


        hashMap.size();



    }

    public synchronized void getNetData(String ss){
        // 步骤6：发送网络请求
        Call<ResultData> call = request.getCall(1001,"melon");
//        try {
//            直接这么用会crash，是因为在主线程里面不能做网络请求。
//            Response<ResultData> response =  call.execute();
//            response.body().show();
//            mActivity.mShowView.setText(response.body().content.out + "xxxx");
//            mActivity.mShowView.setVisibility(View.VISIBLE);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

        call.enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                // 步骤7：处理服务器返回的数据
                response.body().show();
                mActivity.mShowView.setText(response.body().content.out + "hehe");
                mActivity.mShowView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }

    public void getDataWithOkHttpClient(){


        // 1.构造一个okhttpclient实例
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .readTimeout(2000,TimeUnit.MILLISECONDS)
                .cache(new Cache(new File(""), 100))
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(2,TimeUnit.SECONDS);
        OkHttpClient c = builder.build();

        // 2.构造一个request实例
        String URL = "https://github.com/hongyangAndroid";
        Request getReq = new Request.Builder().url(URL).get().build();

        FormBody.Builder form = new FormBody.Builder();
        form.add("id","1001");
        form.add("name","zhouyuan");

        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody reb = RequestBody.create(type,"json-string");

        Request postReq = new Request.Builder().url(URL).post(form.build()).build();
//        try {
//            okHttpClient.newCall(re2).execute();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

        // 3.发送request
        okHttpClient.newCall(postReq).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("melon","onFail....");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // 4.处理返回结果,主线程问题
                Log.i("melon","success .... " + response.body().string());

            }
        });

    }

    public void testLock(){



        ReentrantLock l = new ReentrantLock();
        l.lock();
        try {
            request.notify();
            Log.e("melon","xxxx");
            request.wait();
        }catch (Throwable th){

        }finally {
            l.unlock();
        }

        Handler handler = new Handler();

    }



}
