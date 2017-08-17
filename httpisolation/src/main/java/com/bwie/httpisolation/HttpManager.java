package com.bwie.httpisolation;

import android.os.Handler;

import com.bwie.httpisolation.net.ICallBack;
import com.bwie.httpisolation.net.IHttpEngien;

import java.util.Map;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/8/17
 */
public class HttpManager implements IHttpEngien {

    private static HttpManager instance;
    private IHttpEngien mEngien;
    public Handler handler = new Handler();
    private HttpManager(){

    }
    public void init(IHttpEngien engien){
        mEngien = engien;
    }
    public static HttpManager getInstance(){
        if(instance == null){
            synchronized (HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {
        mEngien.get(url,params,callBack);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        mEngien.post(url,params,callBack);
    }
}
