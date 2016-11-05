package com.zhengsheng.administrator.childteach;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yuhongzhen on 2016/11/4.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);;
    }
}
