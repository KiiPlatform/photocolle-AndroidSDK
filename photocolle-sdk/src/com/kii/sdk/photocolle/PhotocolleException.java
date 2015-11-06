package com.kii.sdk.photocolle;

/**
 * Thrown when an exceptional condition has occurred in this SDK. 
 */
public class PhotocolleException extends Exception {

    private static final long serialVersionUID = -7642842747103852889L;

    /**
     * Constructs an PhotocolleException with the specified exception.
     *
     * @param t the base exception of this.
     */
    PhotocolleException(Throwable t) {
        super(t);
    }

    PhotocolleException(String msg) {
        super(msg);
    }
}
