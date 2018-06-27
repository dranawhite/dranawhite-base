package com.dranawhite.base.validate;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author     ：rui.wei
 * @Date       ：Created in 11:23 2018/6/14
 * @Description：
 */
public class MinLengthTrimValidator implements ConstraintValidator<MinLengthTrim, String> {

    private int length;

    @Override
    public void initialize(MinLengthTrim constraintAnnotation) {
        length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value) || value.length() < length) {
            return false;
        }
        return true;
    }
}
