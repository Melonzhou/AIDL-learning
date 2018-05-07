package com.melon.learn.client;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by Melon on 2018/3/19.
 */

public interface RequestInterface {

    @GET(value = "ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    @HTTP(method = "", path = "{name}", hasBody = false)
    Call<ResultData> getCall(@Path("id") int id, @Path("name") String name);
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法


    public void show();

}
