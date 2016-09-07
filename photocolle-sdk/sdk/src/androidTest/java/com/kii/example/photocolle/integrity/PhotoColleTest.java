package com.kii.example.photocolle.integrity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.CapacityInfo;
import com.kii.sdk.photocolle.Category;
import com.kii.sdk.photocolle.ContentBodyInfo;
import com.kii.sdk.photocolle.ContentGUID;
import com.kii.sdk.photocolle.ContentInfo;
import com.kii.sdk.photocolle.ContentThumbnailInfo;
import com.kii.sdk.photocolle.ContentThumbnailInfoList;
import com.kii.sdk.photocolle.DataID;
import com.kii.sdk.photocolle.DetailedContentInfo;
import com.kii.sdk.photocolle.FileType;
import com.kii.sdk.photocolle.ListResponse;
import com.kii.sdk.photocolle.MimeType;
import com.kii.sdk.photocolle.ModifiedDateFilter;
import com.kii.sdk.photocolle.PersonTag;
import com.kii.sdk.photocolle.PhotoColle;
import com.kii.sdk.photocolle.ProjectionType;
import com.kii.sdk.photocolle.ResizeType;
import com.kii.sdk.photocolle.SortType;
import com.kii.sdk.photocolle.Tag;
import com.kii.sdk.photocolle.TagHead;
import com.kii.sdk.photocolle.TagType;
import com.kii.sdk.photocolle.UploadDateFilter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.test.AndroidTestCase;

public class PhotoColleTest extends AndroidTestCase {

