package com.dranawhite.base.util;

import com.dranawhite.base.exception.DranawhiteException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 地址工具类
 *
 * @author liangyq
 * @version [1.0, 2018/4/24 14:46]
 */
public final class InetAddressUtil {

	public static InetAddress getInetAddress() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException he) {
			throw new DranawhiteException(he);
		}
	}

	/**
	 * 获取机器IP地址
	 *
	 * @return IP地址
	 */
	public static String getLocalAddress() {
		return getInetAddress().getHostAddress();
	}

	/**
	 * 获取机器MAC地址
	 *
	 * @return MAC地址
	 */
	public static String getMacAddress() {
		InetAddress inetAddress = getInetAddress();
		try {
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0, len = mac.length; i < len; i++) {
				sb.append(Integer.toHexString(mac[i] & 0xFF)).append("-");
			}
			return sb.toString().substring(0, sb.length() - 1).toUpperCase();
		} catch (SocketException se) {
			throw new DranawhiteException(se);
		}
	}

}
