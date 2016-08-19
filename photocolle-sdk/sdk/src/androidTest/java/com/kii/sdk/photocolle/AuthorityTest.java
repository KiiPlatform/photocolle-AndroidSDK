package com.kii.sdk.photocolle;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.mockito.ArgumentCaptor;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.kii.example.photocolle.annotation.TestInformation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static org.mockito.Mockito.*;


public class AuthorityTest extends TestCaseBase {

    private static final AuthenticateCallback DUMMY_CALLBACK;
    private static final EnumSet<Scope> EMPTY_PREDEFINED_SCOPES;

    static {
        DUMMY_CALLBACK = new AuthenticateCallback() {
            @Override
            public void onAuthenticated(
                AuthenticationContext context,
                Exception exception)
            {
                // Nothing to do.
            }
        };

        EMPTY_PREDEFINED_SCOPES = EnumSet.allOf(Scope.class);
        EMPTY_PREDEFINED_SCOPES.clear();
    }

    public void testAuthenticateParameter() {
        AuthenticateCallback callback = new AuthenticateCallback() {

            @Override
            public void onAuthenticated(
                    AuthenticationContext context,
                    Exception exception)
            {
                // Nothing to do.
            }
        };

        ParameterException actualException = null;

        // check context is null.
        try {
            Authority.authenticate(
                null,
                "clientId",
                "clientSecret",
                "redirectUri",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("context must no be null.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check clientId is null.
        try {
            Authority.authenticate(
                getContext(),
                null,
                "clientSecret",
                "redirectUri",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("clientId must no be null.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check clientId is empty.
        try {
            Authority.authenticate(
                getContext(),
                "",
                "clientSecret",
                "redirectUri",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("clientId must no be empty.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check clientSecret is null.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                null,
                "redirectUri",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("clientSecret must no be null.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check clientSecret is empty.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                "",
                "redirectUri",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("clientSecret must no be empty.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check redirectUri is null.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                null,
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("redirectUri must no be null.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check redirectUri is empty.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                "",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("redirectUri must no be empty.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check predefinedScopes and extendedScopes are null.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                "redirectUri",
                null,
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals(
                "At least, predefinedScopes and/or extendedScopes must not be null or empty.",
                actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check predefinedScopes and extendedScopes are empty.
        try {
            EnumSet<Scope> predefinedScopes = EnumSet.allOf(Scope.class);
            predefinedScopes.clear();
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                "redirectUri",
                predefinedScopes,
                new String[] { },
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals(
                "At least, predefinedScopes and/or extendedScopes must not be null or empty.",
                actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check predefinedScopes is null and extendedScopes is empty.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                "redirectUri",
                null,
                new String[] { },
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals(
                "At least, predefinedScopes and/or extendedScopes must not be null or empty.",
                actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check predefinedScopes is empty and extendedScopes is null.
        try {
            EnumSet<Scope> predefinedScopes = EnumSet.allOf(Scope.class);
            predefinedScopes.clear();
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                "redirectUri",
                predefinedScopes,
                null,
                "storeKey",
                callback);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals(
                "At least, predefinedScopes and/or extendedScopes must not be null or empty.",
                actualException.getMessage());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    actualException.getReason());
        }
        assertNotNull(actualException);
        actualException = null;

        // check callback is null.
        try {
            Authority.authenticate(
                getContext(),
                "clientId",
                "clientSecret",
                "redirectUri",
                EnumSet.allOf(Scope.class),
                null,
                "storeKey",
                null);
        } catch (ParameterException e) {
            actualException = e;
            assertEquals("callback must no be null.",
                    actualException.getMessage());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    actualException.getReason());
        }
    }

    public void testAuthenticateExtendedScopes() throws InterruptedException {
        AuthenticateCallback callback = new AuthenticateCallback() {

            @Override
            public void onAuthenticated(
                    AuthenticationContext context,
                    Exception exception)
            {
                // Nothing to do.
            }
        };

        // Set 1 extended scope.
        {
            final CountDownLatch latch = new CountDownLatch(1);
            Context spyContext = spy(getContext().getApplicationContext());
            ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
                    Intent.class);
            final Intent[] actualIntent = new Intent[1];
            doAnswer(new Answer() {
                @Override
                public Object answer(
                        InvocationOnMock invocation)
                    throws Throwable
                {
                    actualIntent[0] = (Intent)invocation.getArguments()[0];
                    latch.countDown();
                    return null;
                }
            }).when(spyContext).startActivity(argumentCaptor.capture());

            Authority.authenticate(
                spyContext,
                "clientId",
                "clientSecret",
                "redirectUri",
                null,
                new String[] {"dummy"},
                "storeKey",
                callback);
            latch.await(10, TimeUnit.SECONDS);
            String[] actual = actualIntent[0].getStringArrayExtra("scopes");
            assertEquals(1, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList("dummy")),
                new HashSet<String>(Arrays.asList(actual)));
        }

        // Set 1 extended scope and 1 predefined scope.
        {
            final CountDownLatch latch = new CountDownLatch(1);
            Context spyContext = spy(getContext().getApplicationContext());
            ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
                    Intent.class);
            final Intent[] actualIntent = new Intent[1];
            doAnswer(new Answer() {
                @Override
                public Object answer(
                        InvocationOnMock invocation)
                    throws Throwable
                {
                    actualIntent[0] = (Intent)invocation.getArguments()[0];
                    latch.countDown();
                    return null;
                }
            }).when(spyContext).startActivity(argumentCaptor.capture());

            Authority.authenticate(
                spyContext,
                "clientId",
                "clientSecret",
                "redirectUri",
                EnumSet.of(Scope.PHOTO_GET_CONTENTS_LIST),
                new String[] {"dummy"},
                "storeKey",
                callback);
            latch.await(10, TimeUnit.SECONDS);
            String[] actual = actualIntent[0].getStringArrayExtra("scopes");
            assertEquals(2, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            "dummy")),
                new HashSet<String>(Arrays.asList(actual)));
        }

        // Set 5 extended scopes.
        {
            final CountDownLatch latch = new CountDownLatch(1);
            Context spyContext = spy(getContext().getApplicationContext());
            ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
                    Intent.class);
            final Intent[] actualIntent = new Intent[1];
            doAnswer(new Answer() {
                @Override
                public Object answer(
                        InvocationOnMock invocation)
                    throws Throwable
                {
                    actualIntent[0] = (Intent)invocation.getArguments()[0];
                    latch.countDown();
                    return null;
                }
            }).when(spyContext).startActivity(argumentCaptor.capture());

            Authority.authenticate(
                spyContext,
                "clientId",
                "clientSecret",
                "redirectUri",
                null,
                new String[] {
                            "dummy1",
                            "dummy2",
                            "dummy3",
                            "dummy4",
                            "dummy5"},
                "storeKey",
                callback);
            latch.await(10, TimeUnit.SECONDS);
            String[] actual = actualIntent[0].getStringArrayExtra("scopes");
            assertEquals(5, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            "dummy1",
                            "dummy2",
                            "dummy3",
                            "dummy4",
                            "dummy5")),
                new HashSet<String>(Arrays.asList(actual)));
        }

        // Set 5 extended scope and 5 predefined scope.
        {
            final CountDownLatch latch = new CountDownLatch(1);
            Context spyContext = spy(getContext().getApplicationContext());
            ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
                    Intent.class);
            final Intent[] actualIntent = new Intent[1];
            doAnswer(new Answer() {
                @Override
                public Object answer(
                        InvocationOnMock invocation)
                    throws Throwable
                {
                    actualIntent[0] = (Intent)invocation.getArguments()[0];
                    latch.countDown();
                    return null;
                }
            }).when(spyContext).startActivity(argumentCaptor.capture());

            Authority.authenticate(
                spyContext,
                "clientId",
                "clientSecret",
                "redirectUri",
                EnumSet.of(
                    Scope.PHOTO_GET_CONTENTS_LIST,
                    Scope.PHOTO_GET_CONTENT,
                    Scope.PHOTO_UPLOAD_CONTENT,
                    Scope.PHOTO_GET_VACANT_SIZE,
                    Scope.PHOTO_UPDATE_ROTATE_INFO),
                new String[] {
                            "dummy1",
                            "dummy2",
                            "dummy3",
                            "dummy4",
                            "dummy5"},
                "storeKey",
                callback);
            latch.await(10, TimeUnit.SECONDS);
            String[] actual = actualIntent[0].getStringArrayExtra("scopes");
            assertEquals(10, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENT.getLabel(),
                            Scope.PHOTO_UPLOAD_CONTENT.getLabel(),
                            Scope.PHOTO_GET_VACANT_SIZE.getLabel(),
                            Scope.PHOTO_UPDATE_ROTATE_INFO.getLabel(),
                            "dummy1",
                            "dummy2",
                            "dummy3",
                            "dummy4",
                            "dummy5")),
                new HashSet<String>(Arrays.asList(actual)));
        }

        // Set 1 extended scope and 1 predefined scope and these are same..
        {
            final CountDownLatch latch = new CountDownLatch(1);
            Context spyContext = spy(getContext().getApplicationContext());
            ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
                    Intent.class);
            final Intent[] actualIntent = new Intent[1];
            doAnswer(new Answer() {
                @Override
                public Object answer(
                        InvocationOnMock invocation)
                    throws Throwable
                {
                    actualIntent[0] = (Intent)invocation.getArguments()[0];
                    latch.countDown();
                    return null;
                }
            }).when(spyContext).startActivity(argumentCaptor.capture());

            Authority.authenticate(
                spyContext,
                "clientId",
                "clientSecret",
                "redirectUri",
                EnumSet.of(Scope.PHOTO_GET_CONTENTS_LIST),
                new String[] {Scope.PHOTO_GET_CONTENTS_LIST.getLabel()},
                "storeKey",
                callback);
            latch.await(10, TimeUnit.SECONDS);
            String[] actual = actualIntent[0].getStringArrayExtra("scopes");
            assertEquals(1, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel())),
                new HashSet<String>(Arrays.asList(actual)));
        }

