package com.dranawhite.base.mybatis.comment;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

/**
 * 解析VM文件
 *
 * @author liangyq 2018/1/26
 */
public class VelocityReader {

    private VelocityEngine vmEngine;

    private String vmPath;

    VelocityReader() {
        vmEngine = new VelocityEngine();
    }

    void init() throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        InputStream propIns = resourceLoader.getResourceStream(VelocityConstants.VELOCITY_PATH);
        Properties props = new Properties();
        props.load(propIns);
        vmEngine.init(props);
    }

    void setVmPath(String vmPath) {
        this.vmPath = vmPath;
    }

    Writer putVariables(Map<String, String> variableMap, String defaultPath) {
        Template tpl = getTemplate(defaultPath);
        VelocityContext ctx = new VelocityContext();
        for(Map.Entry<String, String> entry : variableMap.entrySet()) {
            ctx.put(entry.getKey(), entry.getValue());
        }
        StringWriter writer = new StringWriter();
        tpl.merge(ctx, writer);
        return writer;
    }

    Template getTemplate(String defaultPath) {
        try {
            return vmEngine.getTemplate(vmPath);
        } catch (ResourceNotFoundException e) {
            return vmEngine.getTemplate( defaultPath);
        }
    }

}
