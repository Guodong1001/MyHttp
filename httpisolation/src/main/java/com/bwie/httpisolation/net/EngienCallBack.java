package com.bwie.httpisolation.net;

import com.bwie.httpisolation.utils.GenericUtil;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/8/17
 */
public  abstract class EngienCallBack<T> implements ICallBack<T> {
    private Class<T> clazz;

    public EngienCallBack() {
        this.clazz = GenericUtil.getSuperGenericClass(this.getClass());
    }
}
