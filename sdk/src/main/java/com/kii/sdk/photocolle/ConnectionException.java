package com.kii.sdk.photocolle;

import java.io.IOException;

/**
 * Thrown when network IO error occurred. (ex. Time out, No network, etc.)
 */
public class ConnectionException extends PhotocolleException {

    private static final long serialVersionUID = 1L;

    ConnectionException(IOException cause) {
        super(cause);
    }

}
