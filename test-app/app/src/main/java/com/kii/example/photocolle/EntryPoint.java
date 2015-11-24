package com.kii.example.photocolle;

import java.util.EnumSet;

import com.kii.sdk.photocolle.AuthenticateCallback;
import com.kii.sdk.photocolle.AuthenticationContext;
import com.kii.sdk.photocolle.Authority;
import com.kii.sdk.photocolle.Scope;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EntryPoint extends Activity implements AuthenticateCallback {

    private static final String KEY_PREFERENCE = "EntryPointSetting";
    private static final String KEY_USER_ID = "userIDKey";
    private static final int REQUEST_CODE = 1234;

    private EnumSet<Scope> scopes;

    public EntryPoint() {
        this.scopes = EnumSet.allOf(Scope.class);
        this.scopes.remove(Scope.PHONEBOOK_ALLOWED_FRIENDS_BIDIRECTIONAL);
        this.scopes.remove(Scope.PHONEBOOK_POST_FEED);
        this.scopes.remove(Scope.PHONEBOOK_ADD_CONTACT);
        this.scopes.remove(Scope.DATABOX_ALL);
        this.scopes.remove(Scope.USER_ID);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_point);

        if (getUserID(this) == null) {
            updateUserID("test01");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.entry_point_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_set_user_id:
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.entry_point_menu_userid, null);
            final EditText editText = (EditText) view.findViewById(R.id.input_user_id);
            editText.setText(getUserID(this));

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.menu_set_user_id_title);
            builder.setView(view);
            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idx) {
                    updateUserID(editText.getText().toString());
                }
            });
            builder.setNeutralButton("Set null", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idx) {
                    updateUserID(null);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
            return true;
        case R.id.menu_select_scopes:
            Intent intent = new Intent(getApplicationContext(),
                    ScopeSelectorListActivity.class);
            intent.putExtra(ScopeSelectorListActivity.KEY_SCOPES, this.scopes);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data)
    {
        switch (requestCode) {
            case REQUEST_CODE:
                this.scopes = (EnumSet<Scope>)data.getSerializableExtra(
                        ScopeSelectorListActivity.KEY_SCOPES);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    public void onClickedAuthenticate(View view) {
        try {
            Context context = getApplicationContext();
            Authority.authenticate(this, MiscUtils.getClientId(context),
                    MiscUtils.getClientSecret(context),
                    MiscUtils.getRedirectUri(context),
                    this.scopes, null, getUserID(context), this);
        } catch (Exception e) {
            Log.e("EntryPoint", "authenticate fails", e);
            MiscUtils.showDialog(this, "Authenticate",
                    "fail to start to authenticate");
        }
    }

    public void onClickedGetIDs(View view) {
        startActivity(
            new Intent(getApplicationContext(), IDListActivity.class));
    }

    public void onClickedGetIDsWithTags(View view) {
        startActivity(
            new Intent(getApplicationContext(), IDWithTagListActivity.class));
    }

    public void onClickedGetTags(View view) {
        startActivity(
            new Intent(getApplicationContext(), TagListActivity.class));
    }

    public void onClieckedGetDeletionHistory(View view) {
        startActivity(new Intent(getApplicationContext(),
                DeletionHistoryActivity.class));
    }

    public void onClickedUpload(View view) {
        startActivity(new Intent(getApplicationContext(),
                UploadActivity.class));
    }

    public void onClickedGetCapacity(View view) {
        startActivity(new Intent(getApplicationContext(),
                    CapacityInfoActivity.class));
    }

    public void onListAccessToken(View view) {
        startActivity(new Intent(getApplicationContext(),
                    AccessTokenListActivity.class));
    }

    @Override
    public void onAuthenticated(
            AuthenticationContext context,
            Exception exception)
    {
        if (context != null) {
            MiscUtils.showDialog(this, "Authenticate",
                    "success to authenticate");
        } else {
            String msg = "fail to authenticate";
            if (exception != null) {
                Log.e("EntryPoint", "authenticate fails", exception);
                StringBuilder builder = new StringBuilder();
                builder.append(exception.getClass().getSimpleName());
                builder.append(":\n");
                if (exception.getMessage() != null) {
                    builder.append(exception.getMessage());
                } else {
                    builder.append("no message");
                }
                msg = builder.toString();
            } else {
                Log.e("EntryPoint", "authenticate fails");
            }
            MiscUtils.showDialog(this, "Authenticate", msg);
        }
    }

    public void onClickedRefreshToken(View view) {
        Context context = getApplicationContext();
        try {
            AuthenticationContext authContext = AuthenticationContext.loadFrom(
                    context, getUserID(context), MiscUtils.getClientId(context),
                    MiscUtils.getClientSecret(context));
            Authority.refreshToken(authContext, new AuthenticateCallback() {
                @Override
                public void onAuthenticated(AuthenticationContext context, Exception exception) {
                    if (context != null) {
                        MiscUtils.showDialog(EntryPoint.this, "RefreshToken",
                                "Success to refresh token");
                    } else {
                        Log.e("EntryPoint", "refresh failed.", exception);
                        MiscUtils.showDialog(EntryPoint.this, "RefreshToken",
                                "Failed to refresh token");
                    }
                }
            });
        } catch (Exception e) {
            Log.e("EntryPoint", "refresh failed.", e);
            MiscUtils.showDialog(EntryPoint.this, "RefreshToken",
                    "Failed to refresh token");
        }
    }

    static String getUserID(Context context) {
        SharedPreferences pref = context.getSharedPreferences(KEY_PREFERENCE,
                MODE_PRIVATE);
        return pref.getString(KEY_USER_ID, null);
    }
    
    private void updateUserID(String value) {
        SharedPreferences.Editor editor = getSharedPreferences(KEY_PREFERENCE,
                MODE_PRIVATE).edit();
        editor.putString(KEY_USER_ID, value);
        editor.commit();
    }
}
