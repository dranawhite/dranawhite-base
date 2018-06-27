package com.dranawhite.base.util;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * 日期工具类
 *
 * <pre>
 *     Joda-Time jar包提供支持
 * </pre>
 *
 * @author dranawhite
 * @version [1.0, 2018/4/17 14:00]
 */
public final class DateUtil {

	public static String getYesterday() {
		DateTime dateTime = new DateTime();
		DateTime yesterday = dateTime.minusDays(1);
		return yesterday.toString(ISODateTimeFormat.basicDate());
	}

	public static String getToday() {
		DateTime dateTime = new DateTime();
		return dateTime.toString(ISODateTimeFormat.basicDate());
	}

	public static String getYearPatternSecond() {
		DateTime dateTime = new DateTime();
		return dateTime.toString("yyyyMMddHHmmss");
	}

	public static String getYearPatternMicsecond() {
		DateTime dateTime = new DateTime();
		return dateTime.toString("yyyyMMddHHmmssSSS");
	}

}
