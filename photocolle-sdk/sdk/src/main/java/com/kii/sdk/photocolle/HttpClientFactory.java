package com.kii.sdk.photocolle;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.apache.OkApacheClient;

import org.apache.http.client.HttpClient;

//For internal use
class HttpClientFactory {

    HttpClient createClient() {
        return new OkApacheClient(new OkHttpClient());
    }
}
