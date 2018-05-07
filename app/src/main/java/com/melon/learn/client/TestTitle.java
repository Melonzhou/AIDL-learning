package com.melon.learn.client;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by Melon on 2018/3/21.
 */

@Title(id=0,name="")
public class TestTitle {

    public  void getTitle(String xx){}
    public static void get(){}
    public void set(){
        InnerClass.hehe();
    }



    public  static class InnerClass{
        public  int age;
        public static void hehe(){}

        public InnerClass(){
            age = 3;
        }
    }

    public class Heart{
        public int size;
        public Heart(){
            new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int kk;

                }
            };
        }
    }
}