        // Set 5 extended scope and 5 predefined scope. These are same.
        {
            final CountDownLatch latch = new CountDownLatch(1);
            Context spyContext = spy(getContext().getApplicationContext());
            ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
                    Intent.class);
            final Intent[] actualIntent = new Intent[1];
            doAnswer(new Answer() {
                @Override
                public Object answer(
                        InvocationOnMock invocation)
                    throws Throwable
                {
                    actualIntent[0] = (Intent)invocation.getArguments()[0];
                    latch.countDown();
                    return null;
                }
            }).when(spyContext).startActivity(argumentCaptor.capture());

            Authority.authenticate(
                spyContext,
                "clientId",
                "clientSecret",
                "redirectUri",
                EnumSet.of(
                    Scope.PHOTO_GET_CONTENTS_LIST,
                    Scope.PHOTO_GET_CONTENT,
                    Scope.PHOTO_UPLOAD_CONTENT,
                    Scope.PHOTO_GET_VACANT_SIZE,
                    Scope.PHOTO_UPDATE_ROTATE_INFO),
                new String[] {
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENT.getLabel(),
                            Scope.PHOTO_UPLOAD_CONTENT.getLabel(),
                            Scope.PHOTO_GET_VACANT_SIZE.getLabel(),
                            Scope.PHOTO_UPDATE_ROTATE_INFO.getLabel()},
                "storeKey",
                callback);
            latch.await(10, TimeUnit.SECONDS);
            String[] actual = actualIntent[0].getStringArrayExtra("scopes");
            assertEquals(5, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENT.getLabel(),
                            Scope.PHOTO_UPLOAD_CONTENT.getLabel(),
                            Scope.PHOTO_GET_VACANT_SIZE.getLabel(),
                            Scope.PHOTO_UPDATE_ROTATE_INFO.getLabel())),
                new HashSet<String>(Arrays.asList(actual)));
        }
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dFlGYk03MTNTd0FHdzNHSm1KeExJVkE&usp=drive_web#gid=3",
            id = "ID0007"
    )
    public void testAuthenticateExtendedScopesNullPredefined5()
        throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        Context spyContext = spy(getContext().getApplicationContext());
        ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
            Intent.class);
        final Intent[] actualIntent = new Intent[1];
        doAnswer(new Answer() {
            @Override
            public Object answer(
                    InvocationOnMock invocation)
                throws Throwable
            {
                actualIntent[0] = (Intent)invocation.getArguments()[0];
                latch.countDown();
                return null;
            }
        }).when(spyContext).startActivity(argumentCaptor.capture());

        Authority.authenticate(
            spyContext,
            "clientId",
            "clientSecret",
            "redirectUri",
            EnumSet.of(
                Scope.PHOTO_GET_CONTENTS_LIST,
                Scope.PHOTO_GET_CONTENT,
                Scope.PHOTO_UPLOAD_CONTENT,
                Scope.PHOTO_GET_VACANT_SIZE,
                Scope.PHOTO_UPDATE_ROTATE_INFO),
            null,
            "storeKey",
            DUMMY_CALLBACK);
        latch.await(10, TimeUnit.SECONDS);
        String[] actual = actualIntent[0].getStringArrayExtra("scopes");
        assertEquals(5, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENT.getLabel(),
                            Scope.PHOTO_UPLOAD_CONTENT.getLabel(),
                            Scope.PHOTO_GET_VACANT_SIZE.getLabel(),
                            Scope.PHOTO_UPDATE_ROTATE_INFO.getLabel())),
                new HashSet<String>(Arrays.asList(actual)));
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dFlGYk03MTNTd0FHdzNHSm1KeExJVkE&usp=drive_web#gid=3",
            id = "ID0008"
    )
    public void testAuthenticateExtendedScopesEmptyPredefined5()
        throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        Context spyContext = spy(getContext().getApplicationContext());
        ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
            Intent.class);
        final Intent[] actualIntent = new Intent[1];
        doAnswer(new Answer() {
            @Override
            public Object answer(
                    InvocationOnMock invocation)
                throws Throwable
            {
                actualIntent[0] = (Intent)invocation.getArguments()[0];
                latch.countDown();
                return null;
            }
        }).when(spyContext).startActivity(argumentCaptor.capture());

        Authority.authenticate(
            spyContext,
            "clientId",
            "clientSecret",
            "redirectUri",
            EnumSet.of(
                Scope.PHOTO_GET_CONTENTS_LIST,
                Scope.PHOTO_GET_CONTENT,
                Scope.PHOTO_UPLOAD_CONTENT,
                Scope.PHOTO_GET_VACANT_SIZE,
                Scope.PHOTO_UPDATE_ROTATE_INFO),
            new String[] {},
            "storeKey",
            DUMMY_CALLBACK);
        latch.await(10, TimeUnit.SECONDS);
        String[] actual = actualIntent[0].getStringArrayExtra("scopes");
        assertEquals(5, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList(
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENTS_LIST.getLabel(),
                            Scope.PHOTO_GET_CONTENT.getLabel(),
                            Scope.PHOTO_UPLOAD_CONTENT.getLabel(),
                            Scope.PHOTO_GET_VACANT_SIZE.getLabel(),
                            Scope.PHOTO_UPDATE_ROTATE_INFO.getLabel())),
                new HashSet<String>(Arrays.asList(actual)));
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dFlGYk03MTNTd0FHdzNHSm1KeExJVkE&usp=drive_web#gid=3",
            id = "ID0009"
    )
    public void testAuthenticateExtendedScopesDuplicatePredefinedScopesNull()
        throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        Context spyContext = spy(getContext().getApplicationContext());
        ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
            Intent.class);
        final Intent[] actualIntent = new Intent[1];
        doAnswer(new Answer() {
            @Override
            public Object answer(
                    InvocationOnMock invocation)
                throws Throwable
            {
                actualIntent[0] = (Intent)invocation.getArguments()[0];
                latch.countDown();
                return null;
            }
        }).when(spyContext).startActivity(argumentCaptor.capture());

        Authority.authenticate(
            spyContext,
            "clientId",
            "clientSecret",
            "redirectUri",
            null,
            new String[] { "duplicated", "duplicated" },
            "storeKey",
            DUMMY_CALLBACK);
        latch.await(10, TimeUnit.SECONDS);
        String[] actual = actualIntent[0].getStringArrayExtra("scopes");
        assertEquals(1, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList("duplicated")),
                new HashSet<String>(Arrays.asList(actual)));
    }

    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheet/ccc?key=0Al0NPhbFN_J-dFlGYk03MTNTd0FHdzNHSm1KeExJVkE&usp=drive_web#gid=3",
            id = "ID0010"
    )
    public void testAuthenticateExtendedScopesDuplicatePredefinedScopesEmpty()
        throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        Context spyContext = spy(getContext().getApplicationContext());
        ArgumentCaptor<Intent> argumentCaptor = ArgumentCaptor.forClass(
            Intent.class);
        final Intent[] actualIntent = new Intent[1];
        doAnswer(new Answer() {
            @Override
            public Object answer(
                    InvocationOnMock invocation)
                throws Throwable
            {
                actualIntent[0] = (Intent)invocation.getArguments()[0];
                latch.countDown();
                return null;
            }
        }).when(spyContext).startActivity(argumentCaptor.capture());

        Authority.authenticate(
            spyContext,
            "clientId",
            "clientSecret",
            "redirectUri",
            EMPTY_PREDEFINED_SCOPES,
            new String[] { "duplicated", "duplicated" },
            "storeKey",
            DUMMY_CALLBACK);
        latch.await(10, TimeUnit.SECONDS);
        String[] actual = actualIntent[0].getStringArrayExtra("scopes");
        assertEquals(1, actual.length);
            assertEquals(
                new HashSet<String>(Arrays.asList("duplicated")),
                new HashSet<String>(Arrays.asList(actual)));
    }


    @TestInformation(
            url = "https://docs.google.com/a/muraoka-design.com/spreadsheets/d/1e0E9LL7W30qFeYHtju-xl50cCwTHXrTSypqUQdup1Fs/edit#gid=0",
            id = "ID0001,ID0002,ID0003,ID0004,ID0005,ID0006,ID0007,ID0008"
    )
    public void testRefreshTokenParameters() {
        // ID0001
        {
            Exception throwed = null;
            try {
                Authority.refreshToken(null, new AuthenticateCallback() {
                    @Override
                    public void onAuthenticated(
                            AuthenticationContext refreshedContext,
                            Exception exception)
                    {
                        assertTrue("no call this callback", false);
                    }
                });
            } catch (Exception e) {
                throwed = e;
            }
            assertNotNull("exception must be throw", throwed);
            assertEquals(ParameterException.class, throwed.getClass());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    ((ParameterException)throwed).getReason());
            assertEquals("authenticationContext must no be null.",
                    throwed.getMessage());
        }

        // ID0002
        {
            AuthenticationContext context = mock(AuthenticationContext.class);
            Whitebox.setInternalState(context, "clientId", null);
            Whitebox.setInternalState(context, "clientSecret", "dummy");
            Whitebox.setInternalState(context, "appContext", mContext);
            Exception throwed = null;
            try {
                Authority.refreshToken(context, new AuthenticateCallback() {
                    @Override
                    public void onAuthenticated(
                            AuthenticationContext refreshedContext,
                            Exception exception)
                    {
                        assertTrue("no call this callback", false);
                    }
                });
            } catch (Exception e) {
                throwed = e;
            }
            assertNotNull("exception must be throw", throwed);
            assertEquals(ParameterException.class, throwed.getClass());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    ((ParameterException)throwed).getReason());
            assertEquals(
                    "AuthenticateContext which loaded by deprecated loadBy method can't refresh.",
                    throwed.getMessage());
        }

        // ID0003
        {
            AuthenticationContext context = mock(AuthenticationContext.class);
            Whitebox.setInternalState(context, "clientId", "");
            Whitebox.setInternalState(context, "clientSecret", "dummy");
            Whitebox.setInternalState(context, "appContext", mContext);
            Exception throwed = null;
            try {
                Authority.refreshToken(context, new AuthenticateCallback() {
                    @Override
                    public void onAuthenticated(
                            AuthenticationContext refreshedContext,
                            Exception exception)
                    {
                        assertTrue("no call this callback", false);
                    }
                });
            } catch (Exception e) {
                throwed = e;
            }
            assertNotNull("exception must be throw", throwed);
            assertEquals(ParameterException.class, throwed.getClass());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    ((ParameterException)throwed).getReason());
            assertEquals(
                    "AuthenticateContext which loaded by deprecated loadBy method can't refresh.",
                    throwed.getMessage());
        }

        // ID0004
        {
            AuthenticationContext context = mock(AuthenticationContext.class);
            Whitebox.setInternalState(context, "clientId", "dummy");
            Whitebox.setInternalState(context, "clientSecret", null);
            Whitebox.setInternalState(context, "appContext", mContext);
            Exception throwed = null;
            try {
                Authority.refreshToken(context, new AuthenticateCallback() {
                    @Override
                    public void onAuthenticated(
                            AuthenticationContext refreshedContext,
                            Exception exception)
                    {
                        assertTrue("no call this callback", false);
                    }
                });
            } catch (Exception e) {
                throwed =e;
            }
            assertNotNull("exception must be throw", throwed);
            assertEquals(ParameterException.class, throwed.getClass());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    ((ParameterException)throwed).getReason());
            assertEquals(
                    "AuthenticateContext which loaded by deprecated loadBy method can't refresh.",
                    throwed.getMessage());
        }

        // ID0005
        {
            AuthenticationContext context = mock(AuthenticationContext.class);
            Whitebox.setInternalState(context, "clientId", "dummy");
            Whitebox.setInternalState(context, "clientSecret", "");
            Whitebox.setInternalState(context, "appContext", mContext);
            Exception throwed = null;
            try {
                Authority.refreshToken(context, new AuthenticateCallback() {
                    @Override
                    public void onAuthenticated(
                            AuthenticationContext refreshedContext,
                            Exception exception)
                    {
                        assertTrue("no call this callback", false);
                    }
                });
            } catch (Exception e) {
                throwed = e;
            }
            assertNotNull("exception must be throw", throwed);
            assertEquals(ParameterException.class, throwed.getClass());
            assertEquals(ParameterException.Reason.OUT_OF_RANGE,
                    ((ParameterException)throwed).getReason());
            assertEquals(
                    "AuthenticateContext which loaded by deprecated loadBy method can't refresh.",
                    throwed.getMessage());
        }

        // ID0006
        {
            AuthenticationContext context = mock(AuthenticationContext.class);
            Whitebox.setInternalState(context, "clientId", "dummy");
            Whitebox.setInternalState(context, "clientSecret", "dummy");
            Whitebox.setInternalState(context, "refreshToken", null);
            Whitebox.setInternalState(context, "appContext", mContext);
            Exception noThrow = null;
            try {
                final CountDownLatch latch = new CountDownLatch(1);
                Authority.refreshToken(context, new AuthenticateCallback() {
                    @Override
                    public void onAuthenticated(
                            AuthenticationContext refreshedContext,
                            Exception exception)
                    {
                        assertNull("refreshedContext must be null.",
                                refreshedContext);
                        assertNotNull("exception must not be null.",
                                exception);
                        assertEquals(NoRefreshTokenException.class,
                                exception.getClass());
                        latch.countDown();
                    }
                });
                assertTrue("latch was timeout.",
                        latch.await(10, TimeUnit.SECONDS));
            } catch (Exception e) {
                noThrow = e;
            }
            assertNull("exception must not be throwed.", noThrow);
        }

        // ID0008
        {
            AuthenticationContext context = mock(AuthenticationContext.class);
            Whitebox.setInternalState(context, "clientId", "dummy");
            Whitebox.setInternalState(context, "clientSecret", "dummy");
            Whitebox.setInternalState(context, "appContext", mContext);
            Exception throwed = null;
            try {
                Authority.refreshToken(context, null);
            } catch (Exception e) {
                throwed = e;
            }
            assertNotNull("exception must be throw", throwed);
            assertEquals(ParameterException.class, throwed.getClass());
            assertEquals(ParameterException.Reason.NULL_ASSIGNED,
                    ((ParameterException)throwed).getReason());
            assertEquals("callback must no be null.",
                    throwed.getMessage());
        }

    }
}
