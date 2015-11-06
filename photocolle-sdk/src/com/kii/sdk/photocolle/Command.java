package com.kii.sdk.photocolle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.util.Log;

abstract class Command<
        ARGTYPE, LOGICTYPE extends Logic<ARGTYPE, RETURNTYPE>, RETURNTYPE>
{
    private static final String TAG = "PhotoColle Debug";
    private static HttpClientFactory httpClientFactory = new HttpClientFactory();

    final LOGICTYPE logic;
    final URL baseUrl;

    public Command(LOGICTYPE logic, URL baseUrl) {
        this.logic = logic;
        this.baseUrl = baseUrl;
    }

    public final RETURNTYPE execute(ARGTYPE arg)
        throws HttpException, ApplicationLayerException, UploadException,
            ResponseBodyParseException, ConnectionException,
            InvalidTokenException
    {
        URL targetUrl = getTargetUrl();
        HttpUriRequest request = this.logic.createRequest(targetUrl, arg);
        HttpClient client = httpClientFactory.createClient();
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (ClientProtocolException e) {
            throw new ConnectionException(e);
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
        if (MiscUtils.isLoggable) {
            printDebugInfo(request, response);
        }
        return this.logic.parseResponse(response);
    }

    private URL getTargetUrl() {
        if (this.baseUrl == null) {
            return this.logic.getDefaultUrl();
        } else {
            try {
                String protocol = this.baseUrl.getProtocol();
                String host = this.baseUrl.getHost();
                int port = this.baseUrl.getPort();
                String path = concatenatePath(this.baseUrl.getPath(),
                        this.logic.getDefaultUrl().getFile());
                return port == -1 ? new URL(protocol, host, path) :
                        new URL(protocol, host, port, path);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String concatenatePath(String path1, String path2) {
        StringBuilder retval = new StringBuilder();
        assert(path1 == null && path2 == null);
        if (path1 == null) {
            return path2;
        } else if (path2 == null) {
            return path1;
        } else if (path1.endsWith("/") && path2.startsWith("/")) {
            retval.append(path1).append(path2.substring(1, path2.length()));
        } else if (path1.endsWith("/") || path2.startsWith("/")) {
            retval.append(path1).append(path2);
        } else {
            retval.append(path1).append("/").append(path2);
        }
        return retval.toString();
    }

    public static URL toUrl(String src) {
        return MiscUtils.toUrl(src);
    }

    public static void signRequest(
            HttpRequestBase request,
            AuthenticationContext context)
    {
        request.setHeader("Authorization",
                "Bearer " + context.getAccessToken());
    }

    public static HttpEntity generateJSONEntity(
            JSONObject json)
    {
        ByteArrayEntity retval = null;
        try {
            retval = new ByteArrayEntity(
                    json.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        retval.setContentType("application/json");
        return retval;
    }

    private static String headersToString(Header[] headers) {
        String br = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(br);
        for (Header h : headers) {
            builder.append(h.getName());
            builder.append(" = ");
            builder.append(h.getValue());
            builder.append(br);
        }
        builder.append("}");
        return builder.toString();
    }

    private static void printDebugInfo(
            HttpUriRequest request,
            HttpResponse response)
    {
        String br = System.getProperty("line.separator");
        StringBuffer builder = new StringBuffer();
        builder.append("URL: ");
        builder.append(request.getURI().toString());
        builder.append(br);
        builder.append("Request Headers: ");
        builder.append(headersToString(request.getAllHeaders()));
        builder.append(br);
        builder.append("Request Body: ");
        if (request instanceof HttpPost) {
            HttpPost post = (HttpPost)request;
            String body = "This body can't be outputted.";
            try {
                body = ResponseUtil.fromHttpEntityToString(
                        post.getEntity(), "UTF-8");
            } catch (ResponseBodyParseException e) {
                Log.d(TAG, "Parsing request is failed.", e);
            }
            builder.append(body);
        } else {
            builder.append("No body by Get request.");
        }
        builder.append(br);
        builder.append("HTTP Status Code: ");
        builder.append(response.getStatusLine().getStatusCode());
        builder.append(br);
        builder.append("Response Headers: ");
        builder.append(headersToString(response.getAllHeaders()));
        builder.append(br);
        Header contentType = response.getFirstHeader("Content-Type");
        if (contentType != null &&
                "application/json".equals(contentType.getValue())) {
            builder.append("Response Body: ");
            String body = "This body can't be outputted.";
            try {
                body = ResponseUtil.fromHttpEntityToString(
                    response.getEntity(), "UTF-8");
                response.setEntity(new StringEntity(body, "UTF-8"));
            } catch (ResponseBodyParseException e) {
                Log.d(TAG, "Parsing response is failed.", e);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, e.getMessage());
            }
            builder.append(body);
            builder.append(br);
        } else {
            builder.append("Response Body is binary data.");
            builder.append(br);
        }

        Log.d(TAG, builder.toString());
    }
}
