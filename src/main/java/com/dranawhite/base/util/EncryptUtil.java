package com.dranawhite.base.util;

import org.apache.commons.codec.digest.Md5Crypt;

/**
 * MD5加密
 * <pre>
 *     Commons-Codec包提供支持
 * </pre>
 *
 * @author dranawhite
 * @version [1.0, 2018/4/4 16:38]
 */
public final class EncryptUtil {

	/**
	 * MD5加盐加密
	 *
	 * @param passwd 密码
	 * @param salt   盐值
	 *
	 * @return 32位密文
	 */
	public static String encrypt(String passwd, String salt) {
		String pass = Md5Crypt.md5Crypt(passwd.getBytes(), salt);
		return pass.substring(2, pass.length());
	}

	/**
	 * 随机产生32位盐值
	 *
	 * @return 盐值
	 */
	public static String generateSalt() {
		String today = DateUtil.getToday();
		String salt = Md5Crypt.md5Crypt(today.getBytes());
		return salt.substring(2, salt.length());
	}

	/**
	 * MD5加密
	 *
	 * @param passwd 密码
	 *
	 * @return 32位密文
	 */
	public static String encrypt(String passwd) {
		String pass = Md5Crypt.md5Crypt(passwd.getBytes());
		return pass.substring(2, pass.length());
	}

}
