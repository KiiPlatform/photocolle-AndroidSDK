package com.kii.sdk.photocolle;

import android.test.AndroidTestCase;

public class TestCaseBase extends AndroidTestCase {
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().toString());
    }
}
