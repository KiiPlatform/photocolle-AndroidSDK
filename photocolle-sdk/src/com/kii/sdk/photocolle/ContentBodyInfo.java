package com.kii.sdk.photocolle;

import java.io.InputStream;

/**
 * Content body information received from server.
 *
 * ContentBodyInfo has InputStream as its field. The InputStream must be
 * closed by application. even if application does not use the InputStream.
 */
public class ContentBodyInfo implements DTO
{
    private final MimeType contentType;
    private InputStream inputStream;

    /**
     * Create a new content body information.
     *
     * Application developers do not need to use this constructor.
     *
     * @param contentType MIME type of this content.
     * @param inputStream body data.
     */
    ContentBodyInfo(
            MimeType contentType,
            InputStream inputStream)
    {
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    /**
     * Get content type as MIME type.
     *
     * @return MIME type of this content.
     */
    public MimeType getContentType() {
        return this.contentType;
    }

    /**
     * Get InputStream object which contains body data.
     *
     * InputStream must be closed by application.
     *
     * @return body data.
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

}
