package com.kii.sdk.photocolle;

import java.util.ArrayList;
import java.util.List;

/**
 * Thrown when a uploading request sending by PhotoColleSDK was failed to
 * execute on a server.
 */
public class UploadException extends BaseApplicationLayerException {

    public static class ErrorItem {
        private String item = null;
        private ErrorCode errorCode = null;

        ErrorItem(String item, ErrorCode errorCode) {
            this.item = item;
            this.errorCode = errorCode;
        }

        /**
         * Error item name.
         *
         * @return the error item name.
         */
        public String getItem() {
            return this.item;
        }

        /**
         * Error code.
         *
         * @return the errorCode
         */
        public ErrorCode getErrorCode() {
            return this.errorCode;
        }

    }

    private static final long serialVersionUID = 1L;

    private final List<ErrorItem> errorItems = new ArrayList<ErrorItem>();

    UploadException(List<ErrorItem> errorItems) {
        super();
        if (errorItems != null) {
            this.errorItems.addAll(errorItems);
        }
    }

    /**
     * A list of error items.
     *
     * @return the errorItems
     */
    public List<ErrorItem> getErrorItems() {
        return this.errorItems;
    }

}
