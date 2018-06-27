package com.dranawhite.base.util;

import com.dranawhite.common.constants.Separator;
import com.fasterxml.uuid.Generators;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * UUID工具类
 * <pre>
 * UUID版本：
 * 1）基于时间
 * 		计算当前时间戳、随机数和机器Mac地址或者IP
 * 2）DCE安全
 * 		算法和基于时间的相同，但是会把时间的前4位置换为POSIX的UID或GID
 * 3）基于名称
 * 		计算名称和名字空间的MD5散列值
 * 4）随机
 * 		伪随机数生成
 * 5）基于名称(SHA1)
 * 		和版本3算法类似，只是算法使用SHA1
 * </pre>
 *
 * @author dranawhite
 * @version [1.0, 2018/4/17 14:19]
 */
public final class UUIDUtil {

	/**
	 * version 1
	 * 产生基于时间的32位UUID
	 *
	 * @return UUID
	 */
	public static String getTimedUUID() {
		return Generators.timeBasedGenerator().generate().toString();
	}

	/**
	 * version 1
	 *
	 * 产生基于时间的32位UUID
	 * <pre>
	 *     全局有序，日期可读
	 * </pre>
	 *
	 * 格式如下： 1804241902282658/D&gt;1=,-&lt;+).000001
	 * <pre>
	 *     日期从年开始，精确到毫秒；中间是MAC地址，后6位是序列
	 * </pre>
	 *
	 * @return UUID
	 */
	public static String getTimedPatternUUID() {
		StringBuilder sb = new StringBuilder();
		String time = DateUtil.getYearPatternMicsecond();
		AtomicLong counter = Counter.counter;
		long index = counter.getAndIncrement();
		String indexStr = String.valueOf(index % 100000);
		// 序列，扩展到6位
		sb.append(time.substring(2, time.length()))
				.append(Address.getAddress())
				.append(StringUtil.fillStr(indexStr, 6));
		return sb.toString();
	}

	/**
	 * version 4
	 * 随机UUID
	 *
	 * @return UUID
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replace(Separator.MIDDLELINE, "");
	}

	/**
	 * 计数器，单例
	 */
	static class Counter {

		public static AtomicLong counter = new AtomicLong();
	}

	/**
	 * MAC地址，单例
	 * <pre>
	 *     获取MAC地址耗费太大
	 * </pre>
	 */
	static class Address {

		private static volatile String address;

		public static String getAddress() {
			// 双重检测单例
			if (address != null) {
				return address;
			}
			synchronized (Address.class) {
				if (address != null) {
					return address;
				}
				String mac = InetAddressUtil.getMacAddress();
				String macStr = mac.replace(Separator.MIDDLELINE, "");
				char[] chs = macStr.toCharArray();
				for (int i = 0, len = chs.length; i < len; i++) {
					chs[i] = (char) (chs[i] - i);
				}
				address = new String(chs);
			}
			return address;
		}

	}

}
