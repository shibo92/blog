package com.shibo.annotation;

import com.shibo.enums.UserTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shibo
 * @date 19-12-11 下午9:08
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface User {
    UserTypeEnum[] userType();
}
