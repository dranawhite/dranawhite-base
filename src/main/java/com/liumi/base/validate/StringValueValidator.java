package com.liumi.base.validate;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author liangyq
 * @version [1.0, 2018/5/30 14:02]
 */
public class StringValueValidator implements ConstraintValidator<StringValue, String> {

	private String val;

	@Override
	public void initialize(StringValue constraintAnnotation) {
		val = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		return value.equals(val);
	}
}
