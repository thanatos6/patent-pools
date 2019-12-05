package com.suixingpay.aspect;

import java.lang.annotation.*;

/**
 * @author hyx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
