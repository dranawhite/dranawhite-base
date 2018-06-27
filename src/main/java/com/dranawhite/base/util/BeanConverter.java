package com.dranawhite.base.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Bean转换器
 * <pre>
 *     例如，DTO转为DO
 * </pre>
 *
 * @author dranawhite
 * @version [1.0, 2018/4/23 17:14]
 */
public final class BeanConverter {

	private static MapperFacade mapperFacade;

	static {
		DefaultMapperFactory.MapperFactoryBuilder builder = new DefaultMapperFactory.Builder();
		MapperFactory mapperFactory = builder.build();
		mapperFacade = mapperFactory.getMapperFacade();
	}

	public static <S, T> T convert(S origin, Class<T> clz) {
		return mapperFacade.map(origin, clz);
	}

}
