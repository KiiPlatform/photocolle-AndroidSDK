package com.kii.sdk.photocolle;

/**
 * Content thumbnail information.
 */
public class ContentThumbnailInfo implements DTO
{
    private final ContentGUID guid;
    private final MimeType mimeType;
    private final byte[] thumbnailBytes;

    /**
     * Create a new ContentThumbnailInfo.
     *
     * Application developers do not need to use this constructor.
     *
     * @param guid guid of this content.
     * @param mimeType mime type of this content.
     * @param thumbnailBytes thumbnail byte data of this content.
     */
    ContentThumbnailInfo(
            ContentGUID guid,
            MimeType mimeType,
            byte[] thumbnailBytes)
    {
        this.guid = guid;
        this.mimeType = mimeType;
        this.thumbnailBytes = thumbnailBytes;
    }

    /**
     * Get guid of this content.
     *
     * @return guid of this content.
     */
    public ContentGUID getGUID() {
        return this.guid;
    }

    /**
     * Get mime type of this content.
     *
     * @return mime type of this content.
     */
    public MimeType getMimeType() {
        return this.mimeType;
    }

    /**
     * Get thumbnail byte data of this content.
     *
     * @return thumbnail byte data of this content.
     */
    public byte[] getThumbnailBytes() {
        return this.thumbnailBytes;
    }
}
