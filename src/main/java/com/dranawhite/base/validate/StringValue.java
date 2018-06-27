package com.dranawhite.base.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 字符串值校验
 * <pre>
 *     允许null和空值
 * </pre>
 *
 * @author liangyq
 * @version [1.0, 2018/5/30 14:01]
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {StringValueValidator.class})
public @interface StringValue {

	String value() default "";

	String message() default "值非法！";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
