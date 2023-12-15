package com.sera.payapp.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/*
아무 기능도 하지 않지만 Controller 에 어노테이션으로 정의해서 PersistenceAdapter 라는 것을 명시해주는 용도
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface PersistenceAdapter {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
