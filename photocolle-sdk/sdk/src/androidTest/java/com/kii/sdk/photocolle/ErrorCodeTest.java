package com.kii.sdk.photocolle;

import com.kii.sdk.photocolle.BaseApplicationLayerException.ErrorCode;

import android.test.AndroidTestCase;

public class ErrorCodeTest extends AndroidTestCase {

    public void testParameterError() throws Exception {
        assertEquals(ErrorCode.PARAMETER_ERROR, ErrorCode.toErrorCode(100));
    }

    public void testTargetNotFound() throws Exception {
        assertEquals(ErrorCode.TARGET_NOT_FOUND, ErrorCode.toErrorCode(101));
    }

    public void testTimeout() throws Exception {
        assertEquals(ErrorCode.TIMEOUT, ErrorCode.toErrorCode(110));
    }

    public void testNoResults() throws Exception {
        assertEquals(ErrorCode.NO_RESULTS, ErrorCode.toErrorCode(113));
    }

    public void testContentsDuplicate() throws Exception {
        assertEquals(ErrorCode.CONTENTS_DUPLICATED,
                ErrorCode.toErrorCode(1101));
    }

    public void testCapacityOver() throws Exception {
        assertEquals(ErrorCode.CAPACITY_OVER, ErrorCode.toErrorCode(1102));
    }

    public void testFailToGetFreeSpace() throws Exception {
        assertEquals(ErrorCode.FAIL_TO_GET_FREE_SPACE,
                ErrorCode.toErrorCode(1103));
    }

    public void testFailToGetMaximumSpace() throws Exception {
        assertEquals(ErrorCode.FAIL_TO_GET_MAXIMUM_SPACE,
                ErrorCode.toErrorCode(1104));
    }

    public void testContentsNotFound() throws Exception {
        assertEquals(ErrorCode.CONTENTS_NOT_FOUND,
                ErrorCode.toErrorCode(1105));
    }

    public void testMandatoryParameterMissed() throws Exception {
        assertEquals(ErrorCode.MANDATORY_PARAMETER_MISSED,
                ErrorCode.toErrorCode(1110));
    }

    public void testParameterSizeUnmatched() throws Exception {
        assertEquals(ErrorCode.PARAMETER_SIZE_UNMATCHED,
                ErrorCode.toErrorCode(1111));
    }

    public void testParameterTypeUnmatched() throws Exception {
        assertEquals(ErrorCode.PARAMETER_TYPE_UNMATCHED,
                ErrorCode.toErrorCode(1112));
    }

    public void testParameterValueInvalid() throws Exception {
        assertEquals(ErrorCode.PARAMETER_VALUE_INVALID,
                ErrorCode.toErrorCode(1113));
    }

    public void testNewApplicationLayerExceptionFail() throws Exception {
        ErrorCode code = null;
        Exception exception = null;
        try {
            code = ErrorCode.toErrorCode(0);
        } catch (Exception e) {
            exception = e;
        }
        assertNull(code);
        assertNotNull(exception);
        assertEquals(ParameterException.class, exception.getClass());
        ParameterException paramException = (ParameterException)exception;
        assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                paramException.getReason());
    }
}
