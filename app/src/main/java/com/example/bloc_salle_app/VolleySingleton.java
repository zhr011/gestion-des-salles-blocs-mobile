package com.example.bloc_salle_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton myInstance;
    private RequestQueue requestQueue;
    private static Context context;
    private VolleySingleton(Context context){
        this.context = context;
        this.requestQueue = getRequestQueue();

    }
    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized VolleySingleton getInstance(Context context){
        if (myInstance==null){
            myInstance = new VolleySingleton(context);
        }
        return myInstance;
    }
    public<T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);

    }
}