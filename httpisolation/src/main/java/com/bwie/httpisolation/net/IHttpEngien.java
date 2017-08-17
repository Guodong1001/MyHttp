package com.bwie.httpisolation.net;

import java.util.Map;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/8/17
 */
public interface IHttpEngien {
    void get(String url, Map<String,Object> params,ICallBack callBack);
    void post(String url, Map<String,Object> params,ICallBack callBack);
}
