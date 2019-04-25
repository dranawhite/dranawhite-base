package com.dranawhite.base.util;

import com.dranawhite.base.exception.DranawhiteException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;


/**
 * Property工具类
 *
 * @author liangyq
 * @version [1.0, 2018/4/27 16:06]
 */
public final class PropertyLoader {

    private static Properties prop;

    public static void load(String url) {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(url);
            prop = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException ioe) {
            throw new DranawhiteException(ioe);
        }
    }

    public static String getProp(String key) {
        if (prop == null) {
            throw new DranawhiteException("未指定Properties文件！");
        }
        return prop.getProperty(key);
    }
}
