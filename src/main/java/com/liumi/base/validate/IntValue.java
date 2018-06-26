package com.liumi.base.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 校验字符串的值
 * <pre>
 *     不允许null和空值
 * </pre>
 *
 * @author liangyq
 * @version [1.0, 2018/5/29 17:47]
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IntValueValidator.class})
public @interface IntValue {

	int value() default 0;

	String message() default "值非法！";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
