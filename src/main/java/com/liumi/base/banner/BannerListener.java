package com.liumi.base.banner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author liangyq
 * @version [1.0, 2018/6/1 16:20]
 */
@Slf4j
@Component
public class BannerListener implements InitializingBean {

	@Override
	public void afterPropertiesSet() {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource("classpath:banner.txt");
		try(InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
			char[] buf = new char[2048];
			StringBuilder sb = new StringBuilder();
			while (reader.read(buf) > -1) {
				sb.append(buf);
			}
			log.info(sb.toString());
			log.info("====================系统初始化完成====================");
		} catch (Exception ex) {
			log.warn("加载Banner.txt失败，由于以下原因：", ex.getMessage());
		}
	}
}
