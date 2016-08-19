package com.kii.sdk.photocolle;

/**
 * Thrown when PhotoColleSDK receives unexptected response body data from
 * server, therefore, PhotoColleSDK failed to parse response body
 * data to return value of PhotoColleSDK methods.
 */
public class ResponseBodyParseException extends PhotocolleException {

    private static final long serialVersionUID = 1L;

    ResponseBodyParseException(Throwable cause) {
        super(cause);
    }
}
