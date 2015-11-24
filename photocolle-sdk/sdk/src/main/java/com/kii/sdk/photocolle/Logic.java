package com.kii.sdk.photocolle;

import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;


interface Logic<ARGTYPE, RETURNTYPE> {

    HttpUriRequest createRequest(URL url, ARGTYPE arg);

    RETURNTYPE parseResponse(HttpResponse response)
        throws HttpException, ApplicationLayerException,
            ResponseBodyParseException, UploadException, InvalidTokenException;

    URL getDefaultUrl();
}
