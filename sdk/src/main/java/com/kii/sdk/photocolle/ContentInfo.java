package com.kii.sdk.photocolle;

import java.util.Date;

/**
 * Content information.
 */
public class ContentInfo implements DTO
{
    private ContentGUID guid;
    private String name;
    private FileType fileType;
    private Date exifCameraDate;
    private Date modifiedDate;
    private Date uploadedDate;
    private long fileDataSize;
    private boolean resizable;

    /**
     * Create a new content information.
     *
     * Application developers do not need to use this constructor.
     *
     * @param guid guid of this content.
     * @param name name of this content.
     * @param fileType file type of this content
     * @param exifCameraDate photo taking day extracted from EXIF.
     * @param modifiedDate modified date time.
     * @param uploadedDate uploaded date time.
     * @param fileDataSize file size of this content.
     * @param resizable flag to determine resizing content is allowed or not.
     */
    ContentInfo(
        ContentGUID guid,
        String name,
        FileType fileType,
        Date exifCameraDate,
        Date modifiedDate,
        Date uploadedDate,
        long fileDataSize,
        boolean resizable)
    {
        this.guid = guid;
        this.name = name;
        this.fileType = fileType;
        this.exifCameraDate = exifCameraDate;
        this.modifiedDate = modifiedDate;
        this.uploadedDate = uploadedDate;
        this.fileDataSize = fileDataSize;
        this.resizable = resizable;
    }

    /**
     * Get guid of this content.
     *
     * @return guid of this content.
     */
    public ContentGUID getGuid() {
        return this.guid;
    }

    /**
     * Get name of this content.
     *
     * @return name of this content.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get file type of this content.
     *
     * @return file type of this content.
     */
    public FileType getFileType() {
        return this.fileType;
    }

    /**
     * Get photo taking day of this content.
     *
     * @return photo taking day of this content.
     */
    public Date getExifCameraDate() {
        return this.exifCameraDate;
    }

    /**
     * Get modified date time of this content.
     *
     * @return modified date time of this content.
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Get uploaded date time of this content.
     *
     * @return uploaded date time of this content.
     */
    public Date getUploadedDate() {
        return this.uploadedDate;
    }

    /**
     * Get file size of this content.
     *
     * @return File size of this content.
     */
    public long getFileDataSize() {
        return this.fileDataSize;
    }

    /**
     * Get a flag to determine resizing content is allowed or not.
     *
     * @return flag to determine resizing content is allowed or not.
     */
    public boolean isResizable() {
        return this.resizable;
    }

}
