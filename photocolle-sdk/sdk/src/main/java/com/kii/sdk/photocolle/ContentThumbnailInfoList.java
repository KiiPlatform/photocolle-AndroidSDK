package com.kii.sdk.photocolle;

import java.util.ArrayList;
import java.util.List;

/**
 * List of content thumbnail information.
 */
public class ContentThumbnailInfoList implements DTO
{
    private final List<ContentThumbnailInfo> list =
        new ArrayList<ContentThumbnailInfo>();

    private final List<ContentGUID> ngList = new ArrayList<ContentGUID>();

    /**
     * Create a new ContentThumbnailInfoList
     *
     * Application developers do not need to use this constructor.
     */
    ContentThumbnailInfoList() {}

    /**
     * Get list of content thumbnail information.
     *
     * @return list.
     */
    public List<ContentThumbnailInfo> getList() {
        return this.list;
    }

    /**
     * Get the list of content guids whose thumbnails PhotoColleSDK failed to
     * retrieve.
     *
     * @return ngList.
     */
    public List<ContentGUID> getNGList() {
        return this.ngList;
    }
}