    protected void setUp() throws Exception {
        super.setUp();

        Thread.sleep(1000);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUploadContentBodyJPEG() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        AssetFileDescriptor file = getFileFromTestDataDirectory("test1.jpg");
        DataID dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "test title.",
                file.getLength(),
                MimeType.JPEG,
                file.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());
    }

    private AssetFileDescriptor getFileFromTestDataDirectory(
            String filename) throws IOException {
        return getContext().getResources().getAssets().openFd(filename);
    }

    // test**Simple method is leaving arguments out, if it is able to.
    // test**Complex method is no leaving arguments out.

    public void testGetContentIDListSimple() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        ListResponse<ContentInfo> responses = photoColle.getContentIDList(
                FileType.IMAGE, false, null, null, null,
                SortType.CREATION_DATETIME_ASC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertTrue(responses.getNextPage() >= 0);
        assertEquals(responses.getStart(), 1);
        for (ContentInfo info : responses.getList()) {
            assertNotNull(info);
            assertNotNull(info.getGuid());
            assertNotNull(info.getName());
            assertEquals(info.getFileType(), FileType.IMAGE);
            assertNotNull(info.getExifCameraDate());
            assertNotNull(info.getModifiedDate());
            assertNotNull(info.getUploadedDate());
            assertTrue(info.getFileDataSize() > 0);
        }
    }

    public void testGetContentIDListComplex() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        UploadDateFilter filter = new UploadDateFilter(new Date(0));
        ListResponse<ContentInfo> responses = photoColle.getContentIDList(
                FileType.IMAGE, false, filter, Integer.valueOf(10),
                Integer.valueOf(5), SortType.CREATION_DATETIME_ASC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertTrue(responses.getNextPage() >= 0);
        assertEquals(responses.getStart(), 5);
        for (ContentInfo info : responses.getList()) {
            assertNotNull(info);
            assertNotNull(info.getGuid());
            assertNotNull(info.getName());
            assertEquals(info.getFileType(), FileType.IMAGE);
            assertNotNull(info.getExifCameraDate());
            assertNotNull(info.getModifiedDate());
            assertNotNull(info.getUploadedDate());
            assertTrue(info.getFileDataSize() > 0);
        }
    }

    public void testGetContentIDListWithTagSimple() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        ListResponse<DetailedContentInfo> responses =
                photoColle.getContentIDListWithTags(
                        ProjectionType.ALL_DETAILS, FileType.IMAGE,
                        null, false, null, null, null,
                        SortType.CREATION_DATETIME_ASC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertTrue(responses.getNextPage() >= 0);
        assertEquals(responses.getStart(), 1);
        for (DetailedContentInfo info : responses.getList()) {
            assertNotNull(info);
            assertNotNull(info.getGuid());
            assertNotNull(info.getName());
            assertEquals(info.getFileType(), FileType.IMAGE);
            assertNotNull(info.getExifCameraDate());
            assertNotNull(info.getModifiedDate());
            assertNotNull(info.getUploadedDate());
            assertTrue(info.getFileDataSize() > 0);
            assertNotNull(info.getScore());
            assertNotNull(info.getOrientation());
            assertNotNull(info.getPersons());
            assertNotNull(info.getEvents());
            assertNotNull(info.getFavorites());
            assertNotNull(info.getPlaces());
            assertNotNull(info.getYearMonths());
        }
    }

    public void testGetContentIDListWithTagComplex() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        // TODO: create criteria tag list from getTagIDList server response.
        // NOTE: now only get year-month tag.
        List<TagHead> criteria = null;
        ModifiedDateFilter filter = new ModifiedDateFilter(new Date(0));
        ListResponse<DetailedContentInfo> responses =
                photoColle.getContentIDListWithTags(
                        ProjectionType.ALL_DETAILS, FileType.IMAGE,
                        criteria, false, filter, Integer.valueOf(15),
                        Integer.valueOf(3), SortType.CREATION_DATETIME_ASC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertTrue(responses.getNextPage() >= 0);
        assertEquals(responses.getStart(), 3);
        for (DetailedContentInfo info : responses.getList()) {
            assertNotNull(info);
            assertNotNull(info.getGuid());
            assertNotNull(info.getName());
            assertEquals(info.getFileType(), FileType.IMAGE);
            assertNotNull(info.getExifCameraDate());
            assertNotNull(info.getModifiedDate());
            assertNotNull(info.getUploadedDate());
            assertTrue(info.getFileDataSize() > 0);
            assertNotNull(info.getScore());
            assertNotNull(info.getOrientation());
            assertNotNull(info.getPersons());
            assertNotNull(info.getEvents());
            assertNotNull(info.getFavorites());
            assertNotNull(info.getPlaces());
            assertNotNull(info.getYearMonths());
        }
    }

    public void testGetTagIDListSimple() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        ListResponse<Tag> responses =
                photoColle.getTagIDList(Category.ALL, null, null);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertEquals(responses.getNextPage(), 0);
        assertEquals(responses.getStart(), -1);
        for (Tag tag : responses.getList()) {
            assertNotNull(tag);
            assertNotNull(tag.getTagType());
            assertNotNull(tag.getTagGUID());
            assertNotNull(tag.getName());
            assertTrue(tag.getContentsCount() >= 0);
            if (tag.getTagType() == TagType.PERSON) {
                assertNotNull(((PersonTag)tag).getBirthDate());
            }
        }
    }

    public void testGetTagIDListComplex() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        ListResponse<Tag> responses =
                photoColle.getTagIDList(Category.ALL, null, new Date(0));
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertEquals(responses.getNextPage(), 0);
        assertEquals(responses.getStart(), -1);
        for (Tag tag : responses.getList()) {
            assertNotNull(tag);
            assertNotNull(tag.getTagType());
            assertNotNull(tag.getTagGUID());
            assertNotNull(tag.getName());
            assertTrue(tag.getContentsCount() >= 0);
            if (tag.getTagType() == TagType.PERSON) {
                assertNotNull(((PersonTag)tag).getBirthDate());
            }
        }
    }

    public void testGetContentDeletionHitorySimple() throws Exception {
        // TODO: Make deletion history on server.
        PhotoColle photoColle = getPhotoColle();
        ListResponse<ContentGUID> responses =
                photoColle.getContentDeletionHistory(FileType.IMAGE, null,
                        null, null);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertTrue(responses.getNextPage() >= 0);
        assertEquals(responses.getStart(), 1);
        for (ContentGUID guid : responses.getList()) {
            assertNotNull(guid);
            assertNotNull(guid.getString());
        }
    }

    public void testGetContentDeletionHitoryComplex() throws Exception {
        // TODO: Make deletion history on server.
        PhotoColle photoColle = getPhotoColle();
        ListResponse<ContentGUID> responses =
                photoColle.getContentDeletionHistory(FileType.IMAGE, new Date(0),
                        Integer.valueOf(50), Integer.valueOf(13));
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 0);
        assertTrue(responses.getNextPage() >= 0);
        assertEquals(responses.getStart(), 13);
        for (ContentGUID guid : responses.getList()) {
            assertNotNull(guid);
            assertNotNull(guid.getString());
        }
    }

    public void testGetContentBodyInfoOriginal() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        AssetFileDescriptor file = getFileFromTestDataDirectory("body.jpg");
        DataID dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "body.jpg",
                file.getLength(),
                MimeType.JPEG,
                file.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        ListResponse<ContentInfo> responses = photoColle.getContentIDList(
                FileType.IMAGE, false, null, null, null,
                SortType.UPLOAD_DATETIME_DESC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 1);
        assertNotNull(responses.getList());

        TestUtil.waitForUseNextNonce();

        ContentGUID guid = responses.getList().get(0).getGuid();
        assertNotNull(guid);
        ContentBodyInfo info = photoColle.getContentBodyInfo(FileType.IMAGE,
                guid, ResizeType.ORIGINAL);
        assertNotNull(info);
        assertNotNull(info.getContentType());
        assertEquals(info.getContentType().getLabel(), MimeType.JPEG.getLabel());
        assertNotNull(info.getInputStream());
    }

    public void testGetContentBodyInfoResize() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        AssetFileDescriptor file = getFileFromTestDataDirectory("body.jpg");
        DataID dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "body2.jpg",
                file.getLength(),
                MimeType.JPEG,
                file.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        ListResponse<ContentInfo> responses = photoColle.getContentIDList(
                FileType.IMAGE, false, null, null, null,
                SortType.UPLOAD_DATETIME_DESC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 1);
        assertNotNull(responses.getList());

        TestUtil.waitForUseNextNonce();

        ContentGUID guid = responses.getList().get(0).getGuid();
        assertNotNull(guid);
        ContentBodyInfo info = photoColle.getContentBodyInfo(FileType.IMAGE,
                guid, ResizeType.RESIZED_IMAGE);
        assertNotNull(info);
        assertNotNull(info.getContentType());
        assertEquals(info.getContentType().getLabel(), MimeType.JPEG.getLabel());
        assertNotNull(info.getInputStream());
    }

    public void testGetContentThumbnailInfoSingle() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        AssetFileDescriptor file = getFileFromTestDataDirectory("thumb.jpg");
        DataID dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "thumb.jpg",
                file.getLength(),
                MimeType.JPEG,
                file.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        ListResponse<ContentInfo> responses = photoColle.getContentIDList(
                FileType.IMAGE, false, null, null, null,
                SortType.UPLOAD_DATETIME_DESC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 1);

        TestUtil.waitForUseNextNonce();

        ContentGUID guid = responses.getList().get(0).getGuid();
        assertNotNull(guid);
        ContentThumbnailInfoList list = photoColle.getContentThumbnailInfo(guid);
        assertNotNull(list);
        assertNotNull(list.getList());
        ContentThumbnailInfo info = list.getList().get(0);
        assertNotNull(info);
        assertEquals(info.getGUID().getString(), guid.getString());
        assertNotNull(info.getMimeType());
        assertEquals(info.getMimeType().getLabel(), MimeType.JPEG.getLabel());
        assertNotNull(info.getThumbnailBytes());
    }

    public void testGetContentThumbnailInfoPlural() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        AssetFileDescriptor file = getFileFromTestDataDirectory("thumb.jpg");
        DataID dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "thumb_1.jpg",
                file.getLength(),
                MimeType.JPEG,
                file.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        AssetFileDescriptor file2 = getFileFromTestDataDirectory("thumb.jpg");
        dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "thumb_2.jpg",
                file2.getLength(),
                MimeType.JPEG,
                file2.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        AssetFileDescriptor file3 = getFileFromTestDataDirectory("thumb.jpg");
        dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "thumb_3.jpg",
                file3.getLength(),
                MimeType.JPEG,
                file3.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        AssetFileDescriptor file4 = getFileFromTestDataDirectory("thumb.jpg");
        dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "thumb_4.jpg",
                file4.getLength(),
                MimeType.JPEG,
                file4.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        AssetFileDescriptor file5 = getFileFromTestDataDirectory("thumb.jpg");
        dataId = photoColle.uploadContentBody(
                FileType.IMAGE,
                "thumb_5.jpg",
                file5.getLength(),
                MimeType.JPEG,
                file5.createInputStream());
        assertNotNull(dataId);
        assertNotNull(dataId.getString());

        TestUtil.waitForUploadIndexed();

        ListResponse<ContentInfo> responses = photoColle.getContentIDList(
                FileType.IMAGE, false, null, null, null,
                SortType.UPLOAD_DATETIME_DESC);
        assertNotNull(responses);
        assertTrue(responses.getCount() >= 5);

        TestUtil.waitForUseNextNonce();

        List<ContentGUID> guids = new ArrayList<ContentGUID>();
        guids.add(responses.getList().get(0).getGuid());
        guids.add(responses.getList().get(1).getGuid());
        guids.add(responses.getList().get(2).getGuid());
        guids.add(responses.getList().get(3).getGuid());
        guids.add(responses.getList().get(4).getGuid());
        ContentThumbnailInfoList list = photoColle.getContentThumbnailInfo(
                guids.toArray(new ContentGUID[] {}));
        List<String> guidExpect = new ArrayList<String>();
        for (ContentGUID g : guids) {
            guidExpect.add(g.getString());
        }
        assertNotNull(list);
        assertNotNull(list.getList());
        assertTrue(list.getList().size() >= 5);
        for (int i = 0; i < 5; ++i) {
            ContentThumbnailInfo info = list.getList().get(i);
            assertNotNull(info);
            assertNotNull(info.getGUID());
            assertTrue(guidExpect.contains(info.getGUID().getString()));
            assertNotNull(info.getMimeType());
            assertEquals(info.getMimeType().getLabel(), MimeType.JPEG.getLabel());
            assertNotNull(info.getThumbnailBytes());
        }
    }

    public void testGetCapacityInfo() throws Exception {
        PhotoColle photoColle = getPhotoColle();
        CapacityInfo info = photoColle.getCapacityInfo();
        assertNotNull(info);
        assertTrue(info.getMaxSpace() >= -1);
        assertTrue(info.getFreeSpace() >= 0);
    }

    private PhotoColle getPhotoColle() throws Exception {
        Context context = getContext();
        JSONObject json = loadAuthenticationContextData();
        // make mock.
        AuthenticationContext authenticateContext =
                mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn(
            json.getString("accessToken"));
        PhotoColle retval = new PhotoColle(context, authenticateContext);
        return retval;
    }

    private JSONObject loadAuthenticationContextData() throws Exception {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                new InputStreamReader(
                    getContext().getResources().getAssets().open("auth.json")));
            StringBuilder buildr = new StringBuilder();
            String str;
            while((str = reader.readLine()) != null) {
                buildr.append(str);
            }
            return new JSONObject(buildr.toString());
        } finally {
            closeQuietly(reader);
        }
    }

    private static void closeQuietly(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
