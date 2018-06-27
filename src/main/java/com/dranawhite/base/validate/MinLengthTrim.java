package com.dranawhite.base.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @ Author     ：rui.wei
 * @ Date       ：Created in 11:22 2018/6/14
 * @ Description：
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MinLengthTrimValidator.class})
public @interface MinLengthTrim {
    int length() default 0;

    String message() default "未达到最小长度限制！";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
