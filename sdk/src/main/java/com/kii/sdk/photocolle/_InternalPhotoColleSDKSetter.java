package com.kii.sdk.photocolle;

import java.net.URL;

// This is a secret class to integrate PhotoColleSDK into KiiCloudSDK.
public final class _InternalPhotoColleSDKSetter {

    public static void setBaseUrl(PhotoColle photocolle, URL baseUrl) {
        photocolle.baseUrl = baseUrl;
    }
}
