package com.kii.sdk.photocolle;

import static org.mockito.Mockito.*;

import java.net.URL;

import org.apache.http.client.methods.HttpGet;

import com.kii.example.photocolle.annotation.TestInformation;

import android.test.AndroidTestCase;

public class GetCapacityInfoLogicTest extends AndroidTestCase {

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dC1nd3RCR2pkRkVDSmdnYzc0UVVlZmc#gid=10",
            id = "GCILCR0001"
    )
    public void test1() throws Exception {
        // make mock.
        AuthenticationContext authenticateContext =
            mock(AuthenticationContext.class);
        // set return value to mock object.
        when(authenticateContext.getAccessToken()).thenReturn("ACCESS_TOKEN");

        GetCapacityInfoLogic.Args arg =
            new GetCapacityInfoLogic.Args(
                authenticateContext);
        GetCapacityInfoLogic logic = new GetCapacityInfoLogic();
        HttpGet request = (HttpGet)logic.createRequest(
                new URL("http://example.com"), arg);
        // verify mock access.
        verify(authenticateContext, times(1)).getAccessToken();

        // check request.
        assertNotNull(request);
        assertEquals("http://example.com", request.getURI().toString());
    }

}

