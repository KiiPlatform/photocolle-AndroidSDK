package com.kii.sdk.photocolle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


class GetCapacityInfoLogic implements
        Logic<GetCapacityInfoLogic.Args, CapacityInfo>
 {

    private static final URL DEFAULT_URL = Command.toUrl(
            "https://xlb.photocolle-docomo.com/file_a2/1.0/docomo/capacity");

    public static class Args {

        public final AuthenticationContext context;

        public Args(AuthenticationContext context) {
            if (context == null) {
                throw new ParameterException(
                    ParameterException.Reason.NULL_ASSIGNED,
                    "context must not be null.");
            }

            this.context = context;
        }

    }

    @Override
    public HttpUriRequest createRequest(URL url, Args arg) {
        try {
            HttpGet retval = new HttpGet(url.toURI());

            // Set arguments for 3.00 request as HTTP header.
            Command.signRequest(retval, arg.context);

            return retval;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CapacityInfo parseResponse(
            HttpResponse response)
        throws HttpException, ApplicationLayerException,
            ResponseBodyParseException, InvalidTokenException
    {
        try {
            // check status code.
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                ResponseUtil.throwHttpStatusRelatedException(response);
            }

            // check result.
            JSONObject json = ResponseUtil.newJSONObject(EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            int result = ResponseUtil.getInt(json, "result");
            switch (result) {
                case 0:
                    return toCapacityInfo(json);
                case 1:
                    throw ResponseUtil.newApplicationLayerException(json);
                default:
                    throw new ParameterException(
                            ParameterException.Reason.OUT_OF_RANGE,
                            "result is out of range: " + result);
            }
        } catch (IOException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParseException e) {
            throw new ResponseBodyParseException(e);
        } catch (ParameterException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    private static CapacityInfo toCapacityInfo(
            JSONObject json)
        throws ResponseBodyParseException
    {
        try {
            return new CapacityInfo(
                Long.valueOf(json.optString("max_space", "-1")),
                Long.valueOf(ResponseUtil.getString(json, "free_space")));
        } catch (NumberFormatException e) {
            throw new ResponseBodyParseException(e);
        }
    }

    public URL getDefaultUrl() {
        return DEFAULT_URL;
    }
}
