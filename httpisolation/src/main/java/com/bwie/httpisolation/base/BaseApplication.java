package com.bwie.httpisolation.base;

import android.app.Application;

import com.bwie.httpisolation.HttpManager;
import com.bwie.httpisolation.net.OkHttpEngien;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/8/17
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.getInstance().init(new OkHttpEngien());
    }
}
