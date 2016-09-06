package com.kii.sdk.photocolle;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.apache.OkApacheClient;

import org.apache.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

//For internal use
class HttpClientFactory {

    private static HttpClient CLIENT = null;

    synchronized HttpClient createClient() {
        if (CLIENT != null) {
            return CLIENT;
        }
        OkHttpClient client = new OkHttpClient();
        client.setRetryOnConnectionFailure(true);
        CLIENT = new OkApacheClient(client);
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        return CLIENT;

    }
}
