package com.melon.learn.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 远程client调用的service
 * Created by Melon on 2017/8/20.
 */

public class MyService extends Service{

    private IMyAidlInterface.Stub mBinder;
    private String mText;
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new IMyAidlInterface.Stub() {
            @Override
            public void setString(String test) throws RemoteException {
                mText = test;
            }

            @Override
            public String getStringWithDate() throws RemoteException {
                if (mText != null) {
                    return mText + System.currentTimeMillis();
                }
                return null;
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


}
