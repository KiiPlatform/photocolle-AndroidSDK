package com.kii.sdk.photocolle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

class AuthorizationData implements Parcelable {

    final String accessToken;
    final long expiredDate;
    final String refreshToken;
    final Set<String> scopes;

    private AuthorizationData(
            String accessToken,
            long expiredDate,
            String refreshToken,
            Set<String> scopes)
    {
        this.accessToken = accessToken;
        this.expiredDate = expiredDate;
        this.refreshToken = refreshToken;
        this.scopes = scopes;
    }

    static AuthorizationData create(JSONObject json) throws JSONException {
        String accessToken = json.getString("access_token");
        String expiresIn = json.getString("expires_in");
        String refreshToken = json.optString("refresh_token", null);
        String scope = json.getString("scope");
        long expiredDate = 0L;
        if (expiresIn != null) {
            expiredDate = System.currentTimeMillis() +
                    (Long.parseLong(expiresIn) * 1000);
        }
        Set<String> scopes = new HashSet<String>();
        for (String s : scope.split(" ")) {
            if (!TextUtils.isEmpty(s) && !scopes.contains(s)) {
                scopes.add(s);
            }
        }
        if (!TextUtils.isEmpty(accessToken) && expiredDate > 0L &&
                !"".equals(refreshToken) && !TextUtils.isEmpty(scope)) {
            return new AuthorizationData(accessToken, expiredDate,
                    refreshToken, scopes);
        } else {
            return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.accessToken);
        dest.writeLong(this.expiredDate);
        dest.writeString(this.refreshToken);
        dest.writeStringArray(this.scopes.toArray(new String[0]));
    }

    public static final Parcelable.Creator<AuthorizationData> CREATOR =
            new Parcelable.Creator<AuthorizationData>() {
        @Override
        public AuthorizationData createFromParcel(Parcel in) {
            return new AuthorizationData(in.readString(), in.readLong(),
                    in.readString(),
                    new HashSet<String>(Arrays.asList(in.createStringArray())));
        }

        @Override
        public AuthorizationData[] newArray(int size) {
            return new AuthorizationData[size];
        }
    };

}
