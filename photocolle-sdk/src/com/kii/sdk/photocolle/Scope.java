package com.kii.sdk.photocolle;

/**
 * Applications request permissions to  Docomo Authentication server with
 * this Scope.
 *
 * @see <A
 * HREF="https://dev.smt.docomo.ne.jp/pdf/API_common_reference_v2.0.0.pdf">
 * docomo Developer support API common reference version 2.0.0</A>
 * @see Authority#authenticate(Context, String, String, String, EnumSet,
 * String, AuthenticateCallback)
 */
public enum Scope {

    /**
     * A scope to get a list of PhotoColle photos and movies.
     *
     * <P>
     * This scope enables following APIs:
     * <UL>
     *  <LI>{@link PhotoColle#getContentIDList(FileType, boolean, DateFilter,
     *  Integer, Integer, SortType) getContentIDList(FileType, boolean,
     *  DateFilter, Integer, Integer, SortType)}</LI>
     *  <LI>{@link PhotoColle#getContentDeletionHistory(FileType, Date,
     *  Integer, Integer) getContentDeletionHistory(FileType, Date,
     *  Integer, Integer)}</LI>
     * </UL>
     * </P>
     */
    PHOTO_GET_CONTENTS_LIST("PhotoGetContentsList"),

    /**
     * A scope to get a PhotoColle photo or movie.
     *
     * <P>
     * This scope enables following APIs:
     * <UL>
     *  <LI>{@link PhotoColle#getContentBodyInfo(FileType, ContentGUID,
     * ResizeType) getContentBodyInfo(FileType, ContentGUID,
     * ResizeType)}</LI>
     *  <LI>{@link PhotoColle#getContentThumbnailInfo(ContentGUID...)
     * getContentThumbnailInfo(ContentGUID...)}</LI>
     * </UL>
     * </P>
     */
    PHOTO_GET_CONTENT("PhotoGetContent"),

    /**
     * A scope to upload a PhotoColle photo or movie.
     *
     * <P>
     * This scope enables following API:
     * <UL>
     *  <LI>{@link PhotoColle#uploadContentBody(FileType, String, long,
     * MimeType, InputStream) uploadContentBody(FileType, String, long,
     * MimeType, InputStream)}</LI>
     * </UL>
     * </P>
     */
    PHOTO_UPLOAD_CONTENT("PhotoUploadContent"),

    /**
     * A scope to confirm vacant size of PhotoColle.
     *
     * <P>
     * This scope enables following API:
     * <UL>
     *  <LI>{@link PhotoColle#getCapacityInfo() getCapacityInfo()}</LI>
     * </UL>
     * </P>
     */
    PHOTO_GET_VACANT_SIZE("PhotoGetVacantSize"),

    /**
     * A scope to update rotation information of a PhotoColle photo.
     *
     * <P>
     * Current PhotoColleSDK does not adapt this feature. The method
     * using this feature will be provided in future.
     * </P>
     */
    PHOTO_UPDATE_ROTATE_INFO("PhotoUpdateRotateInfo"),

    /**
     * A scope to update trash information of a PhotoColle photo and movie.
     *
     * <P>
     * Current PhotoColleSDK does not adapt this feature. The method
     * using this feature will be provided in future.
     * </P>
     */
    PHOTO_UPDATE_TRASH_INFO("PhotoUpdateTrashInfo"),

    /**
     * A scope to get a list of tags and a list of PhotoColle photos and
     * movies with tags.
     *
     * <P>
     * If applications want to use this scope, you need to demand to Docomo.
     * This scope enables following APIs:
     * <UL>
     *  <LI>{@link PhotoColle#getContentIDListWithTags(ProjectionType,
     * FileType, List, boolean, DateFilter, Integer, Integer, SortType)
     * getContentIDListWithTags(ProjectionType, FileType, List, boolean,
     * DateFilter, Integer, Integer, SortType)}</LI>
     *  <LI>{@link PhotoColle#getTagIDList(Category, FileType, Date)
     * getTagIDList(Category, FileType, Date)}</LI>
     * </UL>
     * </P>
     */
    PHOTO_GET_GROUP_INFO("PhotoGetGroupInfo"),

    /**
     * A scope to get allowed friends bidirectional information of Docomo
     * phonebook.
     *
     * <P>
     * PhotoColleSDK does not have a method to use this feature.
     * </P>
     *
     * <P>
     * Current Docomo phonebook server does not adapt this feature. The
     * feature concerned this scope will be provided in future.
     * </P>
     */
    PHONEBOOK_ALLOWED_FRIENDS_BIDIRECTIONAL(
        "PhonebookAllowedFriendsBidirectional"),

    /**
     * A scope to post feed of Docomo phonebook.
     *
     * <P>
     * PhotoColleSDK does not have a method to use this feature.
     * </P>
     */
    PHONEBOOK_POST_FEED("PhonebookPostFeed"),

    /**
     * A scope to add contact of Docomo phonebook.
     *
     * <P>
     * PhotoColleSDK does not have a method to use this feature.
     * </P>
     */
    PHONEBOOK_ADD_CONTACT("PhonebookAddContact"),

    /**
     * A scope to access service folder of data management box.
     *
     * <P>
     * PhotoColleSDK does not have a method to use this feature.
     * </P>
     */
    DATABOX_ALL("DataboxAll"),

    /**
     * A scope to get user id.
     *
     * <P>
     * PhotoColleSDK does not have a method to use this feature.
     * </P>
     */
    USER_ID("userid");

    private final String label;

    private Scope(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

}
