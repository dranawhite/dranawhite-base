package com.dranawhite.base.validate;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author ：rui.wei
 * @Date ：Created in 15:38 2018/6/14
 * @Description：
 */
public class MinMaxLengthTrimValidator  implements ConstraintValidator<MinMaxLengthTrim, String> {

    private int minLength;

    private int maxLength;

    @Override
    public void initialize(MinMaxLengthTrim constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value) || value.length() < minLength || value.length() > maxLength) {
            return false;
        }
        return true;
    }
}
