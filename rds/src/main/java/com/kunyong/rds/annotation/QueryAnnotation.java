package com.kunyong.rds.annotation;

import com.kunyong.rds.enums.MatchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryAnnotation {
    String column() default "";

    MatchType func() default MatchType.equal;
}
