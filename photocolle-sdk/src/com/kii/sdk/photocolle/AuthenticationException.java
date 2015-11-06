package com.kii.sdk.photocolle;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Thrown when authentication is failed.
 */
public class AuthenticationException extends PhotocolleException {

    /**
     * Error reason returned from server.
     */
    public enum Reason {

        /**
         * The request is missing a required parameter, includes an invalid
         * parameter value, includes a parameter more than once, or is
         * otherwise malformed.
         */
        INVALID_REQUEST("invalid_request"),

        /**
         * The authorization server does not support requested response type.
         */
        UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),

        /**
         * The authorization server encountered an unexpected condition that
         * prevented it from fulfilling the request.
         */
        SERVER_ERROR("server_error"),

        /**
         * The provided authorization grant (e.g., authorization code,
         * resource owner credentials) or refresh token is invalid, expired,
         * revoked, does not match the redirection URI used in the
         * authorization request, or was issued to another client.
         */
        INVALID_GRANT("invalid_grant"),

        /**
         * Client authentication failed (e.g., unknown client, no client
         * authentication included, or unsupported authentication method).
         */
        INVALID_CLIENT("invalid_client"),

        /**
         * The client is not authorized to request an authorization code
         * using this method.
         */
        UNAUTHORIZED_CLIENT("unauthorized_client"),

        /**
         * The authorization grant type is not supported by the authorization
         * server.
         */
        UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),

        /**
         * The requested scope is invalid, unknown, or malformed.
         */
        INVALID_SCOPE("invalid_scope"),

        /**
         * The authorization server is currently unable to handle the request
         * due to a temporary overloading or maintenance of the server.
         */
        TEMPORARILY_UNAVAILABLE("temporarily_unavailable");

        private static Reason[] reasons = null;

        private final String label;

        private Reason(String label) {
            this.label = label;
        }

        /**
         * @return the label
         */
        public String getLabel() {
            return this.label;
        }

        static Reason toReason(String label) {
            if (reasons == null) {
                reasons = Reason.values();
            }
            for (Reason retval : reasons) {
                if (label.equals(retval.getLabel())) {
                    return retval;
                }
            }
            // If This is programming error.
            throw new IllegalArgumentException(
                String.format("unexpected reason:%s", label));
        }
    }

    private static final long serialVersionUID = 1L;

    private final Reason reason;
    private final Map<String, String> detail;

    AuthenticationException(Reason reason, Map<String, String> detail) {
        super(createMessage(reason, detail));
        this.reason = reason;
        this.detail = detail;
    }

    private static String createMessage(
            Reason reason,
            Map<String, String> detail)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("className", "AuthenticationException");
            json.put("reason", reason.label);
            if (detail != null) {
                json.put("detail", new JSONObject(detail));
            }
            return json.toString(4);
        } catch (JSONException e) {
            // Ignore JSON exception.
        }
        return null;
    }

    /**
     * Error reason returned from server.
     *
     * @return the reason
     */
    public Reason getReason() {
        return this.reason;
    }

    /**
     * Detail of error return from server.
     *
     * @return the detail
     */
    public Map<String, String> getDetail() {
        return this.detail;
    }

}
