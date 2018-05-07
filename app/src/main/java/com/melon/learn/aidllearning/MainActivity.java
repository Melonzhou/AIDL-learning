package com.melon.learn.aidllearning;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.melon.learn.client.MyClient;
import com.melon.learn.service.IMyAidlInterface;
import com.melon.learn.service.MyService;
import com.melon.learn.service.TreeNode;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mConnection;
    private IMyAidlInterface myAidlInterface;
    private TextView mClickView;
    public TextView mShowView;
    public ImageView mImageView;
    private MyClient client;
    private View.OnClickListener mCommonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mClickView) {
                if (myAidlInterface == null) {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                } else {
                    try {
                        myAidlInterface.setString("oooh ~");
                        mShowView.setText(myAidlInterface.getStringWithDate(""));
                        mShowView.setVisibility(View.VISIBLE);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
//                client = new MyClient(MainActivity.this);
////                client.getDataWithOkHttpClient();
//                client.testHashMap();

//                String url = "http://imgsrc.baidu.com/forum/pic/item/c9ff4434970a304e657ea63fdac8a786cb175cf2.jpg";
////                Glide.with(MainActivity.this).load(url).into(mImageView);
//                Glide.with(MainActivity.this).load(R.mipmap.ic_launcher).into(mImageView);

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
        mImageView = (ImageView) findViewById(R.id.view_image_show);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.i("melon","onServiceConnected...");
                myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                if (myAidlInterface == null) {
                    return;
                }
                try {
                    myAidlInterface.setString("hello world ~ ");
                    TreeNode node = new TreeNode();
                    myAidlInterface.setLinkNode(node);
                    String str = myAidlInterface.getStringWithDate("");
                    Log.e("melon","node.name=" + myAidlInterface.getTreeNode().name);
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
