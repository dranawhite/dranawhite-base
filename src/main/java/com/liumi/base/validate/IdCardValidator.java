package com.liumi.base.validate;

import com.liumi.base.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author liangyq
 * @version [1.0, 2018/5/30 11:42]
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

	@Override
	public void initialize(IdCard constraintAnnotation) {
		// Do Nothing
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtil.isIDCard(value);
	}
}
