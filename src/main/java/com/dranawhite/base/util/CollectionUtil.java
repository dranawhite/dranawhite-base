package com.dranawhite.base.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author liangyq
 * @version [1.0, 2018/4/26 17:27]
 */
public final class CollectionUtil {

	public static boolean isEmpty(Collection<?> coll) {
		return CollectionUtils.isEmpty(coll);
	}

	public static boolean isEmpty(Object...obj) {
		return !isNotEmpty(obj);
	}

	public static boolean isEmpty(Map map) {
		if (map == null || map.size() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Collection<?> coll) {
		return CollectionUtils.isNotEmpty(coll);
	}

	public static boolean isNotEmpty(Object...obj) {
		return ObjectUtils.allNotNull(obj);
	}

	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}
}
