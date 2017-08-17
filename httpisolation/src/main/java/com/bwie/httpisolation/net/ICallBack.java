package com.bwie.httpisolation.net;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/8/17
 */
public interface ICallBack<T>{
    void onSuccess(T t);
    void onError(String errorMessage,int errorCode);
}
