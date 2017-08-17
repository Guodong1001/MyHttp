package com.bwie.httpisolation.net;

import com.bwie.httpisolation.HttpManager;
import com.bwie.httpisolation.utils.GenericUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.httpisolation.utils.GenericUtil.getSuperGenericClass;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/8/17
 */
public class OkHttpEngien implements IHttpEngien {
    private OkHttpClient client;

    public OkHttpEngien() {
        client = new OkHttpClient.Builder().build();
    }


    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callBack) {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpManager.getInstance().handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage(), 0);

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                HttpManager.getInstance().handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Class<?> clazz = GenericUtil.getSuperGenericClass(callBack.getClass());
                        Object o = gson.fromJson(result, clazz);
                        callBack.onSuccess(o);

                    }
                });
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        FormBody.Builder body = new FormBody.Builder();
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                body.add(entry.getKey(), (String) entry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpManager.getInstance().handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage(), 0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                HttpManager.getInstance().handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Class<?> clazz = getSuperGenericClass(callBack.getClass());
                        callBack.onSuccess(gson.fromJson(result, clazz));
                    }
                });
            }
        });

    }
}
