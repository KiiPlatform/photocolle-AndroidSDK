package com.kii.example.photocolle;

import java.util.concurrent.TimeUnit;

import com.kii.sdk.photocolle.AuthenticateCallback;
import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.Authority;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccessTokenEditActivity extends Activity {

    public static final String KEY_STOREKEY = "storeKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_token_edit);

        updateEditText();
    }

    private void updateEditText() {
        SharedPreferences preference =
                AccessTokenListActivity.getPhotoCollePreference(this);

        AuthenticationContext context = null;
        try {
            context = AuthenticationContext.loadFrom(
                    this, getStoreKey(), MiscUtils.getClientId(this),
                    MiscUtils.getClientSecret(this));
        } catch (Exception e) {
            // nothing to do.
        }

        StringBuilder builder = new StringBuilder();
        builder.append("StoreKey: ");
        builder.append(getStoreKey());
        builder.append("\n");
        if (context != null) {
            builder.append(context.getRemainingTime(TimeUnit.SECONDS));
        } else {
            builder.append("loadFrom failed.");
        }
        TextView storeKeyView = (TextView)findViewById(R.id.store_key_text);
        storeKeyView.setText(builder.toString());

        EditText accessTokenEdit = (EditText)findViewById(R.id.access_token_edit);
        accessTokenEdit.setText(preference.getString(getAccessTokenKey(),
                "No Value"));

        EditText expiredTimeEdit = (EditText)findViewById(R.id.expired_time_edit);
        expiredTimeEdit.setText(Long.toString(preference.getLong(
                getExpiredDateKey(), 0L)));

        EditText refreshTokenEdit = (EditText)findViewById(R.id.refresh_token_edit);
        refreshTokenEdit.setText(preference.getString(getRefreshTokenKey(),
                "No Value"));
    }

    public void onRefreshToken(View view) {
        save();
        try {
            AuthenticationContext context = AuthenticationContext.loadFrom(
                    this, getStoreKey(), MiscUtils.getClientId(this),
                    MiscUtils.getClientSecret(this));
            Authority.refreshToken(context, new AuthenticateCallback() {
                @Override
                public void onAuthenticated(
                        AuthenticationContext refreshedContext,
                        Exception exception)
                {
                    if (exception != null) {
                        Log.e(getClass().getSimpleName(), "refresh failed.",
                                exception);
                        MiscUtils.showDialog(AccessTokenEditActivity.this,
                                "RefreshToken", "Failed to refresh token");
                    } else {
                        updateEditText();
                        MiscUtils.showDialog(AccessTokenEditActivity.this,
                                "RefreshToken", "Success to refresh token");
                    }
                }
            });
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "refresh failed.", e);
            MiscUtils.showDialog(AccessTokenEditActivity.this, "RefreshToken",
                    "Failed to refresh token");
        }
    }

    public void onSave(View view) {
        save();
    }

    private void save() {
        EditText accessTokenEdit = (EditText)findViewById(R.id.access_token_edit);
        EditText expiredTimeEdit = (EditText)findViewById(R.id.expired_time_edit);
        EditText refreshTokenEdit = (EditText)findViewById(R.id.refresh_token_edit);

        SharedPreferences.Editor editor =
                AccessTokenListActivity.getPhotoCollePreference(this).edit();
        editor.putString(getAccessTokenKey(), accessTokenEdit.getText().toString());
        editor.putLong(getExpiredDateKey(), Long.parseLong(
                expiredTimeEdit.getText().toString()));
        editor.putString(getRefreshTokenKey(),
                refreshTokenEdit.getText().toString());
        editor.commit();
    }

    public void onChangeAccessToken(View view) {
        String value = "dummy";
        SharedPreferences.Editor editor =
                AccessTokenListActivity.getPhotoCollePreference(this).edit();
        editor.putString(getAccessTokenKey(), value);
        if (editor.commit()) {
            EditText editView = (EditText)findViewById(R.id.access_token_edit);
            editView.setText(value);
        }
    }

    public void onChangeExpiredTime(View view) {
        Long value = System.currentTimeMillis();
        SharedPreferences.Editor editor =
                AccessTokenListActivity.getPhotoCollePreference(this).edit();
        editor.putLong(getExpiredDateKey(), value);
        if (editor.commit()) {
            updateEditText();
        }
    }

    public void onChangeRefreshToken(View view) {
        String value = "dummy";
        SharedPreferences.Editor editor =
                AccessTokenListActivity.getPhotoCollePreference(this).edit();
        editor.putString(getRefreshTokenKey(), value);
        if (editor.commit()) {
            EditText editView = (EditText)findViewById(R.id.refresh_token_edit);
            editView.setText(value);
        }
    }

    public void onChangeAll(View view) {
        String value1 = "dummy";
        Long value2 = System.currentTimeMillis();
        String value3 = "dummy";
        SharedPreferences.Editor editor =
                AccessTokenListActivity.getPhotoCollePreference(this).edit();
        editor.putString(getAccessTokenKey(), value1);
        editor.putLong(getExpiredDateKey(), value2);
        editor.putString(getRefreshTokenKey(), value3);
        if (editor.commit()) {
            EditText editView1 = (EditText)findViewById(R.id.access_token_edit);
            EditText editView2 = (EditText)findViewById(R.id.expired_time_edit);
            EditText editView3 = (EditText)findViewById(R.id.refresh_token_edit);
            editView1.setText(value1);
            editView2.setText(value2.toString());
            editView3.setText(value3);
        }
    }

    public void onRemove(View view) {
        SharedPreferences.Editor editor =
                AccessTokenListActivity.getPhotoCollePreference(this).edit();
        editor.remove(getAccessTokenKey());
        editor.remove(getExpiredDateKey());
        editor.remove(getRefreshTokenKey());
        if (editor.commit()) {
            finish();
        }
    }

    private String getStoreKey() {
        return getIntent().getStringExtra(KEY_STOREKEY);
    }

    private String getAccessTokenKey() {
        return getStoreKey() + ".accessToken";
    }

    private String getExpiredDateKey() {
        return getStoreKey() + ".expiredDate";
    }

    private String getRefreshTokenKey() {
        return getStoreKey() + ".refreshToken";
    }
}
