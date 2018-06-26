package com.liumi.base.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 校验字符串最大长度
 * <pre>
 * 实例：
 * 		null   false
 * 	    ""	   false
 * 	    "  "   false
 * 	    "123"  true
 * </pre>
 *
 * @author liangyq
 * @version [1.0, 2018/5/29 17:40]
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MaxLengthTrimValidator.class})
public @interface MaxLengthTrim {

	int length() default 0;

	String message() default "超过最大长度限制！";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
