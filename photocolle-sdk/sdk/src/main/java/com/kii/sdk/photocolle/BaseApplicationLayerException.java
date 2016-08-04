package com.kii.sdk.photocolle;

/**
 * Base class for application layer exception.
 */
public abstract class BaseApplicationLayerException
    extends PhotocolleException
{

    private static final long serialVersionUID = 1L;

    /**
     * Error code returned from server.
     */
    public static enum ErrorCode {
        /**
         * Something wrong with a parameter or parameters which a client sent.
         *
         * <P>
         * You need to fix parameters to retry the operation.
         * This error code is notified by following methods:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getContentIDList getContentIDList}</LI>
         *  <LI>{@link PhotoColle#getContentIDListWithTags
         * getContentIDListWithTags}</LI>
         *  <LI>{@link PhotoColle#getTagIDList getTagIDList}</LI>
         *  <LI>{@link PhotoColle#getContentDeletionHistory
         * getContentDeletionHistory}</LI>
         *  <LI>{@link PhotoColle#getContentBodyInfo
         * getContentBodyInfo}</LI>
         *  <LI>{@link PhotoColle#getContentThumbnailInfo
         * getContentThumbnailInfo}</LI>
         * </UL>
         */
        PARAMETER_ERROR(100, false),

        /**
         * There is no targets to retrieve.
         * <P>
         * You can not retry the operation.
         * This error code is notified by following methods:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getContentBodyInfo
         * getContentBodyInfo}</LI>
         *  <LI>{@link PhotoColle#getContentThumbnailInfo
         * getContentThumbnailInfo}</LI>
         * </UL>
         */
        TARGET_NOT_FOUND(101, false),

        /**
         * Server process timeout. retry later.
         *
         * <P>
         * This error code is notified by following methods:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getContentIDList getContentIDList}</LI>
         *  <LI>{@link PhotoColle#getContentIDListWithTags
         * getContentIDListWithTags}</LI>
         *  <LI>{@link PhotoColle#getTagIDList getTagIDList}</LI>
         *  <LI>{@link PhotoColle#getContentDeletionHistory
         * getContentDeletionHistory}</LI>
         *  <LI>{@link PhotoColle#getContentBodyInfo
         * getContentBodyInfo}</LI>
         *  <LI>{@link PhotoColle#getContentThumbnailInfo
         * getContentThumbnailInfo}</LI>
         * </UL>
         */
        TIMEOUT(110, true),

        /**
         * Search result is empty.
         *
         * <P>
         * There is no result matched to query which client sent.
         * This error code is notified by following methods:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getContentIDList getContentIDList}</LI>
         *  <LI>{@link PhotoColle#getContentIDListWithTags
         * getContentIDListWithTags}</LI>
         *  <LI>{@link PhotoColle#getTagIDList getTagIDList}</LI>
         * </UL>
         */
        NO_RESULTS(113, false),

        /**
         * Server process failed because of server internal error.
         *
         * <P>
         * You can not retry the operation. Please contact service provider.
         * This error code is notified by following methods:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getContentIDList getContentIDList}</LI>
         *  <LI>{@link PhotoColle#getContentIDListWithTags
         * getContentIDListWithTags}</LI>
         *  <LI>{@link PhotoColle#getTagIDList getTagIDList}</LI>
         *  <LI>{@link PhotoColle#getContentDeletionHistory
         * getContentDeletionHistory}</LI>
         *  <LI>{@link PhotoColle#getContentBodyInfo
         * getContentBodyInfo}</LI>
         *  <LI>{@link PhotoColle#getContentThumbnailInfo
         * getContentThumbnailInfo}</LI>
         * </UL>
         */
        SERVER_ERROR(900, false),

        /**
         * Contents are duplicated.
         *
         * <P>
         * An uploading content is duplicated to other content in server. If
         * you want to upload the content in client, you need to remove a
         * duplicated content in server by other application.
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#uploadContentBody uploadContentBody}</LI>
         * </UL>
         */
        CONTENTS_DUPLICATED(1101, false),

        /**
         * Capacity of a user is over.
         *
         * <P>
         * Server reject an uploaded content because of capacity over.
         * You need to remove conentes in server to upload new contents by
         * other application.
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#uploadContentBody uploadContentBody}</LI>
         * </UL>
         */
        CAPACITY_OVER(1102, false),

        /**
         * Fail to get free space of a user.
         *
         * <P>
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getCapacityInfo getCapacityInfo}</LI>
         * </UL>
         */
        FAIL_TO_GET_FREE_SPACE(1103, true),

        /**
         * Fail to get maximum space of a user.
         *
         * <P>
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#getCapacityInfo getCapacityInfo}</LI>
         * </UL>
         */
        FAIL_TO_GET_MAXIMUM_SPACE(1104, true),

        /** Contents not found. */
        CONTENTS_NOT_FOUND(1105, false),

        /**
         * Mandatory parameter is missed.
         *
         * <P>
         * If you want to retry, check parameter.
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#uploadContentBody uploadContentBody}</LI>
         * </UL>
         */
        MANDATORY_PARAMETER_MISSED(1110, false),

        /**
         * Parameters sent by a client size is unmatched.
         *
         * <P>
         * If you want to retry, check parameter.
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#uploadContentBody uploadContentBody}</LI>
         * </UL>
         */
        PARAMETER_SIZE_UNMATCHED(1111, false),

        /**
         * Type of parameters are unmateched.
         *
         * <P>
         * If you want to retry, check parameter.
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#uploadContentBody uploadContentBody}</LI>
         * </UL>
         */
        PARAMETER_TYPE_UNMATCHED(1112, false),

        /**
         *
         * Value of parameters are invalid.
         *
         * <P>
         * If you want to retry, check parameter.
         * This error code is notified by a following method:
         * </P>
         * <UL>
         *  <LI>{@link PhotoColle#uploadContentBody uploadContentBody}</LI>
         * </UL>
         */
        PARAMETER_VALUE_INVALID(1113, false);

        private static ErrorCode[] errorCodeArray = null;

        private final int code;
        private final boolean retryable;

        private ErrorCode(int code, boolean retryable) {
            this.code = code;
            this.retryable = retryable;
        }

        /**
         * Error code defined by PhotoColle service.
         *
         * @return the code
         */
        public int getCode() {
            return this.code;
        }

        /**
         * Retryable flag. if true you can retry with same parameters.
         *
         * @return the retryable
         */
        public boolean isRetryable() {
            return this.retryable;
        }

        static ErrorCode toErrorCode(int code) {
            if (errorCodeArray == null) {
                errorCodeArray = ErrorCode.values();
            }
            for (ErrorCode retval :errorCodeArray) {
                if (code == retval.getCode()) {
                    return retval;
                }
            }
            // If This is programming error.
            throw new ParameterException(
                    ParameterException.Reason.OUT_OF_RANGE,
                    String.format("unexpected error code:%d", code));
        }
    }

    BaseApplicationLayerException() {
        super((Throwable)null);
    }

}
