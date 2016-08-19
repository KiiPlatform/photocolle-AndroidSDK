package com.kii.sdk.photocolle;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

//For internal use
class HttpClientFactory {

    HttpClient createClient() {
        return new DefaultHttpClient();
    }
}
