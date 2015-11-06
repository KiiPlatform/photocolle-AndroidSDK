package com.kii.sdk.photocolle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import android.content.Context;


public class PhotoColle {

    // This field essentially should be private final field, however, to
    // integrate PhotoColleSDK into KiiCloudSDK, this field became package
    // internal.
    URL baseUrl;

    private final AuthenticationContext authenticationContext;

    /**
     * Constructor of this class.
     * @param androidContext Android context (Activity or so).
     * @param authenticationContext a context of an authentication.
     * @throws ParameterException One or more arguments are invalid.
     */
    public PhotoColle(
            Context androidContext,
            AuthenticationContext authenticationContext)
        throws ParameterException
    {
        if (androidContext == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "androidContext must not be null.");
        }
        if (authenticationContext == null) {
            throw new ParameterException(
                ParameterException.Reason.NULL_ASSIGNED,
                "authenticationContext must not be null.");
        }
        baseUrl = MiscUtils.getPhotoColleBaseURL(androidContext);
        this.authenticationContext = authenticationContext;
        MiscUtils.setupLogProperty(androidContext);
    }

    /**
     * Get AuthenticationContext object.
     *
     * @return an AuthenticationContext object.
     */
    public AuthenticationContext getAuthenticationContext() {
        return this.authenticationContext;
    }

    /**
     * Get the list of content information.
     *
     * Do not call this method on the main thread.
     *
     * @param fileType The file type to select contents. This API does not
     * support {@link FileType#ALL ALL}.
     * @param forDustbox The flag to select contents from dust box or not.
     * @param dateFilter The filter of minimum date time. Contents are
     * selected if contents's date time is equal or greater than this
     * value. This parameter is optional. {@link ModifiedDateFilter} or
     * {@link UploadDateFilter} can be used as this parameter.
     * @param maxResults The maximum of results in list. This parameter is
     * optional. If this parameter is null, then 100 is used.
     * Domain of this parameter is between 1 and 100.
     * Otherwise, this method throws an exception.
     * @param start The start index of selected results.
     * This parameter is optional. If this parameter is null, then 1 is used.
     * Domain of this parameter is equal or greater than 1.
     * Otherwise, this method throws an exception.
     * @param sortType Type of sort. This method can not use
     * {@link SortType#SCORE_DESC}.
     * @return The list of target content information.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public ListResponse<ContentInfo> getContentIDList(
            FileType fileType,
            boolean forDustbox,
            DateFilter dateFilter,
            Integer maxResults,
            Integer start,
            SortType sortType)
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetContentIDListCommand command = new GetContentIDListCommand(
                baseUrl);
        try {
            return command.execute(new GetContentIDListCommand.Args(
                        authenticationContext, fileType, forDustbox,
                        dateFilter, maxResults, start, sortType));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the list of content information with tags.
     *
     * Do not call this method on the main thread.
     *
     * @param projectionType The type of projection rule. If projectionType
     * is {@link ProjectionType#FILE_COUNT FILE_COUNT}, then fileType is only
     * meaningful parameter. Other parameters are ignored.
     * @param fileType The file type to select contents.
     * @param criteriaList The tags to select contents. This parameter is
     * optional. This list can have five tags at the maximum. If this list
     * has more than five tags, then this method throws an exception.
     * @param forDustbox The flag to select contents from dust box or not.
     * @param dateFilter The filter of minimum date time. Contents are
     * selected if content's date time is equal or greater than this
     * value. This parameter is optional. {@link ModifiedDateFilter} or
     * {@link UploadDateFilter} can be used as this parameter.
     * @param maxResults The maximum of results in list. This parameter is
     * optional. If this parameter is null, then 1000 is used.
     * Domain of this parameter is between 1 and 1000.
     * Otherwise, this method throws an exception.
     * @param start The start index of selected results.
     * This parameter is optional. If this parameter is null, then 1 is used.
     * Domain of this parameter is equal or greater than 1.
     * Otherwise, this method throws an exception.
     * @param sortType Type of sort.
     * @return The list of target content information.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public ListResponse<DetailedContentInfo> getContentIDListWithTags(
            ProjectionType projectionType,
            FileType fileType,
            List<TagHead> criteriaList,
            boolean forDustbox,
            DateFilter dateFilter,
            Integer maxResults,
            Integer start,
            SortType sortType)
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetContentIDListWithTagsCommand command =
                new GetContentIDListWithTagsCommand(baseUrl);
        try {
            return command.execute(new GetContentIDListWithTagsCommand.Args(
                        authenticationContext, projectionType, fileType,
                        criteriaList, forDustbox, dateFilter, maxResults,
                        start, sortType));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the list of tag information.
     *
     * Do not call this method on the main thread.
     *
     * @param category The category to select tags.
     * @param fileType The file type of content to select tags. This parameter
     * is optional.
     * @param minDateModified The filter of minimum modified date time.
     * Tags are selected if tag's modified date time is equal or greater than
     * this value. This parameter is optional.
     * @return The list of tag information.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public ListResponse<Tag> getTagIDList(
            Category category,
            FileType fileType,
            Date minDateModified)
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetTagIDListCommand command = new GetTagIDListCommand(baseUrl);
        try {
            return command.execute(new GetTagIDListCommand.Args(
                        authenticationContext, category, fileType,
                        minDateModified));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the list of deleted content information.
     *
     * Do not call this method on the main thread.
     *
     * @param fileType The file type to select contents. This API does not
     * support {@link FileType#ALL ALL}.
     * @param minDateDeleted The filter of minimum deleted date time.
     * Contents are selected if content's deleted date time is equal or
     * greater than this value. This parameter is optional.
     * @param maxResults The maximum of results in list. This parameter is
     * optional. If this parameter is null, then 100 is used.
     * Domain of this parameter is between 1 and 100.
     * Otherwise, this method throws an exception.
     * @param start The start index of selected results.
     * This parameter is optional. If this parameter is null, then 1 is used.
     * Domain of this parameter is equal or greater than 1.
     * Otherwise, this method throws an exception.
     * @return The list of deleted content information.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public ListResponse<ContentGUID> getContentDeletionHistory(
            FileType fileType,
            Date minDateDeleted,
            Integer maxResults,
            Integer start)
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetContentDeletionHistoryCommand command =
                new GetContentDeletionHistoryCommand(baseUrl);
        try {
            return command.execute(new GetContentDeletionHistoryCommand.Args(
                        authenticationContext, fileType, minDateDeleted,
                        maxResults, start));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    /**
     * Get content body data.
     *
     * Do not call this method on the main thread.
     *
     * @param fileType The file type for selecting content. This API does not
     * support {@link FileType#ALL ALL}.
     * @param contentGUID guid of selecting content.
     * @param resizeType The resize type. if ResizeType.RESIZED_IMAGE or
     * ResizeType.RESIZED_VIDEO is selected, then returned content is resized.
     * resizeType is restricted by fileType. Detail of the restrictions are
     * shown by following table:
     * <table border="1">
     *   <tr>
     *     <td>{@link FileType}</td>
     *     <td>Allowed {@link ResizeType}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link FileType#IMAGE IMAGE}</td>
     *     <td>{@link ResizeType#ORIGINAL ORIGINAL}, {@link
     *         ResizeType#RESIZED_IMAGE RESIZED_IMAGE}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link FileType#SLIDE_MOVIE SLIDE_MOVIE}</td>
     *     <td>{@link ResizeType#ORIGINAL ORIGINAL}, {@link
     *         ResizeType#RESIZED_IMAGE RESIZED_IMAGE}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link FileType#VIDEO VIDEO}</td>
     *     <td>{@link ResizeType#ORIGINAL ORIGINAL}, {@link
     *         ResizeType#RESIZED_IMAGE RESIZED_IMAGE}, {@link
     *         ResizeType#RESIZED_VIDEO RESIZED_VIDEO}</td>
     *   </tr>
     * </table>
     * @return Content body data.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public ContentBodyInfo getContentBodyInfo(
            FileType fileType,
            ContentGUID contentGUID,
            ResizeType resizeType)
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetContentBodyInfoCommand command = new GetContentBodyInfoCommand(
                baseUrl);
        try {
            return command.execute(new GetContentBodyInfoCommand.Args(
                        authenticationContext, fileType, contentGUID,
                        resizeType));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    /**
     * Get thumbnails.
     *
     * Do not call this method on the main thread.
     *
     * @param contentGUIDs The guids to select thumbnails. This parameter has
     * 30 guids at the maximum.
     * @return A list of thumbnails.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public ContentThumbnailInfoList getContentThumbnailInfo(
            ContentGUID... contentGUIDs)
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetContentThumbnailInfoCommand command =
                new GetContentThumbnailInfoCommand(baseUrl);
        try {
            return command.execute(new GetContentThumbnailInfoCommand.Args(
                        authenticationContext, contentGUIDs));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    /**
     * Upload a content body data.
     *
     * Do not call this method on the main thread.
     *
     * @param fileType The file type of this content. This method can not use
     * {@link FileType#SLIDE_MOVIE}.
     * @param fileName The file name with extension of this content.
     * @param size The data size of this content. If fileType equals IMAGE,
     * the maximum of size is 30MB. If fileType equals VIDEO, the maximum of
     * size is 100MB.
     * @param mimeType The mimeType of this content.
     * mimeType is restricted by fileType. Detail of the restrictions are
     * shown by following table:
     * <table border="1">
     *   <tr>
     *     <td>{@link FileType}</td>
     *     <td>Allowed {@link MimeType}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link FileType#IMAGE IMAGE}</td>
     *     <td>{@link MimeType#JPEG JPEG}, {@link MimeType#PJPEG PJPEG}</td>
     *   </tr>
     *   <tr>
     *     <td>{@link FileType#VIDEO VIDEO}</td>
     *     <td>{@link MimeType#THREE_GP THREE_GP}, {@link MimeType#AVI AVI},
     *       {@link MimeType#QUICKTIME QUICKTIME}, {@link MimeType#MP4 MP4},
     *       {@link MimeType#VND_MTS VND_MTS}, {@link MimeType#MPEG MPEG}</td>
     *   </tr>
     * </table>
     * @param bodyStream Data to upload. If fileType equals IMAGE,
     * the maximum of data size is 30MB. If fileType equals VIDEO,
     * the maximum of data size is 100MB. (Same as size parameter)
     * @return ID of this uploaded content. This ID is assigned by server.
     * @throws UploadException a sending request is failed to execute on a
     * server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public DataID uploadContentBody(
            FileType fileType,
            String fileName,
            long size,
            MimeType mimeType,
            InputStream bodyStream)
        throws UploadException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        UploadContentBodyCommand command = new UploadContentBodyCommand(
                baseUrl);
        try {
            return command.execute(new UploadContentBodyCommand.Args(
                        authenticationContext, fileType, fileName, size,
                        mimeType, bodyStream));
        } catch (ApplicationLayerException e) {
            // ApplicationLayerException is not thrown in uploadContentBody.
            throw new RuntimeException(e);
        } finally {
            MiscUtils.closeQuietly(bodyStream);
        }
    }

    /**
     * Confirm maximum space and free space of this user.
     *
     * Do not call this method on the main thread.
     *
     * @return Maximum space and free space of this user. maximum space can be
     * negative if server does not response maximum space.
     * @throws ApplicationLayerException a sending request is failed to
     * execute on a server.
     * @throws ConnectionException network IO error occurred.
     * @throws HttpException received unexpected status code in HTTP session.
     * @throws ParameterException parameter is invalid.
     * @throws ResponseBodyParseException this method received unexpected
     * response body data from server
     * @throws InvalidTokenException token is expired.
     */
    public CapacityInfo getCapacityInfo()
        throws ApplicationLayerException,
            ConnectionException, HttpException, ParameterException,
            ResponseBodyParseException, InvalidTokenException
    {
        refreshToken();
        GetCapacityInfoCommand command = new GetCapacityInfoCommand(
                baseUrl);
        try {
            return command.execute(new GetCapacityInfoCommand.Args(
                        authenticationContext));
        } catch (UploadException e) {
            // UploadException is thrown in uploadContentBody only.
            throw new RuntimeException(e);
        }
    }

    private void refreshToken()
        throws ConnectionException, HttpException, InvalidTokenException
    {
        try {
            Authority.tryToRefreshToken(this.authenticationContext);
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

}
