package com.kii.sdk.photocolle;

/**
 * Thrown when a request sending by PhotoColleSDK was failed to execute on a
 * server.
 */
public class ApplicationLayerException extends BaseApplicationLayerException {

    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    private String paramName;
    private String paramValue;

    ApplicationLayerException(
            int errorCode,
            String paramName,
            String paramValue)
    {
        super();
        this.errorCode = ErrorCode.toErrorCode(errorCode);
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    /**
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    /**
     * @return the paramName
     */
    public String getParamName() {
        return this.paramName;
    }

    /**
     * @return the paramValue
     */
    public String getParamValue() {
        return this.paramValue;
    }

}
