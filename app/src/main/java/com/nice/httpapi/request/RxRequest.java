package com.nice.httpapi.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONObject;
import java.util.HashMap;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2015/11/17.
 */
public class RxRequest {

    public static Observable<JSONObject> createJsonRequest(final int method, final String url, final JSONObject jsonRequest){
        System.out.println("url:" + url);
        return Observable.create(new Observable.OnSubscribe<JSONObject>() {
            @Override
            public void call(final Subscriber<? super JSONObject> subscriber) {

                QSJsonObjectRequest jsonObjectRequest = new QSJsonObjectRequest(method, url, jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("response:" + response);
//                        if (MetadataParser.hasError(response)){
//                            //errorCode became the msg
//                            Throwable errorCode = new Throwable(String.valueOf(MetadataParser.getError(response)));
//                            subscriber.onError(errorCode);
//                            subscriber.onCompleted();
//                        }
                        subscriber.onNext(response);
                        subscriber.onCompleted();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Throwable errorCode = new Throwable("8888", error);
                        subscriber.onError(errorCode);
                        subscriber.onCompleted();
                    }
                });
                RequestQueueManager.INSTANCE.getQueue().add(jsonObjectRequest);
            }
        });
    }

    public static Observable<JSONObject> createIdRequest(final String url, final String _id){
        HashMap<String, String> map = new HashMap<>();
        map.put("_id", _id);
        return createJsonRequest(Request.Method.POST, url, new JSONObject(map));
    }
}
