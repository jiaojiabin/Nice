package com.nice.httpapi.request;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.nice.NiceApplication;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

/**
 * Created by i068020 on 2/24/15.
 */
public enum RequestQueueManager {
    INSTANCE;

    private RequestQueue queue;
    RequestQueueManager() {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());
        OkUrlFactory okUrlFactory = new OkUrlFactory(client);
        queue = Volley.newRequestQueue(NiceApplication.instance(), new OkHttpStack(okUrlFactory));
    }

    public RequestQueue getQueue() {
        return queue;
    }
}
