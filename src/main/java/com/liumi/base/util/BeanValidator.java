package com.liumi.base.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

/**
 * Hibernate Bean验证器
 *
 * @author liangyuquan
 * @version [1.0, 2018/4/23 17:22]
 */
public final class BeanValidator {

	private static Validator validator;

	static {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	public static <T, G> String validate(T obj, Class<G> ...clz) {
		Set<ConstraintViolation<T>> resultSet = validator.validate(obj, clz);
		if (CollectionUtils.isEmpty(resultSet)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (ConstraintViolation<T> violation : resultSet) {
			sb.append(violation.getMessage()).append("|");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public static <T> String  validate(T obj) {
		return validate(obj, Default.class);
	}

}
