package com.kii.example.photocolle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface TestInformation {
    /** Url for test case. */
    String url();

    /** test id */
    String id();
}
