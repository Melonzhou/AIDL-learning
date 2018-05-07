package com.melon.learn.client;

import android.util.Log;

/**
 * Created by Melon on 2018/3/23.
 */

public class TestFanxing<T,P,V> {

    private T drive;

    public TestFanxing(T t, V v){

        Log.e("melon", t.getClass().getName() + v.getClass().getName());
    }
}
