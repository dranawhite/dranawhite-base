package com.liumi.base.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Author ：rui.wei
 * @Date ：Created in 15:37 2018/6/14
 * @Description：
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MinMaxLengthTrimValidator.class})
public @interface MinMaxLengthTrim {
    int minLength() default 0;

    int maxLength() default 50;

    String message() default "字段长度不再区间范围内";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
