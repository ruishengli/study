package com.inlook.or.study.nohttp;

import com.inlook.or.study.utils.Logs;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

/**
 * Created by or on 2016/7/12.
 */
public class NoHttp {

    private static final String TAG = NoHttp.class.getSimpleName();
    public static void testNoHttp() {

        Request<String> request = com.yolanda.nohttp.NoHttp.createStringRequest("http://www.baidu.com");
        RequestQueue queue = com.yolanda.nohttp.NoHttp.newRequestQueue();
        queue.add(0,request,new OnResponseListener() {

            @Override
            public void onStart(int what) {
                Logs.d(TAG,"onStart");
            }

            @Override
            public void onSucceed(int what, Response response) {
                Logs.d(TAG,"onSucceed:" + response.get() + "," +response.getCookies());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                Logs.d(TAG,"onFailed");
            }

            @Override
            public void onFinish(int what) {
                Logs.d(TAG,"onFinish");
            }
        });
    }
}
