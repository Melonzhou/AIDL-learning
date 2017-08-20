package com.melon.learn.aidllearning;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.melon.learn.service.IMyAidlInterface;
import com.melon.learn.service.MyService;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mConnection;
    private IMyAidlInterface myAidlInterface;
    private TextView mClickView;
    private TextView mShowView;
    private View.OnClickListener mCommonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mClickView) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClickView = (TextView) findViewById(R.id.view_click);
        mShowView = (TextView) findViewById(R.id.view_show_result);
        mClickView.setOnClickListener(mCommonClick);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.i("melon","onServiceConnected...");
                myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                if (myAidlInterface == null) {
                    return;
                }
                try {
                    myAidlInterface.setString("hello world");
                    String str = myAidlInterface.getStringWithDate();
                    mShowView.setText(str);
                    mShowView.setVisibility(View.VISIBLE);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i("melon","onServiceDisconnected...");
            }
        };
    }
}
