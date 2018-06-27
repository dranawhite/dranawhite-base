package com.dranawhite.base.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * int型枚举值校验
 *
 * @author liangyq
 * @version [1.0, 2018/5/30 10:26]
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IntEnumValidator.class})
public @interface IntEnum {

	int[] enums();

	String message() default "值越界！";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
