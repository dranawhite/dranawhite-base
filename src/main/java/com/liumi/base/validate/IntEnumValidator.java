package com.liumi.base.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author liangyq
 * @version [1.0, 2018/5/30 10:29]
 */
public class IntEnumValidator implements ConstraintValidator<IntEnum, Integer> {

	private int[] enums;

	@Override
	public void initialize(IntEnum constraintAnnotation) {
		enums = constraintAnnotation.enums();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (enums == null || enums.length == 0) {
			throw new IllegalStateException("@IntEnum中的enums属性必填");
		}
		for (int val : enums) {
			if (val == value.intValue()) {
				return true;
			}
		}
		return false;
	}
}
