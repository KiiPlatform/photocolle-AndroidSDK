package com.kii.sdk.photocolle;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Thrown when PhotoColleSDK received unexpected status code in HTTP session.
 */
public class HttpException extends PhotocolleException {

    private static final long serialVersionUID = 1L;

    private final int statusCode;
    private final String reasonPhrase;
    private final String responseBody;

    HttpException(int statusCode, String reasonPhrase, String responseBody) {
        super(createMessage(statusCode, reasonPhrase, responseBody));
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.responseBody = responseBody;
    }

    private static String createMessage(
            int statusCode,
            String reasonPhase,
            String responseBody)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("className", "HttpException");
            json.put("statusCode", statusCode);
            json.put("reasonPhase", reasonPhase);
            json.put("responseBody", responseBody);
            return json.toString(4);
        } catch (JSONException e) {
            // Ignore JSON exception.
        }
        return null;
    }

    /**
     * Represents status code as returned from HTTP server. See <a
     * href="http://www.ietf.org/rfc/rfc2616.txt">RFC2616</a> section 6.1.1.
     *
     * @return the status code of HTTP session.
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * Represents reason phrase as returned from HTTP server. See <a
     * href="http://www.ietf.org/rfc/rfc2616.txt">RFC2616</a> section 6.1.1.
     *
     * @return the response phrase of HTTP Session.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * Represents response body sent from server.
     *
     * @return the error message from server.
     */
    public String getResponseBody() {
        return this.responseBody;
    }

}
