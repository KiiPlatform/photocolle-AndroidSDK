package com.kii.sdk.photocolle;

class RequestToken implements DTO
{
    private final String tokenKey;
    private final String tokenSecret;

    public RequestToken(String key, String secret) {
        this.tokenKey = key;
        this.tokenSecret = secret;
    }

    public String getTokenKey() {
        return this.tokenKey;
    }

    public String getTokenSecret() {
        return this.tokenSecret;
    }
}
