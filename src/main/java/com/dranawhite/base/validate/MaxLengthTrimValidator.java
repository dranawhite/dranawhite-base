package com.dranawhite.base.validate;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author liangyq
 * @version [1.0, 2018/5/29 17:43]
 */
public class MaxLengthTrimValidator implements ConstraintValidator<MaxLengthTrim, String> {

	private int length;

	@Override
	public void initialize(MaxLengthTrim constraintAnnotation) {
		length = constraintAnnotation.length();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value) || value.length() > length) {
			return false;
		}
		return true;
	}
}
