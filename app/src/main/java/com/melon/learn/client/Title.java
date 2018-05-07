package com.melon.learn.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 一个名为Title的注解，有2个属性（id和name）,生命周期在运行时
 * Created by Melon on 2018/3/21.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Title {
    int id();
    String name();
}


