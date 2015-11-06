package com.kii.example.photocolle.integrity;

class TestUtil {
    public static void waitForUploadIndexed() throws Exception {
        Thread.sleep(15000);
    }

    public static void waitForUseNextNonce() throws Exception {
        Thread.sleep(1);
    }
}
