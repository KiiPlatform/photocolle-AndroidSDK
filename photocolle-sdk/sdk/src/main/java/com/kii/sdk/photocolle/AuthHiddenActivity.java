package com.kii.sdk.photocolle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Receive the consented result of authentication in this Activity.
 *
 * This activity is only referenced from our SDK. You need to write following
 * configuration in AndoridManifest.xml.
 *
 * <pre>
 * {@code
 *  <activity
 *    android:name="com.kii.sdk.photocolle.AuthHiddenActivity"
 *    android:label="API" />
 * }
 * </pre>
 */
public class AuthHiddenActivity extends Activity {

    static final String KEY_AUTH_URL = "authUrl";
    static final String KEY_TOKEN_URL = "tokenUrl";
    static final String KEY_CLIENT_ID = "clientId";
    static final String KEY_CLIENT_SECRET = "clientSecret";
    static final String KEY_SCOPES = "scopes";
    static final String KEY_REDIRECT_URI = "redirectUri";
    static final String KEY_EXCEPTION = "exception";
    static final String KEY_AUTHORIZATION_DATA = "authorizationData";
    static final String KEY_ERROR_CODE = "errorCode";
    static final String KEY_NONCE = "nonce";

    static final String ACTION_AUTH_RESULT =
            "com.kii.sdk.photocolle.action.auth.RESULT";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final ViewSwitcher viewSwitcher = new ViewSwitcher(this);
        this.addContentView(viewSwitcher, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(
            Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        viewSwitcher.addView(linearLayout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

        TextView textView = new TextView(this);
        textView.append("Connecting to authorization server...");
        linearLayout.addView(textView, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

        ProgressBar progressBar = new ProgressBar(this, null,
                android.R.attr.progressBarStyleLarge);
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.addView(progressBar, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

        WebView webView = new WebView(this);
        viewSwitcher.addView(webView, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();

        webView.getSettings().setJavaScriptEnabled(true);
        final String state = generateState();
        Intent intent = getIntent();
        final URL tokenUrl = (URL)intent.getSerializableExtra(KEY_TOKEN_URL);
        final String clientId = intent.getStringExtra(KEY_CLIENT_ID);
        final String clientSecret = intent.getStringExtra(KEY_CLIENT_SECRET);
        final String redirectUri = intent.getStringExtra(KEY_REDIRECT_URI);
        final String nonce = intent.getStringExtra(KEY_NONCE);

        webView.setWebViewClient(new WebViewClient() {
            boolean isFinished = false;

            @Override
            public void onPageFinished(WebView webView, String url) {
                Uri uri = Uri.parse(url);
                if (this.isFinished) {
                    return;
                }
                if (!isRedirectUri(Uri.parse(redirectUri), uri)) {
                    if (viewSwitcher.getCurrentView() != webView) {
                        viewSwitcher.showNext();
                    }
                    return;
                }
                this.isFinished = true;
                String code = uri.getQueryParameter("code");
                String receivedState = uri.getQueryParameter("state");
                String error = uri.getQueryParameter("error");
                if (!receivedState.equals(state)) {
                    notifyUnexpecedState(receivedState, state, nonce);
                    return;
                } else if (error != null) {
                    notifyError(error, nonce);
                    return;
                } else if (code == null) {
                    notifyNullCode(nonce);
                    return;
                }
                getAccessTokenAsync(tokenUrl, clientId, clientSecret, code,
                        redirectUri, nonce);
                viewSwitcher.showPrevious();
            }

            private boolean isRedirectUri(Uri redirectUri, Uri uri) {
                if (!redirectUri.getScheme().equals(uri.getScheme())) {
                    return false;
                } else if (!redirectUri.getHost().equals(uri.getHost())) {
                    return false;
                } else if (!redirectUri.getPath().equals(uri.getPath())) {
                    return false;
                }
                return true;
            }
        });
        final String url = generateUrl(
            ((URL)intent.getSerializableExtra(KEY_AUTH_URL)).toString(),
            clientId, redirectUri, intent.getStringArrayExtra(KEY_SCOPES),
            state);
        webView.loadUrl(url);
    }

    private void getAccessTokenAsync(
            final URL tokenUrl,
            final String clientId,
            final String clientSecret,
            final String code,
            final String redirectUrl,
            final String nonce)
    {
        (new Thread(new Runnable() {

            @Override
            public void run() {
                DefaultHttpClient client = new DefaultHttpClient();
                final Intent intent = generateIntent(nonce);
                try {
                    HttpUriRequest request = createHttpUriRequest(tokenUrl,
                            clientId, clientSecret, code, redirectUrl, nonce);
                    Bundle bundle = client.execute(request,
                            new ResponseHandler<Bundle>() {

                                @Override
                                public Bundle handleResponse(
                                        HttpResponse response)
                                    throws ClientProtocolException, IOException
                                {
                                    try {
                                        return parseResponse(response);
                                    } catch (HttpException e) {
                                        intent.putExtra(KEY_EXCEPTION, e);
                                    } catch (ParseException e) {
                                        intent.putExtra(KEY_EXCEPTION,
                                                new ResponseBodyParseException(e));
                                    } catch (JSONException e) {
                                        intent.putExtra(KEY_EXCEPTION,
                                                new ResponseBodyParseException(e));
                                    } catch (RuntimeException e) {
                                        intent.putExtra(KEY_EXCEPTION,
                                                new ResponseBodyParseException(e));
                                    }
                                    return null;
                                }
                            });
                    if (bundle != null) {
                        intent.putExtras(bundle);
                    }
                } catch (URISyntaxException e) {
                    intent.putExtra(KEY_EXCEPTION,
                            new ResponseBodyParseException(e));
                } catch (UnsupportedEncodingException e) {
                    intent.putExtra(KEY_EXCEPTION,
                            new ResponseBodyParseException(e));
                } catch (ClientProtocolException e) {
                    intent.putExtra(KEY_EXCEPTION, new ConnectionException(e));
                } catch (IOException e) {
                    intent.putExtra(KEY_EXCEPTION, new ConnectionException(e));
                } catch (RuntimeException e) {
                    intent.putExtra(KEY_EXCEPTION,
                            new ResponseBodyParseException(e));
                } finally {
                    client.getConnectionManager().shutdown();
                }
                sendBroadcast(intent);
                finish();
            }
        })).start();
    }

    private HttpUriRequest createHttpUriRequest(
            URL tokenUrl,
            String clientId,
            String clientSecret,
            String code,
            String redirectUrl,
            String nonce)
        throws URISyntaxException, UnsupportedEncodingException
    {
        HttpPost retval = new HttpPost(tokenUrl.toURI());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Collections.addAll(params,
                new BasicNameValuePair("code", code),
                new BasicNameValuePair("redirect_uri", redirectUrl),
                new BasicNameValuePair("grant_type", "authorization_code"));
        retval.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        retval.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        retval.setHeader("Authorization",
                createAuthorization(clientId, clientSecret));
        return retval;
    }

    static String createAuthorization(
            String clientId,
            String clientSecret)
        throws UnsupportedEncodingException
    {
        StringBuilder auth = new StringBuilder();
        auth.append(URLEncoder.encode(clientId, "UTF-8"));
        auth.append(":");
        auth.append(URLEncoder.encode(clientSecret, "UTF-8"));
        return "Basic " + Base64.encodeToString(auth.toString().getBytes(),
                Base64.NO_WRAP);
    }

    private Bundle parseResponse(HttpResponse response)
        throws ParseException, JSONException, IOException, HttpException
    {
        Bundle retval = new Bundle();

        StatusLine statusLine = response.getStatusLine();
        String body = EntityUtils.toString(response.getEntity(), "UTF-8");
        switch (statusLine.getStatusCode()) {
            case 200:
                JSONObject json = new JSONObject(body);
                AuthorizationData data = AuthorizationData.create(json);
                if (data != null) {
                    retval.putParcelable(KEY_AUTHORIZATION_DATA, data);
                } else {
                    throw new RuntimeException("no authorization data: " +
                            body);
                }
                break;
            case 400: {
                String error = (new JSONObject(body)).optString("error", null);
                if ("invalid_request".equals(error) ||
                        "invalid_grant".equals(error) ||
                        "unauthorized_client".equals(error) ||
                        "unsupported_grant_type".equals(error) ||
                        "invalid_scope".equals(error)) {
                    retval.putString(KEY_ERROR_CODE, error);
                } else {
                    throw new HttpException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(), body);
                }
                break;
            }
            case 401: {
                String error = (new JSONObject(body)).optString("error", null);
                if ("invalid_client".equals(error)) {
                    retval.putString(KEY_ERROR_CODE, error);
                } else {
                    throw new HttpException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(), body);
                }
                break;
            }
            case 500: {
                String error = (new JSONObject(body)).optString("error", null);
                if ("server_error".equals(error)) {
                    retval.putString(KEY_ERROR_CODE, error);
                } else {
                    throw new HttpException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(), body);
                }
                break;
            }
            case 503: {
                String error = (new JSONObject(body)).optString("error", null);
                if ("temporarily_unavailable".equals(error)) {
                    retval.putString(KEY_ERROR_CODE, error);
                } else {
                    throw new HttpException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase(), body);
                }
                break;
            }
            default:
                throw new HttpException(statusLine.getStatusCode(),
                        statusLine.getReasonPhrase(), body);
        }
        return retval;
    }

    private void notifyNullCode(String nonce) {
        Intent intent = generateIntent(nonce);
        intent.putExtra(KEY_EXCEPTION, new ResponseBodyParseException(
                    new Exception(
                        "Server response is invalid: code must not be null")));
        sendBroadcast(intent);
        finish();
    }

    private void notifyError(String error, String nonce) {
        Intent intent = generateIntent(nonce);
        intent.putExtra(KEY_ERROR_CODE, error);
        sendBroadcast(intent);
        finish();
    }

    private void notifyUnexpecedState(
            String receivedState,
            String state,
            String nonce)
    {
        Intent intent = generateIntent(nonce);
        intent.putExtra(KEY_EXCEPTION, new ResponseBodyParseException(
                    new Exception(
                        String.format(
                            "state is unmatched. expected=%s, actual=%s",
                            state, receivedState))));
        sendBroadcast(intent);
        finish();
    }

    private String generateState() {
        return Long.toString(System.currentTimeMillis());
    }

    private static String generateUrl(
            String authURL,
            String clientId,
            String redirectUri,
            String[] scopes,
            String state)
    {
        StringBuilder scope = new StringBuilder();
        for (String s : scopes) {
            if (scope.length() > 0) {
                scope.append("+");
            }
            scope.append(s);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(authURL).append("?");
        builder.append("client_id=").append(clientId).append("&");
        builder.append("response_type=code&");
        builder.append("redirect_uri=").append(redirectUri).append("&");
        builder.append("scope=").append(scope.toString()).append("&");
        builder.append("state=").append(state);
        return builder.toString();
    }

    private static Intent generateIntent(String nonce) {
        Intent retval = new Intent(ACTION_AUTH_RESULT);
        retval.putExtra(KEY_NONCE, nonce);
        return retval;
    }

}
