package com.example.android.newsapp.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.newsapp.query.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsLoader extends AsyncTaskLoader<List<NewsDetail>> {
    private final String RESPONSE = "response";
    private final String RESULTS = "results";
    private final String TAG = NewsLoader.class.getSimpleName();
    private URL url;
    private Context c;

    public NewsLoader(Context context, URL url) {
        super(context);
        c = context;
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<NewsDetail> loadInBackground() {
        List<NewsDetail> newsDetailList;
        newsDetailList = getList();
        return newsDetailList;
    }

    private ArrayList<NewsDetail> getList() {
        ArrayList<NewsDetail> list = new ArrayList<NewsDetail>();
        String response = null;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                response = Query.makeHttpReques(url);
            }
        } catch (IOException e) {
            Log.d(TAG, e.toString());


        }
        try {
            if (response != null) {
                JSONObject root = new JSONObject(response);
                JSONObject resultData = root.getJSONObject(RESPONSE);
                JSONArray results = resultData.getJSONArray(RESULTS);

                if (null != results && results.length() > 0) {
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject current = results.getJSONObject(i);
                        list.add(new NewsDetail(current.getString("sectionName"), current.getString("webTitle"), current.getString("webUrl"), current.getString("webPublicationDate")));
                    }
                }
            }

        } catch (JSONException e) {
            Log.d(TAG, e.toString());
        }
        return list;
    }

}
