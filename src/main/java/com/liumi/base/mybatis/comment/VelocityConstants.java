package com.liumi.base.mybatis.comment;

/**
 * 解析VM文件
 *
 * @author liangyq 2018/1/26
 */
public final class VelocityConstants {

    private static final String PREFIX = "velocity/";

    public static final String DEFAULT_COPYRIGHT_PATH = PREFIX + "copyright.vm";

    public static final String DEFAULT_SETTER_PATH = PREFIX + "setter.vm";

    public static final String DEFAULT_GETTER_PATH = PREFIX + "getter.vm";

    public static final String DEFAULT_CLASS_PATH = PREFIX + "class.vm";

    public static final String VELOCITY_PATH = "velocity/velocity.properties";
}
