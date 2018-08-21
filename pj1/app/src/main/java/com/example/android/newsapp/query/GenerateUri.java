package com.example.android.newsapp.query;

import java.net.MalformedURLException;
import java.net.URL;


public class GenerateUri {
    private static final String GENERATED_API_KEY = "9f77a22f-b501-4dff-b9ba-f449a53a8731";
    private static final String PROTOCOL = "https";
    private static final String AUTHORITY = "content.guardianapis.com";
    private static final String ORDER_BY = "order-by";
    private static final String API = "api-key";
    private static final String ORDER_NEWEST = "newest";
    private static final String FORMAT = "format";
    private static final String JSON = "json";
    private static final String ITEMS = "page-size";


    public static URL getUri(String type, String noOfItems) {
        try {
            return new URL(baseUri(type, noOfItems));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String baseUri(String type, String noOfItems) {
        return new android.net.Uri.Builder()
                .scheme(PROTOCOL)
                .encodedAuthority(AUTHORITY)
                .appendPath(type)
                .appendQueryParameter(ORDER_BY, ORDER_NEWEST)
                .appendQueryParameter(API, GENERATED_API_KEY)
                .appendQueryParameter(FORMAT, JSON)
                .appendQueryParameter(ITEMS, noOfItems)
                .build()
                .toString();
    }
}
